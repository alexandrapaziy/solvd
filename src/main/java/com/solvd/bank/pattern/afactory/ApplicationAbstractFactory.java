package com.solvd.bank.pattern.afactory;

import com.solvd.bank.domain.Bank;
import com.solvd.bank.persistence.BankRepository;
import com.solvd.bank.service.BankService;

public class ApplicationAbstractFactory {
    public static void main(String[] args) {
        Bank bankJDBC = new Bank("PumbBank", "Kyiv city", "+380956738295");
        BankFactory bankFactoryJDBC = new JdbcBankFactory();
        BankRepository bankRepositoryJDBC = bankFactoryJDBC.createBankRepository();
        BankService bankServiceJDBC = bankFactoryJDBC.createBankService(bankRepositoryJDBC);
        bankServiceJDBC.createBank(bankJDBC);
        bankServiceJDBC.deleteBank(bankJDBC.getBankId());

        Bank bankMyBatis = new Bank("PumbBank", "Kyiv city", "+380956738295");
        BankFactory bankFactoryMyBatis = new MyBatisBankFactory();
        BankRepository bankRepositoryMyBatis = bankFactoryMyBatis.createBankRepository();
        BankService bankServiceMyBatis = bankFactoryMyBatis.createBankService(bankRepositoryMyBatis);
        bankServiceMyBatis.createBank(bankMyBatis);
        bankServiceMyBatis.deleteBank(bankMyBatis.getBankId());
    }
}