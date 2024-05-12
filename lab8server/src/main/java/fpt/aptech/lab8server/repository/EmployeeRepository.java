package fpt.aptech.lab8server.repository;

import fpt.aptech.lab8server.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    @Query("SELECT e FROM Employee e WHERE e.code = :code AND e.password = :password AND e.isactive = true")
    Employee checkLogin(@PathVariable("code") String code, @PathVariable("password") String password);

    @Query("SELECT e FROM Employee e WHERE e.code = :code")
    Employee findOne(@PathVariable("code") String code);
}
