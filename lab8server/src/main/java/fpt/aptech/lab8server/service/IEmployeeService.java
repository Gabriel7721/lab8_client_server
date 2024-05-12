package fpt.aptech.lab8server.service;

import fpt.aptech.lab8server.entities.Employee;
import java.util.List;

public interface IEmployeeService {

    Employee checkLogin(String code, String password);

    List<Employee> getAllEmployees();

    Employee getEmployee(String code);

    Employee saveEmployee(Employee employee);

    void deleteEmployee(String code);
}
