package com.solvd.bank.pattern.decorator;

public class Bank {
    public static void provideBankService(boolean creditConsultation, boolean insuranceConsultation) {
        BankService bankService = new RegularBankService();

        if (creditConsultation) {
            bankService = new CreditBankServiceDecorator(bankService);
        }
        if (insuranceConsultation) {
            bankService = new InsuranceBankServiceDecorator(bankService);
        }

        bankService.provideBankService();
    }
}