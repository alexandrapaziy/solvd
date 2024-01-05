package com.solvd.bank;

import com.solvd.bank.domain.Bank;
import com.solvd.bank.domain.BankBranch;
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

public class ApplicationDOM {
    public static void main(String[] args) {
        File bankFile = new File("src/main/resources/xml/bank.xml");
        File bankBranchFile = new File("src/main/resources/xml/bankBranch.xml");

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