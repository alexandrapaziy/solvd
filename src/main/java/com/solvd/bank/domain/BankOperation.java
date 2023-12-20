package com.solvd.bank.domain;

import java.time.LocalDateTime;

public class BankOperation {
    private int bankOperationId;
    private BankOperationType bankOperationType;
    private Account account;
    private Employee employee;
    private LocalDateTime date;

    public BankOperation(BankOperationType bankOperationType, Account account, Employee employee, LocalDateTime date) {
        this.bankOperationType = bankOperationType;
        this.account = account;
        this.employee = employee;
        this.date = date;
    }

    public int getBankOperationId() {
        return bankOperationId;
    }

    public void setBankOperationId(int bankOperationId) {
        this.bankOperationId = bankOperationId;
    }

    public BankOperationType getBankOperationType() {
        return bankOperationType;
    }

    public void setBankOperationType(BankOperationType bankOperationType) {
        this.bankOperationType = bankOperationType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}