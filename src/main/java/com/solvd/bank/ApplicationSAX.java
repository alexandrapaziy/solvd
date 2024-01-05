package com.solvd.bank;

import com.solvd.bank.domain.Customer;
import com.solvd.bank.handler.SAXHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class ApplicationSAX {
    public static void main(String[] args) {
        File customerFile = new File("src/main/resources/xml/customer.xml");
        SAXHandler handler = new SAXHandler();

        //SAX
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(customerFile, handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }

        Customer customer = handler.getCustomer();
    }
}