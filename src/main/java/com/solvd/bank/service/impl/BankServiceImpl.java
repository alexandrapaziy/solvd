package com.solvd.bank.service.impl;

import com.solvd.bank.domain.Bank;
import com.solvd.bank.persistence.BankRepository;
import com.solvd.bank.service.BankService;

import java.util.List;

public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;

    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public void createBank(Bank bank) {
        bank.setBankId(null);
        bankRepository.create(bank);
    }

    @Override
    public void updateBank(Bank bank) {
        bankRepository.update(bank);
    }

    @Override
    public void deleteBank(Long bankId) {
        bankRepository.deleteById(bankId);
    }

    @Override
    public Bank getBankById(Long bankId) {
        return bankRepository.findById(bankId);
    }

    @Override
    public List<Bank> getAllABanks() {
        return bankRepository.findAll();
    }
}