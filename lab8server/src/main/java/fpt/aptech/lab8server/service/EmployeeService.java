package fpt.aptech.lab8server.service;

import fpt.aptech.lab8server.entities.Employee;
import fpt.aptech.lab8server.repository.EmployeeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee checkLogin(String code, String password) {
        Employee employee = employeeRepository.checkLogin(code, password);
        if (employee == null || employee.getIsactive() == null || !employee.getIsactive()) {
            return null;
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(String code) {
        return employeeRepository.findOne(code);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(String code) {
        Employee employee = employeeRepository.findById(code).get();
        employeeRepository.delete(employee);
    }

}
