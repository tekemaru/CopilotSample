package com.example.demo.api;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeServiceImpl employeeService;

    @Test
    public void testDeleteEmployee() throws Exception {
        Long employeeId = 1L;
        doNothing().when(employeeService).deleteEmployeeById(employeeId);

        mockMvc.perform(delete("/api/employees/{employeeId}", employeeId))
                .andExpect(status().isOk());

        verify(employeeService).deleteEmployeeById(employeeId);
    }

    @Test
    public void testGetEmployeesTable() throws Exception {
        Employee employee1 = new Employee(1L, "John Doe", "password1", 50000, "Engineering");
        Employee employee2 = new Employee(2L, "Jane Smith", "password2", 60000, "Marketing");
        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeService.searchEmployees(null, null, null, null, null)).thenReturn(employees);

        String expectedHtml = "<tr id='employee-1'><td>1</td><td>John Doe</td><td>50000</td><td>Engineering</td><td>"
                + "<button class='btn btn-sm btn-danger' hx-delete='/api/employees/1' hx-swap='outerHTML:beforeend' hx-target='closest tr'>削除</button>"
                + "</td></tr>"
                + "<tr id='employee-2'><td>2</td><td>Jane Smith</td><td>60000</td><td>Marketing</td><td>"
                + "<button class='btn btn-sm btn-danger' hx-delete='/api/employees/2' hx-swap='outerHTML:beforeend' hx-target='closest tr'>削除</button>"
                + "</td></tr>";

        mockMvc.perform(get("/api/employees/table"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedHtml));
    }
}