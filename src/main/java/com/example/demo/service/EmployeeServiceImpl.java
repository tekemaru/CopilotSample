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

                // パラメータ入力がない場合は、フィルタリングを行わない
                // TODO: 社員番号の昇順で並び替えを行う
                return StreamSupport.stream(employeeIterable.spliterator(), false)
                                .filter(e -> employeeId.isEmpty() ||
                                                e.getEmployeeId().toString().contains(employeeId))
                                .filter(e -> name.isEmpty() || e.getEmployeeName().contains(name))
                                .filter(e -> salary_from.isEmpty() || e.getSalary() >= Long.parseLong(salary_from))
                                .filter(e -> salary_to.isEmpty() || e.getSalary() <= Long.parseLong(salary_to))
                                .filter(e -> department.isEmpty() || e.getDepartment().contains(department))
                                .toList();
        }

        // TODO: 社員情報を登録するメソッドを実装する

        // TODO: 社員IDをkeyとして、社員情報を削除するメソッドを実装する

}
