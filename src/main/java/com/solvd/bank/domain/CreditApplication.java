package com.solvd.bank.domain;

import java.time.LocalDate;

public class CreditApplication {
    private Long creditApplicationId;
    private Customer customer;
    private CreditApplicationStatus creditApplicationStatus;
    private LocalDate date;

    public CreditApplication() {
    }

    public CreditApplication(Customer customer, CreditApplicationStatus creditApplicationStatus, LocalDate date) {
        this.customer = customer;
        this.creditApplicationStatus = creditApplicationStatus;
        this.date = date;
    }

    public Long getCreditApplicationId() {
        return creditApplicationId;
    }

    public void setCreditApplicationId(Long creditApplicationId) {
        this.creditApplicationId = creditApplicationId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CreditApplicationStatus getCreditApplicationStatus() {
        return creditApplicationStatus;
    }

    public void setCreditApplicationStatus(CreditApplicationStatus creditApplicationStatus) {
        this.creditApplicationStatus = creditApplicationStatus;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}