package com.solvd.bank.pattern.factory;

import com.solvd.bank.domain.Bank;
import com.solvd.bank.persistence.BankRepository;
import com.solvd.bank.service.BankService;
import com.solvd.bank.service.impl.BankServiceImpl;

public class ApplicationFactory {
    public static void main(String[] args) {
        Bank bankJDBC = new Bank("PumbBank", "Kyiv city", "+380956738295");
        BankRepository bankRepositoryJDBC = RepositoriesFactory.createRepository(RepositoryType.JDBC);
        BankService bankServiceJDBC = new BankServiceImpl(bankRepositoryJDBC);
        bankServiceJDBC.createBank(bankJDBC);
        bankServiceJDBC.deleteBank(bankJDBC.getBankId());

        Bank bankMyBatis = new Bank("PumbBank", "Kyiv city", "+380956738295");
        BankRepository bankRepositoryMyBatis = RepositoriesFactory.createRepository(RepositoryType.MyBatis);
        BankService bankServiceMyBatis = new BankServiceImpl(bankRepositoryMyBatis);
        bankServiceMyBatis.createBank(bankMyBatis);
        bankServiceMyBatis.deleteBank(bankMyBatis.getBankId());
    }
}