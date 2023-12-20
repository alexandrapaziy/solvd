package com.solvd.bank.domain;

public class TransactionType {
    private int transactionTypeId;
    private String transactionTypeName;

    public TransactionType(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }

    public int getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(int transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public String getTransactionTypeName() {
        return transactionTypeName;
    }

    public void setTransactionTypeName(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }
}