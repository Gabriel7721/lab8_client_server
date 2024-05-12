package fpt.aptech.lab8server.service;

import fpt.aptech.lab8server.entities.Department;
import java.util.List;

public interface IDepartmentService {

    List<Department> getAllDepartments();

    Department getDepartmentById(Integer id);
}
