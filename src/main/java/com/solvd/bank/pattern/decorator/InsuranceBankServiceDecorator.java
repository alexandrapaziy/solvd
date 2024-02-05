package com.solvd.bank.pattern.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class InsuranceBankServiceDecorator implements BankService {
    private static final Logger LOGGER = (Logger) LogManager.getLogger(InsuranceBankServiceDecorator.class);
    private final BankService bankService;

    public InsuranceBankServiceDecorator(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public void provideBankService() {
        bankService.provideBankService();
        LOGGER.info("Insurance consultation was added");
    }
}