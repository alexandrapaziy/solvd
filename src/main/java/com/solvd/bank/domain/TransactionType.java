package com.solvd.bank.domain;

public class TransactionType {
    private Integer transactionTypeId;
    private String transactionTypeName;

    public TransactionType(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }

    public Integer getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(Integer transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public String getTransactionTypeName() {
        return transactionTypeName;
    }

    public void setTransactionTypeName(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }
}