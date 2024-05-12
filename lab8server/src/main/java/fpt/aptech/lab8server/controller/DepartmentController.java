package fpt.aptech.lab8server.controller;

import fpt.aptech.lab8server.entities.Department;
import fpt.aptech.lab8server.service.IDepartmentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
// http://localhost:9999/api/department
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    IDepartmentService iDepartmentService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Department> get() {
        return iDepartmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Department getDepartmentById(@PathVariable Integer id) {
        return iDepartmentService.getDepartmentById(id);
    }
}
