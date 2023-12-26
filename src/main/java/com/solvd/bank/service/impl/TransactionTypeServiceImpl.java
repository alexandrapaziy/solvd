package com.solvd.bank.service.impl;

import com.solvd.bank.domain.TransactionType;
import com.solvd.bank.domain.exception.ServiceException;
import com.solvd.bank.persistence.TransactionTypeRepository;
import com.solvd.bank.service.TransactionTypeService;

public class TransactionTypeServiceImpl implements TransactionTypeService {
    private final TransactionTypeRepository transactionTypeRepository;

    public TransactionTypeServiceImpl(TransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @Override
    public void createTransactionType(TransactionType transactionType) {
        try {
            transactionTypeRepository.create(transactionType);
        } catch (Exception e) {
            throw new ServiceException("Failed to create transaction type", e);
        }
    }

    @Override
    public void deleteTransactionType(Integer transactionTypeId) {
        try {
            transactionTypeRepository.deleteById(transactionTypeId);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete transaction type", e);
        }
    }

    @Override
    public TransactionType getTransactionTypeById(Integer transactionTypeId) {
        try {
            return transactionTypeRepository.findById(transactionTypeId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get transaction type by id", e);
        }
    }
}