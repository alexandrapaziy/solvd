package com.solvd.bank.service;

import com.solvd.bank.domain.Transaction;

import java.util.List;

public interface TransactionService {
    void createTransaction(Transaction transaction);

    void updateTransaction(Transaction transaction);

    void deleteTransaction(Long transactionId);

    Transaction getTransactionById(Long transactionId);

    List<Transaction> getAllTransactions();
}