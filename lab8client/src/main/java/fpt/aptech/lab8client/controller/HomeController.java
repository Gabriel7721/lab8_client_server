package fpt.aptech.lab8client.controller;

import fpt.aptech.lab8client.dto.EmployeeDTO;
import fpt.aptech.lab8client.models.Employee;
import fpt.aptech.lab8client.models.Department;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final String urlEmployee = "http://localhost:9999/api/employee";
    private final String urlDepartment = "http://localhost:9999/api/department";
    private static final RestTemplate restTemplate = new RestTemplate();

    // Method to check login status
    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("loggedInUser") != null;
    }

    @RequestMapping("/")
    public String index(Model model, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }
        ResponseEntity<List<Employee>> response = restTemplate.exchange(
                urlEmployee + "/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>() {
        }
        );
        List<Employee> eList = response.getBody();

        if (eList != null) {
            List<EmployeeView> employeeViews = eList.stream().map(employee -> {
                String departmentName = "Unknown";
                try {
                    Department department = restTemplate.getForObject(urlDepartment + "/" + employee.getDepartment().getId(), Department.class);
                    if (department != null) {
                        departmentName = department.getName();
                    }
                } catch (HttpClientErrorException.NotFound e) {
                    System.err.println("Department not found: " + employee.getDepartment().getId());
                }
                return new EmployeeView(employee, departmentName);
            }).collect(Collectors.toList());

            model.addAttribute("employees", employeeViews);
        }
        return "index";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @RequestParam String code, @RequestParam String password, HttpSession session) {
        try {
            Employee employee = restTemplate.getForObject(urlEmployee + "/" + code + "/" + password, Employee.class);
            if (employee != null && employee.getIsactive() != null && employee.getIsactive()) {
                session.setAttribute("loggedInUser", employee);
                return "redirect:/";
            } else {
                model.addAttribute("error", "Invalid credentials or account not active");
                return "login";
            }
        } catch (HttpClientErrorException e) {
            model.addAttribute("error", "Login failed: " + e.getMessage());
            return "login";
        }
    }

    @GetMapping("/create")
    public String createForm(Model model, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }
        model.addAttribute("employee", new Employee());
        return "create";
    }

    @PostMapping("/save")
    public String saveEmployee(Model model, @Valid EmployeeDTO employeeDTO, BindingResult bindingResult, HttpSession session) throws IOException {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }
        if (bindingResult.hasErrors()) {
            return "create";
        }
        Employee employee = new Employee(employeeDTO.getCode(), employeeDTO.getName(), employeeDTO.getPassword(),
                employeeDTO.getSkill(), employeeDTO.getSalary(), employeeDTO.getIsactive(), employeeDTO.getDepartment());
        try {
            restTemplate.postForEntity(urlEmployee + "/create", employee, Employee.class);
            return "redirect:/";
        } catch (HttpClientErrorException e) {
            model.addAttribute("error", "Error saving employee: " + e.getMessage());
            return "create";
        }
    }

    @GetMapping("/edit/{code}")
    public String editForm(@PathVariable("code") String code, Model model, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }
        try {
            Employee employee = restTemplate.getForObject(urlEmployee + "/" + code, Employee.class);
            model.addAttribute("employee", employee);
            return "edit";
        } catch (HttpClientErrorException e) {
            model.addAttribute("error", "Error retrieving employee: " + e.getMessage());
            return "redirect:/";
        }
    }

    @PostMapping("/update")
    public String updateEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        try {
            restTemplate.put(urlEmployee + "/save", employee, Employee.class);
            return "redirect:/";
        } catch (HttpClientErrorException e) {
            session.setAttribute("error", "Error updating employee: " + e.getMessage());
            return "edit";
        }
    }

    @GetMapping("/delete/{code}")
    public String deleteEmployee(@PathVariable("code") String code, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }
        try {
            restTemplate.delete(urlEmployee + "/delete/" + code);
        } catch (HttpClientErrorException e) {
            System.err.println("Error deleting employee: " + e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // Inner class to hold Employee with department name
    public static class EmployeeView {

        private Employee employee;
        private String departmentName;

        public EmployeeView(Employee employee, String departmentName) {
            this.employee = employee;
            this.departmentName = departmentName;
        }

        public Employee getEmployee() {
            return employee;
        }

        public String getDepartmentName() {
            return departmentName;
        }
    }
}
