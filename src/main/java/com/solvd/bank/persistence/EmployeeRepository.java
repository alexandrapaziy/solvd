package com.solvd.bank.persistence;

import com.solvd.bank.domain.Employee;

import java.util.List;

public interface EmployeeRepository {
    void create(Employee employee);

    void update(Employee employee);

    void deleteById(Integer employeeId);

    Employee findById(Integer employeeId);

    List<Employee> findAll();
}