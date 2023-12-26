package com.solvd.bank.service.impl;

import com.solvd.bank.domain.*;
import com.solvd.bank.domain.exception.ServiceException;
import com.solvd.bank.persistence.EmployeeRepository;
import com.solvd.bank.service.*;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final BankBranchService bankBranchService;
    private final PositionService positionService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, BankBranchService bankBranchService,
                               PositionService positionService) {
        this.employeeRepository = employeeRepository;
        this.bankBranchService = bankBranchService;
        this.positionService = positionService;
    }

    @Override
    public void createEmployee(Employee employee) {
        try {
            if (employee.getBankBranch() != null && employee.getPosition() != null) {
                BankBranch bankBranch = bankBranchService.getBankBranchById(employee.getBankBranch().getBankBranchId());
                Position position = positionService.getPositionById(employee.getPosition().getPositionId());

                employee.setBankBranch(bankBranch);
                employee.setPosition(position);
            }

            employeeRepository.create(employee);
        } catch (Exception e) {
            throw new ServiceException("Failed to create employee", e);
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        try {
            if (employee.getBankBranch() != null && employee.getPosition() != null) {
                BankBranch bankBranch = bankBranchService.getBankBranchById(employee.getBankBranch().getBankBranchId());
                Position position = positionService.getPositionById(employee.getPosition().getPositionId());

                employee.setBankBranch(bankBranch);
                employee.setPosition(position);
            }

            employeeRepository.update(employee);
        } catch (Exception e) {
            throw new ServiceException("Failed to update employee", e);
        }
    }

    @Override
    public void deleteEmployee(Integer employeeId) {
        try {
            employeeRepository.deleteById(employeeId);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete employee", e);
        }
    }

    @Override
    public Employee getEmployeeById(Integer employeeId) {
        try {
            return employeeRepository.findById(employeeId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get employee by id", e);
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try {
            return employeeRepository.findAll();
        } catch (Exception e) {
            throw new ServiceException("Failed to get all employees", e);
        }
    }
}