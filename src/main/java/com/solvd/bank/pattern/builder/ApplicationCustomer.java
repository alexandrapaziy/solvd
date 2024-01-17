package com.solvd.bank.pattern.builder;

public class ApplicationCustomer {
    public static void main(String[] args) {
        Customer customer = Customer.builder()
                .setCustomerId(1L)
                .setCustomerName("Ivan")
                .setCustomerSurname("Karpenko")
                .setCustomerPatronymic("Karyi")
                .setCustomerPhone("+380936728493")
                .setCustomerEmail("ivankarpenko@gmail.com")
                .setCustomerAddress("Kyiv city")
                .build();
    }
}