package com.solvd.bank.service;

import com.solvd.bank.domain.BankOperation;
import com.solvd.bank.domain.Employee;

import java.util.List;

public interface BankOperationService {
    void createBankOperation(BankOperation bankOperation, Employee employee);

    void updateBankOperation(BankOperation bankOperation, Employee employee);

    void deleteBankOperation(Integer bankOperationId, Integer employeeId);

    BankOperation getBankOperationById(Integer bankOperationId);

    List<BankOperation> getAllBankOperations();
}