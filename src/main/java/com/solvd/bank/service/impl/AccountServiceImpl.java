package com.solvd.bank.service.impl;

import com.solvd.bank.domain.*;
import com.solvd.bank.domain.exception.ServiceException;
import com.solvd.bank.persistence.AccountRepository;
import com.solvd.bank.service.AccountService;
import com.solvd.bank.service.AccountTypeService;
import com.solvd.bank.service.BankBranchService;
import com.solvd.bank.service.CustomerService;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountTypeService accountTypeService;
    private final BankBranchService bankBranchService;

    public AccountServiceImpl(AccountRepository accountRepository, CustomerService customerService,
                              AccountTypeService accountTypeService, BankBranchService bankBranchService) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.accountTypeService = accountTypeService;
        this.bankBranchService = bankBranchService;
    }

    @Override
    public void createAccount(Account account) {
        try {
            if (account.getCustomer() != null && account.getAccountType() != null && account.getBankBranch() != null) {
                Customer customer = customerService.getCustomerById(account.getCustomer().getCustomerId());
                AccountType accountType = accountTypeService.getAccountTypeById(account.getAccountType().getAccountTypeId());
                BankBranch bankBranch = bankBranchService.getBankBranchById(account.getBankBranch().getBankBranchId());

                account.setCustomer(customer);
                account.setAccountType(accountType);
                account.setBankBranch(bankBranch);
            }

            accountRepository.create(account);
        } catch (Exception e) {
            throw new ServiceException("Failed to create account", e);
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            if (account.getCustomer() != null && account.getAccountType() != null && account.getBankBranch() != null) {
                Customer customer = customerService.getCustomerById(account.getCustomer().getCustomerId());
                AccountType accountType = accountTypeService.getAccountTypeById(account.getAccountType().getAccountTypeId());
                BankBranch bankBranch = bankBranchService.getBankBranchById(account.getBankBranch().getBankBranchId());

                account.setCustomer(customer);
                account.setAccountType(accountType);
                account.setBankBranch(bankBranch);
            }

            accountRepository.update(account);
        } catch (Exception e) {
            throw new ServiceException("Failed to update account", e);
        }
    }

    @Override
    public void deleteAccount(Long accountId) {
        try {
            accountRepository.deleteById(accountId);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete account", e);
        }
    }

    @Override
    public Account getAccountById(Long accountId) {
        try {
            return accountRepository.findById(accountId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get account by id", e);
        }
    }

    @Override
    public List<Account> getAllAccounts() {
        try {
            return accountRepository.findAll();
        } catch (Exception e) {
            throw new ServiceException("Failed to get all accounts", e);
        }
    }
}