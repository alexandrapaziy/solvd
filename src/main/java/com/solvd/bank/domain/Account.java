package com.solvd.bank.domain;

public class Account {
    private Long accountId;
    private Customer customer;
    private AccountType accountType;
    private BankBranch bankBranch;
    private double balance;

    public Account() {
    }

    public Account(Customer customer, AccountType accountType, BankBranch bankBranch, double balance) {
        this.customer = customer;
        this.accountType = accountType;
        this.bankBranch = bankBranch;
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public BankBranch getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(BankBranch bankBranch) {
        this.bankBranch = bankBranch;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}