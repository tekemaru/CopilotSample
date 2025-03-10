package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Employee;

public interface EmployeeService {
    // 社員情報を検索するメソッド
    List<Employee> searchEmployees(String employeeId, String name, String salary_from, String salary_to,
            String department);

}
