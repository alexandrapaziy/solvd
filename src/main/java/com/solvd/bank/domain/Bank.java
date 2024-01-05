package com.solvd.bank.domain;

public class Bank {
    private Long bankId;
    private String bankName;
    private String bankLocation;
    private String bankPhone;

    public Bank() {
    }

    public Bank(String bankName, String bankLocation, String bankPhone) {
        this.bankName = bankName;
        this.bankLocation = bankLocation;
        this.bankPhone = bankPhone;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankLocation() {
        return bankLocation;
    }

    public void setBankLocation(String bankLocation) {
        bankLocation = bankLocation;
    }

    public String getBankPhone() {
        return bankPhone;
    }

    public void setBankPhone(String bankPhone) {
        bankPhone = bankPhone;
    }
}