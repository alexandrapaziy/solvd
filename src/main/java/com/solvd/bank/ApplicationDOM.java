package com.solvd.bank;

import com.solvd.bank.domain.Bank;
import com.solvd.bank.domain.BankBranch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApplicationDOM {

    private static final Logger LOGGER = (Logger) LogManager.getLogger(ApplicationDOM.class);

    public static void main(String[] args) {
        File bankFile = new File("src/main/resources/xml/bank.xml");
        File bankBranchFile = new File("src/main/resources/xml/bankBranch.xml");
        File transactionFile = new File("src/main/resources/xml/transaction.xml");

        // DOM
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document bankDocument = builder.parse(bankFile);
            Document bankBranchDocument = builder.parse(bankBranchFile);

            Element bankElement = bankDocument.getDocumentElement();
            Element bankBranchElement = bankBranchDocument.getDocumentElement();

            Long bankId = Long.parseLong(getElementValue(bankElement, "bankId"));
            String bankName = getElementValue(bankElement, "bankName");
            String bankLocation = getElementValue(bankElement, "bankLocation");
            String bankPhone = getElementValue(bankElement, "bankPhone");

            Bank bank = new Bank(bankName, bankLocation, bankPhone);
            bank.setBankId(bankId);

            Integer bankBranchId = Integer.parseInt(getElementValue(bankBranchElement, "bankBranchId"));
            String bankBranchLocation = getElementValue(bankBranchElement, "bankBranchLocation");
            String bankBranchPhone = getElementValue(bankBranchElement, "bankBranchPhone");

            BankBranch bankBranch = new BankBranch(bank, bankBranchLocation, bankBranchPhone);
            bankBranch.setBankBranchId(bankBranchId);

        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document transactionDocument = builder.parse(transactionFile);

            Element transactionElement = transactionDocument.getDocumentElement();

            Long transactionId = Long.parseLong(getElementValue(transactionElement, "transactionId"));
            String transactionTypeName = getElementValue(transactionElement, "transactionTypeName");

            Long accountId = Long.parseLong(getElementValue(transactionElement, "accountId"));
            String accountTypeName = getElementValue(transactionElement, "accountTypeName");
            Double balance = Double.parseDouble(getElementValue(transactionElement, "balance"));

            String customerName = getElementValue(transactionElement, "customerName");
            String customerSurname = getElementValue(transactionElement, "customerSurname");
            String customerPatronymic = getElementValue(transactionElement, "customerPatronymic");

            String bankName = getElementValue(transactionElement, "bankName");
            String bankLocation = getElementValue(transactionElement, "bankLocation");

            Double amount = Double.parseDouble(getElementValue(transactionElement, "amount"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime date = LocalDateTime.parse(getElementValue(transactionElement, "date"), formatter);

            LOGGER.info("Transaction id: " + transactionId);
            LOGGER.info("Transaction type: " + transactionTypeName);
            LOGGER.info("Amount: " + amount);
            LOGGER.info("Date: " + date + "\n");

            LOGGER.info("Account id: " + accountId);
            LOGGER.info("Account type: " + accountTypeName);
            LOGGER.info("Account balance: " + balance + "\n");

            LOGGER.info("Customer name: " + customerName);
            LOGGER.info("Customer surname: " + customerSurname);
            LOGGER.info("Customer patronymic: " + customerPatronymic + "\n");

            LOGGER.info("Bank Name: " + bankName);
            LOGGER.info("Bank Location: " + bankLocation + "\n");

        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getElementValue(Element element, String elementValueName) {
        NodeList nodeList = element.getElementsByTagName(elementValueName);
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            return node.getTextContent();
        }
        return null;
    }
}