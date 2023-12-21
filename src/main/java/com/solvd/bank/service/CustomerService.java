package com.solvd.bank.service;

import com.solvd.bank.domain.Customer;

import java.util.List;

public interface CustomerService {
    void createCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(Long customerId);

    Customer getCustomerById(Long customerId);

    List<Customer> getAllACustomer();
}