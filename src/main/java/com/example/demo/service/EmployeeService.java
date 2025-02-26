package com.example.demo.service;
import com.example.demo.entity.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> searchEmployees(String employeeId, String name, String salary_from, String salary_to, String department);
    void deleteEmployeeById(Long id);
}
