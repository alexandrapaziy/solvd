package com.solvd.bank.service;

import com.solvd.bank.domain.Bank;

import java.util.List;

public interface BankService {
    void createBank(Bank bank);

    void updateBank(Bank bank);

    void deleteBank(Long bankId);

    Bank getBankById(Long bankId);

    List<Bank> getAllABanks();
}