package com.solvd.bank;

import com.solvd.bank.domain.Customer;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ApplicationStAX {
    public static void main(String[] args) {
        File customerFile = new File("src/main/resources/xml/customer.xml");

        //StAX
        try (FileInputStream stream = new FileInputStream(customerFile)) {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader reader = factory.createXMLEventReader(stream);

            Customer customer = null;

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    String elementName = startElement.getName().getLocalPart();

                    switch (elementName) {
                        case "customer":
                            customer = new Customer();
                            break;
                        case "customerId":
                            event = reader.nextEvent();
                            customer.setCustomerId(Long.parseLong(event.asCharacters().getData()));
                            break;
                        case "customerName":
                            event = reader.nextEvent();
                            customer.setCustomerName(event.asCharacters().getData());
                            break;
                        case "customerSurname":
                            event = reader.nextEvent();
                            customer.setCustomerSurname(event.asCharacters().getData());
                            break;
                        case "customerPatronymic":
                            event = reader.nextEvent();
                            customer.setCustomerPatronymic(event.asCharacters().getData());
                            break;
                        case "customerPhone":
                            event = reader.nextEvent();
                            customer.setCustomerPhone(event.asCharacters().getData());
                            break;
                        case "customerEmail":
                            event = reader.nextEvent();
                            customer.setCustomerEmail(event.asCharacters().getData());
                            break;
                        case "customerAddress":
                            event = reader.nextEvent();
                            customer.setCustomerAddress(event.asCharacters().getData());
                            break;
                    }
                }
            }
        } catch (IOException | XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }
}