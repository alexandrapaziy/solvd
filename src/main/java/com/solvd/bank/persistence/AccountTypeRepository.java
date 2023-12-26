package com.solvd.bank.persistence;

import com.solvd.bank.domain.AccountType;

public interface AccountTypeRepository {
    void create(AccountType accountType);

    void deleteById(Integer accountTypeId);

    AccountType findById(Integer accountTypeId);
}