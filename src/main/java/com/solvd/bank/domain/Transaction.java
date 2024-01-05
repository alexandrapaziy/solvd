package com.solvd.bank.domain;

import java.time.LocalDateTime;

public class Transaction {
    private Long transactionId;
    private Account account;
    private TransactionType transactionType;
    private double amount;
    private LocalDateTime date;

    public Transaction() {
    }

    public Transaction(Account account, TransactionType transactionType, double amount, LocalDateTime date) {
        this.account = account;
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = date;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}