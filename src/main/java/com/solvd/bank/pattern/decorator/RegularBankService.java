package com.solvd.bank.pattern.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class RegularBankService implements BankService {
    private static final Logger LOGGER = (Logger) LogManager.getLogger(RegularBankService.class);

    @Override
    public void provideBankService() {
        LOGGER.info("Regular bank service was provided");
    }
}
