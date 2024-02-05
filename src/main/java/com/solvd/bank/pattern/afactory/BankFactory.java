package com.solvd.bank.pattern.afactory;

import com.solvd.bank.persistence.BankRepository;
import com.solvd.bank.service.BankService;

public interface BankFactory {
    BankRepository createBankRepository();

    BankService createBankService(BankRepository bankRepository);
}