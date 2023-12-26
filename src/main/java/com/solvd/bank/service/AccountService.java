package com.solvd.bank.service;

import com.solvd.bank.domain.Account;

import java.util.List;

public interface AccountService {
    void createAccount(Account account);

    void updateAccount(Account account);

    void deleteAccount(Long accountId);

    Account getAccountById(Long accountId);

    List<Account> getAllAccounts();
}