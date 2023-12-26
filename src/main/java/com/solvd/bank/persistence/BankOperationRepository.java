package com.solvd.bank.persistence;

import com.solvd.bank.domain.BankOperation;
import com.solvd.bank.domain.Employee;

import java.util.List;

public interface BankOperationRepository {
    void create(BankOperation bankOperation, Employee employee);

    void update(BankOperation bankOperation, Employee employee);

    void deleteById(Integer bankOperationId, Integer employeeId);

    BankOperation findById(Integer bankOperationId);

    List<BankOperation> findAll();

    // many-to-many

    void addEmployeeForBankOperation(BankOperation bankOperation, Employee employee);

    void removeEmployeeForBankOperation(Integer bankOperationId, Integer employeeId);

    Employee getCurrentEmployee(Integer bankOperationId);
}