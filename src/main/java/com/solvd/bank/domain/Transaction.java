package com.solvd.bank.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.solvd.bank.adapter.DateAdapter;
import com.solvd.bank.adapter.DateDeserializerAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDateTime;

@XmlRootElement(name = "transaction")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction {
    private Long transactionId;
    private Account account;
    private TransactionType transactionType;
    private double amount;

    @XmlJavaTypeAdapter(DateAdapter.class)
    @JsonDeserialize(using = DateDeserializerAdapter.class)
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