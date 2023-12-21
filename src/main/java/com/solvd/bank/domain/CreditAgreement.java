package com.solvd.bank.domain;

public class CreditAgreement {
    private long creditAgreementId;
    private CreditApplication creditApplication;
    private CreditAgreementStatus creditAgreementStatus;
    private double amount;
    private int interest;
    private double oneTimePayment;

    public CreditAgreement(CreditApplication creditApplication, CreditAgreementStatus creditAgreementStatus, double amount, int interest, double oneTimePayment) {
        this.creditApplication = creditApplication;
        this.creditAgreementStatus = creditAgreementStatus;
        this.amount = amount;
        this.interest = interest;
        this.oneTimePayment = oneTimePayment;
    }

    public long getCreditAgreementId() {
        return creditAgreementId;
    }

    public void setCreditAgreementId(long creditAgreementId) {
        this.creditAgreementId = creditAgreementId;
    }

    public CreditApplication getCreditApplication() {
        return creditApplication;
    }

    public void setCreditApplication(CreditApplication creditApplication) {
        this.creditApplication = creditApplication;
    }

    public CreditAgreementStatus getCreditAgreementStatus() {
        return creditAgreementStatus;
    }

    public void setCreditAgreementStatus(CreditAgreementStatus creditAgreementStatus) {
        this.creditAgreementStatus = creditAgreementStatus;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getInterest() {
        return interest;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }

    public double getOneTimePayment() {
        return oneTimePayment;
    }

    public void setOneTimePayment(double oneTimePayment) {
        this.oneTimePayment = oneTimePayment;
    }
}