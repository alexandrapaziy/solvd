package com.solvd.bank;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.bank.domain.Bank;
import com.solvd.bank.domain.BankBranch;
import com.solvd.bank.domain.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.File;
import java.io.IOException;

public class ApplicationJSON {

    private static final Logger LOGGER = (Logger) LogManager.getLogger(ApplicationJSON.class);

    public static void main(String[] args) {
        File bankFile = new File("src/main/resources/json/bank.json");
        File transactionFile = new File("src/main/resources/json/transaction.json");

        // JSON
        try {
            ObjectMapper mapper = new ObjectMapper();
            Bank bank = mapper.readValue(bankFile, Bank.class);

            LOGGER.info("Bank Information:");
            LOGGER.info("Bank id: " + bank.getBankId());
            LOGGER.info("Bank Name: " + bank.getBankName());
            LOGGER.info("Bank Location: " + bank.getBankLocation());
            LOGGER.info("Bank Phone: " + bank.getBankPhone());

            LOGGER.info("\nBank Branches:");
            for (BankBranch bankBranch : bank.getBankBranches()) {
                LOGGER.info("Branch id: " + bankBranch.getBankBranchId());
                LOGGER.info("Branch Location: " + bankBranch.getBankBranchLocation());
                LOGGER.info("Branch Phone: " + bankBranch.getBankBranchPhone());
                LOGGER.info("-------------------------");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            Transaction transaction = mapper.readValue(transactionFile, Transaction.class);

            LOGGER.info("Transaction id: " + transaction.getTransactionId());
            LOGGER.info("Transaction type: " + transaction.getTransactionType().getTransactionTypeName());
            LOGGER.info("Amount: " + transaction.getAmount());
            LOGGER.info("Date: " + transaction.getDate() + "\n");

            LOGGER.info("Account id: " + transaction.getAccount().getAccountId());
            LOGGER.info("Account type: " + transaction.getAccount().getAccountType().getAccountTypeName());
            LOGGER.info("Account balance: " + transaction.getAccount().getBalance() + "\n");

            LOGGER.info("Customer name: " + transaction.getAccount().getCustomer().getCustomerName());
            LOGGER.info("Customer surname: " + transaction.getAccount().getCustomer().getCustomerSurname());
            LOGGER.info("Customer patronymic: " + transaction.getAccount().getCustomer().getCustomerPatronymic() + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
