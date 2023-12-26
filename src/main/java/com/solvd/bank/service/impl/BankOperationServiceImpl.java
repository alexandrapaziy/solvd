package com.solvd.bank.service.impl;

import com.solvd.bank.domain.*;
import com.solvd.bank.domain.exception.ServiceException;
import com.solvd.bank.persistence.BankOperationRepository;
import com.solvd.bank.service.AccountService;
import com.solvd.bank.service.BankOperationService;
import com.solvd.bank.service.BankOperationTypeService;

import java.util.List;

public class BankOperationServiceImpl implements BankOperationService {
    private final BankOperationRepository bankOperationRepository;
    private final BankOperationTypeService bankOperationTypeService;
    private final AccountService accountService;

    public BankOperationServiceImpl(BankOperationRepository bankOperationRepository,
                                    BankOperationTypeService bankOperationTypeService, AccountService accountService) {
        this.bankOperationRepository = bankOperationRepository;
        this.bankOperationTypeService = bankOperationTypeService;
        this.accountService = accountService;
    }

    @Override
    public void createBankOperation(BankOperation bankOperation, Employee employee) {
        try {
            if (bankOperation.getBankOperationType() != null && bankOperation.getAccount() != null &&
                    employee != null) {
                BankOperationType bankOperationType = bankOperationTypeService.getBankOperationTypeById(bankOperation.getBankOperationType().getBankOperationTypeId());
                Account account = accountService.getAccountById(bankOperation.getAccount().getAccountId());

                bankOperation.setBankOperationType(bankOperationType);
                bankOperation.setAccount(account);
            }

            bankOperationRepository.create(bankOperation, employee);
            bankOperationRepository.addEmployeeForBankOperation(bankOperation, employee);
        } catch (Exception e) {
            throw new ServiceException("Failed to create bank operation", e);
        }
    }

    @Override
    public void updateBankOperation(BankOperation bankOperation, Employee employee) {
        try {
            if (bankOperation.getBankOperationType() != null && bankOperation.getAccount() != null &&
                    employee != null) {
                BankOperationType bankOperationType = bankOperationTypeService.getBankOperationTypeById(bankOperation.getBankOperationType().getBankOperationTypeId());
                Account account = accountService.getAccountById(bankOperation.getAccount().getAccountId());

                bankOperation.setBankOperationType(bankOperationType);
                bankOperation.setAccount(account);

                Integer currentEmployeeId = bankOperation.getEmployee().getEmployeeId();
                bankOperationRepository.removeEmployeeForBankOperation(currentEmployeeId, bankOperation.getBankOperationId());
                bankOperationRepository.addEmployeeForBankOperation(bankOperation, employee);
            }


            bankOperationRepository.update(bankOperation, employee);
        } catch (Exception e) {
            throw new ServiceException("Failed to update bank operation", e);
        }
    }

    @Override
    public void deleteBankOperation(Integer bankOperationId, Integer employeeId) {
        try {
            bankOperationRepository.removeEmployeeForBankOperation(bankOperationId, employeeId);
            bankOperationRepository.deleteById(bankOperationId, employeeId);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete bank operation", e);
        }
    }

    @Override
    public BankOperation getBankOperationById(Integer bankOperationId) {
        try {
            return bankOperationRepository.findById(bankOperationId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get bank operation by id", e);
        }
    }

    @Override
    public List<BankOperation> getAllBankOperations() {
        try {
            return bankOperationRepository.findAll();
        } catch (Exception e) {
            throw new ServiceException("Failed to get all bank operations", e);
        }
    }

}