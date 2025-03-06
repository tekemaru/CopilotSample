package com.example.demo.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    /**
     * 社員情報を検索して、社員IDの昇順に並べ替えるメソッド
     * 
     */
    public List<Employee> searchEmployees(String employeeId, String name, String salary_from, String salary_to,
            String department) {
        // すべての社員データを取得
        Iterable<Employee> employeeIterable = employeeRepository.findAll();
        List<Employee> employees = StreamSupport.stream(employeeIterable.spliterator(), false)
                .toList();

        // パラメータ入力がない場合は、フィルタリングを行わない
        // TODO: 社員番号の昇順で並び替えを行う
        return employees.stream()
                .filter(e -> (employeeId.isEmpty() || e.getEmployeeId().toString().contains(employeeId)))
                .filter(e -> (name.isEmpty() || e.getEmployeeName().contains(name)))
                .filter(e -> (salary_from.isEmpty() || e.getSalary() >= Long.parseLong(salary_from)))
                .filter(e -> (salary_to.isEmpty() || e.getSalary() <= Long.parseLong(salary_to)))
                .filter(e -> (department.isEmpty() || e.getDepartment().contains(department)))
                .toList();
    }

    /**
     * 社員を部署ごとに整列するメソッド
     * 技術部、マーケティング部、営業部、人事部、経理部の順に並べ替える
     */
    public List<Employee> sortEmployeesByDepartment() {
        // すべての社員データを取得
        Iterable<Employee> employeeIterable = employeeRepository.findAll();
        List<Employee> employees = StreamSupport.stream(employeeIterable.spliterator(), false)
                .toList();
        return employees.stream()
                .sorted((e1, e2) -> {
                    String department1 = e1.getDepartment();
                    String department2 = e2.getDepartment();
                    if (department1.equals(department2)) {
                        return 0;
                    }
                    // 部署名を比較して、技術部、マーケティング部、営業部、人事部、経理部の順に並べ替える
                    List<String> departmentOrder = List.of("技術部", "マーケティング部", "営業部", "人事部", "経理部");
                    return Integer.compare(departmentOrder.indexOf(department1), departmentOrder.indexOf(department2));
                })
                .toList();
    }

    /**
     * 社員情報を削除するメソッド
     * 
     */
    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}
