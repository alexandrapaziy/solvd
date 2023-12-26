package com.solvd.bank.service.impl;

import com.solvd.bank.domain.Bank;
import com.solvd.bank.domain.exception.ServiceException;
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
        try {
            bankRepository.create(bank);
        } catch (Exception e) {
            throw new ServiceException("Failed to create bank", e);
        }
    }

    @Override
    public void updateBank(Bank bank) {
        try {
            bankRepository.update(bank);
        } catch (Exception e) {
            throw new ServiceException("Failed to update bank", e);
        }
    }

    @Override
    public void deleteBank(Long bankId) {
        try {
            bankRepository.deleteById(bankId);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete bank", e);
        }
    }

    @Override
    public Bank getBankById(Long bankId) {
        try {
            return bankRepository.findById(bankId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get bank by id", e);
        }
    }

    @Override
    public List<Bank> getAllABanks() {
        try {
            return bankRepository.findAll();
        } catch (Exception e) {
            throw new ServiceException("Failed to get all banks", e);
        }
    }
}