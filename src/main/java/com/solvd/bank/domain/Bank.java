package com.solvd.bank.domain;

public class Bank {
    private long bankId;
    private String bankName;
    private String BankLocation;
    private String BankPhone;

    public Bank(String bankName, String bankLocation, String bankPhone) {
        this.bankName = bankName;
        BankLocation = bankLocation;
        BankPhone = bankPhone;
    }

    public long getBankId() {
        return bankId;
    }

    public void setBankId(long bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankLocation() {
        return BankLocation;
    }

    public void setBankLocation(String bankLocation) {
        BankLocation = bankLocation;
    }

    public String getBankPhone() {
        return BankPhone;
    }

    public void setBankPhone(String bankPhone) {
        BankPhone = bankPhone;
    }
}