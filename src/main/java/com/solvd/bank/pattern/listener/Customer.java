package com.solvd.bank.pattern.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class Customer implements BankListener {
    private static final Logger LOGGER = (Logger) LogManager.getLogger(Customer.class);
    private String customerName;

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public void onBankNameChanged(String newBankName) {
        LOGGER.info("Customer " + customerName + ", Bank name have changed to " + newBankName);
    }
}
