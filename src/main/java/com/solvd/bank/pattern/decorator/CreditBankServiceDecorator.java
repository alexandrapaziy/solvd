package com.solvd.bank.pattern.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class CreditBankServiceDecorator implements BankService {
    private static final Logger LOGGER = (Logger) LogManager.getLogger(CreditBankServiceDecorator.class);
    private final BankService bankService;

    public CreditBankServiceDecorator(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public void provideBankService() {
        bankService.provideBankService();
        LOGGER.info("Credit consultation was added");
    }
}
