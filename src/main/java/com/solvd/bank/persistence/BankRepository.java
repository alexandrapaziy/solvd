package com.solvd.bank.persistence;

import com.solvd.bank.domain.Bank;

import java.util.List;

public interface BankRepository {
    void create(Bank bank);

    void update(Bank bank);

    void deleteById(Long bankId);

    Bank findById(Long bankId);

    List<Bank> findAll();
}