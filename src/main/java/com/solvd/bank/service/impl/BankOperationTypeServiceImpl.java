package com.solvd.bank.service.impl;

import com.solvd.bank.domain.BankOperationType;
import com.solvd.bank.domain.exception.ServiceException;
import com.solvd.bank.persistence.BankOperationTypeRepository;
import com.solvd.bank.service.BankOperationTypeService;

public class BankOperationTypeServiceImpl implements BankOperationTypeService {
    private final BankOperationTypeRepository bankOperationTypeRepository;

    public BankOperationTypeServiceImpl(BankOperationTypeRepository bankOperationTypeRepository) {
        this.bankOperationTypeRepository = bankOperationTypeRepository;
    }

    @Override
    public void createBankOperationType(BankOperationType bankOperationType) {
        try {
            bankOperationTypeRepository.create(bankOperationType);
        } catch (Exception e) {
            throw new ServiceException("Failed to create bank operation type", e);
        }
    }

    @Override
    public void deleteBankOperationType(Integer bankOperationTypeId) {
        try {
            bankOperationTypeRepository.deleteById(bankOperationTypeId);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete bank operation type", e);
        }
    }

    @Override
    public BankOperationType getBankOperationTypeById(Integer bankOperationTypeId) {
        try {
            return bankOperationTypeRepository.findById(bankOperationTypeId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get bank operation type by id", e);
        }
    }
}