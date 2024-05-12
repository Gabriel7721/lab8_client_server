package fpt.aptech.lab8server.controller;

import fpt.aptech.lab8server.entities.Employee;
import fpt.aptech.lab8server.service.IEmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
// http://localhost:9999/api/employee
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    IEmployeeService iEmployeeService;

    @GetMapping("/{code}/{password}")
    @ResponseStatus(HttpStatus.OK)
    public Employee checkLogin(@PathVariable("code") String code, @PathVariable("password") String password) {
        return iEmployeeService.checkLogin(code, password);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> get() {
        return iEmployeeService.getAllEmployees();
    }

    @GetMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    public Employee get(@PathVariable("code") String code) {
        return iEmployeeService.getEmployee(code);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee post(@RequestBody Employee employee) {
        return iEmployeeService.saveEmployee(employee);
    }

    @PutMapping("/save")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Employee put(@RequestBody Employee employee) {
        return iEmployeeService.saveEmployee(employee);
    }

    @DeleteMapping("/delete/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("code") String code) {
        try {
            iEmployeeService.deleteEmployee(code);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
