package com.solvd.bank.domain;

public class BankOperationType {
    private int bankOperationTypeId;
    private String bankOperationTypeName;

    public BankOperationType(String bankOperationTypeName) {
        this.bankOperationTypeName = bankOperationTypeName;
    }

    public int getBankOperationTypeId() {
        return bankOperationTypeId;
    }

    public void setBankOperationTypeId(int bankOperationTypeId) {
        this.bankOperationTypeId = bankOperationTypeId;
    }

    public String getBankOperationTypeName() {
        return bankOperationTypeName;
    }

    public void setBankOperationTypeName(String bankOperationTypeName) {
        this.bankOperationTypeName = bankOperationTypeName;
    }
}