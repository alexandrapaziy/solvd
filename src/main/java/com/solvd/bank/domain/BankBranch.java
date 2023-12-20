package com.solvd.bank.domain;

public class BankBranch {
    private int bankBranchId;
    private Bank bank;
    private String bankBranchLocation;
    private String bankBranchPhone;

    public BankBranch(Bank bank, String bankBranchLocation, String bankBranchPhone) {
        this.bank = bank;
        this.bankBranchLocation = bankBranchLocation;
        this.bankBranchPhone = bankBranchPhone;
    }

    public int getBankBranchId() {
        return bankBranchId;
    }

    public void setBankBranchId(int bankBranchId) {
        this.bankBranchId = bankBranchId;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public String getBankBranchLocation() {
        return bankBranchLocation;
    }

    public void setBankBranchLocation(String bankBranchLocation) {
        this.bankBranchLocation = bankBranchLocation;
    }

    public String getBankBranchPhone() {
        return bankBranchPhone;
    }

    public void setBankBranchPhone(String bankBranchPhone) {
        this.bankBranchPhone = bankBranchPhone;
    }
}