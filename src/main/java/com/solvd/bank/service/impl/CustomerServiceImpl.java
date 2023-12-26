package com.solvd.bank.service.impl;

import com.solvd.bank.domain.Customer;
import com.solvd.bank.domain.exception.ServiceException;
import com.solvd.bank.persistence.CustomerRepository;
import com.solvd.bank.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void createCustomer(Customer customer) {
        try {
            customerRepository.create(customer);
        } catch (Exception e) {
            throw new ServiceException("Failed to create customer", e);
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        try {
            customerRepository.update(customer);
        } catch (Exception e) {
            throw new ServiceException("Failed to update customer", e);
        }
    }

    @Override
    public void deleteCustomer(Long customerId) {
        try {
            customerRepository.deleteById(customerId);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete customer", e);
        }
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        try {
            return customerRepository.findById(customerId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get customer by id", e);
        }
    }

    @Override
    public List<Customer> getAllACustomer() {
        try {
            return customerRepository.findAll();
        } catch (Exception e) {
            throw new ServiceException("Failed to get all customers", e);
        }
    }
}