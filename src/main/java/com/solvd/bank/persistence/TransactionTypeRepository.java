package com.solvd.bank.persistence;


import com.solvd.bank.domain.TransactionType;

public interface TransactionTypeRepository {
    void create(TransactionType transactionType);

    void deleteById(Integer transactionTypeId);

    TransactionType findById(Integer transactionTypeId);
}