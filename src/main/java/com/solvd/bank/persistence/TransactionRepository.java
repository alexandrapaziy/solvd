package com.solvd.bank.persistence;

import com.solvd.bank.domain.Transaction;

import java.util.List;

public interface TransactionRepository {
    void create(Transaction transaction);

    void update(Transaction transaction);

    void deleteById(Long transactionId);

    Transaction findById(Long transactionId);

    List<Transaction> findAll();
}