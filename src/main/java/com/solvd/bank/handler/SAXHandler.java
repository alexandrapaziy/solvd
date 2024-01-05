package com.solvd.bank.handler;

import com.solvd.bank.domain.Customer;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {
    private Customer customer;
    private StringBuilder elementValue;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        elementValue = new StringBuilder();

        if ("customer".equals(qName)) {
            customer = new Customer();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String value = elementValue.toString().trim();

        switch (qName) {
            case "customerId":
                customer.setCustomerId(Long.parseLong(value));
                break;
            case "customerName":
                customer.setCustomerName(value);
                break;
            case "customerSurname":
                customer.setCustomerSurname(value);
                break;
            case "customerPatronymic":
                customer.setCustomerPatronymic(value);
                break;
            case "customerPhone":
                customer.setCustomerPhone(value);
                break;
            case "customerEmail":
                customer.setCustomerEmail(value);
                break;
            case "customerAddress":
                customer.setCustomerAddress(value);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        elementValue.append(ch, start, length);
    }

    public Customer getCustomer() {
        return customer;
    }
}