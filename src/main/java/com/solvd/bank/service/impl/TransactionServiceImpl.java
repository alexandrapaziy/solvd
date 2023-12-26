package com.solvd.bank.service.impl;

import com.solvd.bank.domain.*;
import com.solvd.bank.domain.exception.ServiceException;
import com.solvd.bank.persistence.TransactionRepository;
import com.solvd.bank.service.*;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final TransactionTypeService transactionTypeService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountService accountService,
                                  TransactionTypeService transactionTypeService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.transactionTypeService = transactionTypeService;
    }

    @Override
    public void createTransaction(Transaction transaction) {
        try {
            if (transaction.getAccount() != null && transaction.getTransactionType() != null) {
                Account account = accountService.getAccountById(transaction.getAccount().getAccountId());
                TransactionType transactionType = transactionTypeService.getTransactionTypeById(transaction.getTransactionType().getTransactionTypeId());

                transaction.setAccount(account);
                transaction.setTransactionType(transactionType);
            }

            transactionRepository.create(transaction);
        } catch (Exception e) {
            throw new ServiceException("Failed to create transaction", e);
        }
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        try {
            if (transaction.getAccount() != null && transaction.getTransactionType() != null) {
                Account account = accountService.getAccountById(transaction.getAccount().getAccountId());
                TransactionType transactionType = transactionTypeService.getTransactionTypeById(transaction.getTransactionType().getTransactionTypeId());

                transaction.setAccount(account);
                transaction.setTransactionType(transactionType);
            }

            transactionRepository.update(transaction);
        } catch (Exception e) {
            throw new ServiceException("Failed to update transaction", e);
        }
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        try {
            transactionRepository.deleteById(transactionId);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete transaction", e);
        }
    }

    @Override
    public Transaction getTransactionById(Long transactionId) {
        try {
            return transactionRepository.findById(transactionId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get transaction by id", e);
        }
    }

    @Override
    public List<Transaction> getAllTransactions() {
        try {
            return transactionRepository.findAll();
        } catch (Exception e) {
            throw new ServiceException("Failed to get all transaction", e);
        }
    }
}