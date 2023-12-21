package com.solvd.bank.persistence;

import com.solvd.bank.domain.Customer;

import java.util.List;

public interface CustomerRepository {
    void create(Customer customer);

    void update(Customer customer);

    void deleteById(Long customerId);

    Customer findById(Long customerId);

    List<Customer> findAll();
}