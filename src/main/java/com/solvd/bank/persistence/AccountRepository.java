package com.solvd.bank.persistence;

import com.solvd.bank.domain.Account;

import java.util.List;

public interface AccountRepository {
    void create(Account account);

    void update(Account account);

    void deleteById(Long accountId);

    Account findById(Long accountId);

    List<Account> findAll();
}