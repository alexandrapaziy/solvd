package com.solvd.bank.service.impl;

import com.solvd.bank.domain.Bank;
import com.solvd.bank.domain.BankBranch;
import com.solvd.bank.domain.exception.ServiceException;
import com.solvd.bank.persistence.BankBranchRepository;
import com.solvd.bank.service.BankBranchService;
import com.solvd.bank.service.BankService;

import java.util.List;

public class BankBranchServiceImpl implements BankBranchService {
    private final BankBranchRepository bankBranchRepository;
    private final BankService bankService;

    public BankBranchServiceImpl(BankBranchRepository bankBranchRepository, BankService bankService) {
        this.bankBranchRepository = bankBranchRepository;
        this.bankService = bankService;
    }

    @Override
    public void createBankBranch(BankBranch bankBranch) {
        try {
            if (bankBranch.getBank() != null) {
                Bank bank = bankService.getBankById(bankBranch.getBank().getBankId());
                bankBranch.setBank(bank);
            }

            bankBranchRepository.create(bankBranch);
        } catch (Exception e) {
            throw new ServiceException("Failed to create bank branch", e);
        }
    }

    @Override
    public void updateBankBranch(BankBranch bankBranch) {
        try {
            if (bankBranch.getBank() != null) {
                Bank bank = bankService.getBankById(bankBranch.getBank().getBankId());
                bankBranch.setBank(bank);
            }

            bankBranchRepository.update(bankBranch);
        } catch (Exception e) {
            throw new ServiceException("Failed to update bank branch", e);
        }
    }

    @Override
    public void deleteBankBranch(Integer bankBranchId) {
        try {
            bankBranchRepository.deleteById(bankBranchId);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete bank branch", e);
        }
    }

    @Override
    public BankBranch getBankBranchById(Integer bankBranchId) {
        try {
            return bankBranchRepository.findById(bankBranchId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get bank branch by id", e);
        }
    }

    @Override
    public List<BankBranch> getAllABankBranches() {
        try {
            return bankBranchRepository.findAll();
        } catch (Exception e) {
            throw new ServiceException("Failed to get all bank branches", e);
        }
    }
}