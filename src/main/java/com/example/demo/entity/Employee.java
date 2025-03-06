package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "password")
    private String password;

    @Column(name = "salary")
    private long salary;

    @Column(name = "department")
    private String department;

    public Employee() {
    }

    public Employee(Long employeeId, String employeeName, String password, long salary, String department) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.password = password;
        this.salary = salary;
        this.department = department;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getPassword() {
        return password;
    }

    public long getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
