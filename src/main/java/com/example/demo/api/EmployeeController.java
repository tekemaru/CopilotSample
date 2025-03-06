package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @GetMapping("/table")
    @ResponseBody
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

    @DeleteMapping("/{employeeId}")
    @ResponseBody
    public void deleteEmployee(@PathVariable String employeeId) {
        Long id = Long.parseLong(employeeId);
        employeeService.deleteEmployeeById(id);
    }

    /**
     * 整列ボタン押下時に呼び出されるエンドポイント
     * 技術部、マーケティング部、営業部、人事部、経理部の順に並べ替える
     */
    @GetMapping("/lineUp")
    @ResponseBody
    public String lineUpEmployees() {
        List<Employee> employees = employeeService.sortEmployeesByDepartment();
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
}