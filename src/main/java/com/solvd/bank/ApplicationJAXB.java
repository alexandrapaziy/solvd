package com.solvd.bank;

import com.solvd.bank.domain.Bank;
import com.solvd.bank.domain.BankBranch;
import com.solvd.bank.domain.Transaction;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.File;

public class ApplicationJAXB {

    private static final Logger LOGGER = (Logger) LogManager.getLogger(ApplicationJAXB.class);

    public static void main(String[] args) {
        File bankFile = new File("src/main/resources/xml/bank.xml");
        File transactionFile = new File("src/main/resources/xml/transaction.xml");

        // JAXB
        try {
            JAXBContext context = JAXBContext.newInstance(Bank.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Bank bank = (Bank) unmarshaller.unmarshal(bankFile);

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

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        try {
            JAXBContext context = JAXBContext.newInstance(Transaction.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Transaction transaction = (Transaction) unmarshaller.unmarshal(transactionFile);

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
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}