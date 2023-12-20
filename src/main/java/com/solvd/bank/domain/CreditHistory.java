package com.solvd.bank.domain;

import java.time.LocalDateTime;

public class CreditHistory {
    private long paymentId;
    private CreditAgreement creditAgreement;
    private double amount;
    private LocalDateTime date;

    public CreditHistory(CreditAgreement creditAgreement, double amount, LocalDateTime date) {
        this.creditAgreement = creditAgreement;
        this.amount = amount;
        this.date = date;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public CreditAgreement getCreditAgreement() {
        return creditAgreement;
    }

    public void setCreditAgreement(CreditAgreement creditAgreement) {
        this.creditAgreement = creditAgreement;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}