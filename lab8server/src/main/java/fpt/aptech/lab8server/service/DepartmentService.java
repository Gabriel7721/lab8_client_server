package fpt.aptech.lab8server.service;

import fpt.aptech.lab8server.entities.Department;
import fpt.aptech.lab8server.repository.DepartmentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Integer id) {
        Optional<Department> department = departmentRepository.findById(id);
        return department.orElse(null);
    }

}
