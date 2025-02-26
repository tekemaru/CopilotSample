package com.example.demo.service;
import com.example.demo.entity.Employee;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public List<Employee> searchEmployees(String employeeId, String name, String salary_from, String salary_to, String department);

    public void deleteEmployeeById(Long id);
}
