package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee API", description = "社員情報の管理API")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @GetMapping("/table")
    @ResponseBody
    @Operation(summary = "社員情報の検索", description = "引数を検索条件として、社員情報を検索するエンドポイント")
    public String getEmployeesTable(
            @RequestParam(required = false) String employeeId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String salary_from,
            @RequestParam(required = false) String salary_to,
            @RequestParam(required = false) String department) {
        List<Employee> employees = employeeService.searchEmployees(employeeId, name, salary_from, salary_to,
                department);
        StringBuilder sb = new StringBuilder();

        for (Employee employee : employees) {
            sb.append("<tr id='employee-").append(employee.getEmployeeId()).append("'>");
            sb.append("<td>").append(employee.getEmployeeId()).append("</td>");
            sb.append("<td>").append(employee.getEmployeeName()).append("</td>");
            sb.append("<td>").append(employee.getSalary()).append("</td>");
            sb.append("<td>").append(employee.getDepartment()).append("</td>");
            sb.append("<td>")
                    .append("<button class='btn btn-sm btn-danger' ")
                    .append("hx-delete='/api/employees/").append(employee.getEmployeeId()).append("' ")
                    .append("hx-swap='outerHTML:beforeend' ")
                    .append("hx-target='closest tr'>")
                    .append("削除")
                    .append("</button>")
                    .append("</td>");
            sb.append("</tr>");
        }
        return sb.toString();
    }

    // TODO: 社員情報を削除するエンドポイントを作成する

    // TODO: 社員情報を登録するエンドポイントを作成する

}