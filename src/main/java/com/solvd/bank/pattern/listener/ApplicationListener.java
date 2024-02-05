package com.solvd.bank.pattern.listener;

public class ApplicationListener {
    public static void main(String[] args) {
        Bank bank = new Bank("ABank");

        Customer customerAnna = new Customer("Anna");
        Customer customerMike = new Customer("Mike");

        bank.addCustomer(customerAnna);
        bank.addCustomer(customerMike);

        bank.setName("MonoBank");

        bank.removeCustomer(customerMike);

        bank.setName("PrivatBank");
    }
}