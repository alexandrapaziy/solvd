package com.solvd.bank.domain;

public class Employee {
    private Integer employeeId;
    private BankBranch bankBranch;
    private Position position;
    private String employeeName;
    private String employeeSurname;
    private String employeePatronymic;
    private String employeePhone;
    private String employeeEmail;

    public Employee(BankBranch bankBranch, Position position, String employeeName, String employeeSurname, String employeePatronymic, String employeePhone, String employeeEmail) {
        this.bankBranch = bankBranch;
        this.position = position;
        this.employeeName = employeeName;
        this.employeeSurname = employeeSurname;
        this.employeePatronymic = employeePatronymic;
        this.employeePhone = employeePhone;
        this.employeeEmail = employeeEmail;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public BankBranch getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(BankBranch bankBranch) {
        this.bankBranch = bankBranch;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeSurname() {
        return employeeSurname;
    }

    public void setEmployeeSurname(String employeeSurname) {
        this.employeeSurname = employeeSurname;
    }

    public String getEmployeePatronymic() {
        return employeePatronymic;
    }

    public void setEmployeePatronymic(String employeePatronymic) {
        this.employeePatronymic = employeePatronymic;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }
}