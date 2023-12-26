package com.solvd.bank.service.impl;

import com.solvd.bank.domain.AccountType;
import com.solvd.bank.domain.exception.ServiceException;
import com.solvd.bank.persistence.AccountTypeRepository;
import com.solvd.bank.service.AccountTypeService;

public class AccountTypeServiceImpl implements AccountTypeService {
    private final AccountTypeRepository accountTypeRepository;

    public AccountTypeServiceImpl(AccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    public void createAccountType(AccountType accountType) {
        try {
            accountTypeRepository.create(accountType);
        } catch (Exception e) {
            throw new ServiceException("Failed to create account type", e);
        }
    }

    @Override
    public void deleteAccountType(Integer accountTypeId) {
        try {
            accountTypeRepository.deleteById(accountTypeId);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete account type", e);
        }
    }

    @Override
    public AccountType getAccountTypeById(Integer accountTypeId) {
        try {
            return accountTypeRepository.findById(accountTypeId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get account type by id", e);
        }
    }
}