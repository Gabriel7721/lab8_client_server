package fpt.aptech.lab8client.models;

public class Employee {

    private String code;
    private String name;
    private String password;
    private String skill;
    private Double salary;
    private Boolean isactive;
    private Department department;

    public Employee() {
    }

    public Employee(String code, String name, String password, String skill, Double salary, Boolean isactive, Department department) {
        this.code = code;
        this.name = name;
        this.password = password;
        this.skill = skill;
        this.salary = salary;
        this.isactive = isactive;
        this.department = department;
    }

    public Employee(String code) {
        this.code = code;
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
