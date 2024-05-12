package fpt.aptech.lab8client.dto;

import fpt.aptech.lab8client.models.Department;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EmployeeDTO {

    @NotEmpty(message = "Code is required...")
    private String code;

    @NotEmpty(message = "Name is required...")
    @Size(min = 2, max = 100, message = "Name from 2 to 100 characters...")
    private String name;

    @NotEmpty(message = "Password is required...")
    @Size(min = 8, message = "Password should be at least 8 characters long...")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$", message = "Password must contain at least one letter and one number...")
    private String password;

    @NotEmpty(message = "Skill is required...")
    @Size(min = 2, max = 50, message = "Skill should be between 2 and 50 characters...")
    private String skill;

    @NotNull(message = "Salary is required...")
    @Min(value = 100, message = "Min salary is $100")
    @Max(value = 5000, message = "Max salary is $5000")
    private Double salary;

    private Boolean isactive;

    @NotNull(message = "Department ID is required...")
    private Department department;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String code, String name, String password, String skill, Double salary, Boolean isactive, Department department) {
        this.code = code;
        this.name = name;
        this.password = password;
        this.skill = skill;
        this.salary = salary;
        this.isactive = isactive;
        this.department = department;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

}
