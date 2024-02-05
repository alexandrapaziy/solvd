package com.solvd.bank.pattern.listener;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String bankName;
    private List<Customer> customers;

    public Bank(String bankName) {
        this.bankName = bankName;
        this.customers = new ArrayList<>();
    }

    public String getBankName() {
        return bankName;
    }

    public void setName(String newBankName) {
        if (!this.bankName.equals(newBankName)) {
            this.bankName = newBankName;
            notifyListeners();
        }
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    private void notifyListeners() {
        for (Customer customer : customers) {
            customer.onBankNameChanged(bankName);
        }
    }
}