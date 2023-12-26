package com.solvd.bank.service;

import com.solvd.bank.domain.AccountType;

public interface AccountTypeService {
    void createAccountType(AccountType accountType);

    void deleteAccountType(Integer accountTypeId);

    AccountType getAccountTypeById(Integer accountTypeId);
}