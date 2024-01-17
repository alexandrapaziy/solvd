package com.solvd.bank.pattern.afactory;

import com.solvd.bank.persistence.BankRepository;
import com.solvd.bank.persistence.impl.BankRepositoryImpl;
import com.solvd.bank.service.BankService;
import com.solvd.bank.service.impl.BankServiceImpl;

public class JdbcBankFactory implements BankFactory {
    @Override
    public BankRepository createBankRepository() {
        return new BankRepositoryImpl();
    }

    @Override
    public BankService createBankService(BankRepository bankRepository) {
        return new BankServiceImpl(bankRepository);
    }
}