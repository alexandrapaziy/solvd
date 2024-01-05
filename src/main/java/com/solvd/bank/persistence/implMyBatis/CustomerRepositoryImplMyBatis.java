package com.solvd.bank.persistence.implMyBatis;

import com.solvd.bank.domain.Customer;
import com.solvd.bank.persistence.CustomerRepository;
import com.solvd.bank.persistence.config.Config;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CustomerRepositoryImplMyBatis implements CustomerRepository {
    @Override
    public void create(Customer customer) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CustomerRepository customerRepository = sqlSession.getMapper(CustomerRepository.class);
            customerRepository.create(customer);
        }
    }

    @Override
    public void update(Customer customer) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CustomerRepository customerRepository = sqlSession.getMapper(CustomerRepository.class);
            customerRepository.update(customer);
        }
    }

    @Override
    public void deleteById(Long customerId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CustomerRepository customerRepository = sqlSession.getMapper(CustomerRepository.class);
            customerRepository.deleteById(customerId);
        }
    }

    @Override
    public Customer findById(Long customerId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CustomerRepository customerRepository = sqlSession.getMapper(CustomerRepository.class);
            return customerRepository.findById(customerId);
        }
    }

    @Override
    public List<Customer> findAll() {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CustomerRepository customerRepository = sqlSession.getMapper(CustomerRepository.class);
            return customerRepository.findAll();
        }
    }
}