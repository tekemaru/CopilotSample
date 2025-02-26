package com.example.demo.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.EmployeeServiceImpl;
import com.example.demo.entity.Employee;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

//    @GetMapping("/search")
//    public List<Employee> searchEmployees(
//            @RequestParam(required = false) String employeeId,
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String salary_from,
//            @RequestParam(required = false) String salary_to,
//            @RequestParam(required = false) String department) {
//        return employeeService.searchEmployees(employeeId, name, salary_from, salary_to, department);
//    }

    @GetMapping("/table")
    public String getEmployeesTable(
            @RequestParam(required = false) String employeeId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String salary_from,
            @RequestParam(required = false) String salary_to,
            @RequestParam(required = false) String department) {
        List<Employee> employees = employeeService.searchEmployees(employeeId, name, salary_from, salary_to, department);
        StringBuilder sb = new StringBuilder();

        for (Employee employee : employees) {
            sb.append("<tr>");
            sb.append("<td>").append(employee.getEmployeeId()).append("</td>");
            sb.append("<td>").append(employee.getEmployeeName()).append("</td>");
            sb.append("<td>").append(employee.getSalary()).append("</td>");
            sb.append("<td>").append(employee.getDepartment()).append("</td>");
            sb.append("</tr>");
        }
        return sb.toString();
    }
}