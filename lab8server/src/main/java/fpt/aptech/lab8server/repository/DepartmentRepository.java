package fpt.aptech.lab8server.repository;

import fpt.aptech.lab8server.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
