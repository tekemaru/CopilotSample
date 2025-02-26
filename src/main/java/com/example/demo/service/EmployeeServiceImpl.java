package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;


    public List<Employee> searchEmployees(String employeeId, String name, String salary_from, String salary_to, String department) {
        // すべての社員データを取得
        Iterable<Employee> employeeIterable = employeeRepository.findAll();
        List<Employee> employees = StreamSupport.stream(employeeIterable.spliterator(), false)
                .toList();

        // パラメータ入力がない場合は、フィルタリングを行わない
        return employees.stream()
                .filter(e -> (employeeId.isEmpty()  || e.getEmployeeId().toString().contains(employeeId)))
                .filter(e -> (name.isEmpty() || e.getEmployeeName().contains(name)))
                .filter(e -> (salary_from.isEmpty() || e.getSalary() >= Long.parseLong(salary_from)))
                .filter(e -> (salary_to.isEmpty() || e.getSalary() <= Long.parseLong(salary_to)))
                .filter(e -> (department.isEmpty() || e.getDepartment().contains(department)))
                .toList();
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}
