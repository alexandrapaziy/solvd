package com.solvd.bank.pattern.afactory;

import com.solvd.bank.persistence.BankRepository;
import com.solvd.bank.persistence.implMyBatis.BankRepositoryImplMyBatis;
import com.solvd.bank.service.BankService;
import com.solvd.bank.service.impl.BankServiceImpl;

public class MyBatisBankFactory implements BankFactory {
    @Override
    public BankRepository createBankRepository() {
        return new BankRepositoryImplMyBatis();
    }

    @Override
    public BankService createBankService(BankRepository bankRepository) {
        return new BankServiceImpl(bankRepository);
    }
}