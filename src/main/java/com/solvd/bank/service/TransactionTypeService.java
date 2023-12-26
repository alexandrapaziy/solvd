package com.solvd.bank.service;

import com.solvd.bank.domain.TransactionType;

public interface TransactionTypeService {
    void createTransactionType(TransactionType transactionType);

    void deleteTransactionType(Integer transactionTypeId);

    TransactionType getTransactionTypeById(Integer transactionTypeId);
}