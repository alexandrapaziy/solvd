package com.solvd.bank.domain;

public class BankOperationType {
    private Integer bankOperationTypeId;
    private String bankOperationTypeName;

    public BankOperationType(String bankOperationTypeName) {
        this.bankOperationTypeName = bankOperationTypeName;
    }

    public Integer getBankOperationTypeId() {
        return bankOperationTypeId;
    }

    public void setBankOperationTypeId(Integer bankOperationTypeId) {
        this.bankOperationTypeId = bankOperationTypeId;
    }

    public String getBankOperationTypeName() {
        return bankOperationTypeName;
    }

    public void setBankOperationTypeName(String bankOperationTypeName) {
        this.bankOperationTypeName = bankOperationTypeName;
    }
}