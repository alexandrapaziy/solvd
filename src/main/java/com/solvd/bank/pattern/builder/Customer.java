package com.solvd.bank.pattern.builder;

public class Customer {
    private Long customerId;
    private String customerName;
    private String customerSurname;
    private String customerPatronymic;
    private String customerPhone;
    private String customerEmail;
    private String customerAddress;

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public String getCustomerPatronymic() {
        return customerPatronymic;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public static Builder builder() {
        return new Builder(new Customer());
    }

    public static class Builder {
        private final Customer customer;

        public Builder(Customer customer) {
            this.customer = customer;
        }

        public Builder setCustomerId(Long customerId) {
            customer.customerId = customerId;
            return this;
        }

        public Builder setCustomerName(String customerName) {
            customer.customerName = customerName;
            return this;
        }

        public Builder setCustomerSurname(String customerSurname) {
            customer.customerSurname = customerSurname;
            return this;
        }

        public Builder setCustomerPatronymic(String customerPatronymic) {
            customer.customerPatronymic = customerPatronymic;
            return this;
        }

        public Builder setCustomerPhone(String customerPhone) {
            customer.customerPhone = customerPhone;
            return this;
        }

        public Builder setCustomerEmail(String customerEmail) {
            customer.customerEmail = customerEmail;
            return this;
        }

        public Builder setCustomerAddress(String customerAddress) {
            customer.customerAddress = customerAddress;
            return this;
        }

        public Customer build() {
            return customer;
        }
    }
}