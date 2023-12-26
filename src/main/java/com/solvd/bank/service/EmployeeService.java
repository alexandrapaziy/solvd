package com.solvd.bank.service;

import com.solvd.bank.domain.Employee;

import java.util.List;

public interface EmployeeService {
    void createEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(Integer employeeId);

    Employee getEmployeeById(Integer employeeId);

    List<Employee> getAllEmployees();
}