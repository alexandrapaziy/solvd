package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Customer;
import com.solvd.bank.exception.PersistenceException;
import com.solvd.bank.persistence.CustomerRepository;
import com.solvd.bank.persistence.config.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO bank.customers (name, surname, patronymic, phone, email, address) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE bank.customers SET name = ?, surname = ?, patronymic = ?, phone = ?, email = ?, address = ? WHERE customer_id = ?;";
    private static final String DELETE = "DELETE FROM bank.customers WHERE customer_id = ?;";
    private static final String FIND = "SELECT * FROM bank.customers WHERE customer_id = ?;";
    private static final String FIND_ALL = "SELECT * FROM bank.customers;";
    @Override
    public void create(Customer customer) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setString(2, customer.getCustomerSurname());
            preparedStatement.setString(3, customer.getCustomerPatronymic());
            preparedStatement.setString(4, customer.getCustomerPhone());
            preparedStatement.setString(5, customer.getCustomerEmail());
            preparedStatement.setString(6, customer.getCustomerAddress());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                customer.setCustomerId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create the customer", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Customer customer) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setString(2, customer.getCustomerSurname());
            preparedStatement.setString(3, customer.getCustomerPatronymic());
            preparedStatement.setString(4, customer.getCustomerPhone());
            preparedStatement.setString(5, customer.getCustomerEmail());
            preparedStatement.setString(6, customer.getCustomerAddress());
            preparedStatement.setLong(7, customer.getCustomerId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to update the customer", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Long customerId) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, customerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to delete the customer", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Customer findById(Long customerId) {
        Customer customer = null;

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND)) {
            preparedStatement.setLong(1, customerId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String customerName = resultSet.getString("name");
                String customerSurname = resultSet.getString("surname");
                String customerPatronymic = resultSet.getString("patronymic");
                String customerPhone = resultSet.getString("phone");
                String customerEmail = resultSet.getString("email");
                String customerAddress = resultSet.getString("address");

                customer = new Customer(customerName, customerSurname, customerPatronymic, customerPhone, customerEmail, customerAddress);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find the customer by id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return customer;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String customerName = resultSet.getString("name");
                String customerSurname = resultSet.getString("surname");
                String customerPatronymic = resultSet.getString("patronymic");
                String customerPhone = resultSet.getString("phone");
                String customerEmail = resultSet.getString("email");
                String customerAddress = resultSet.getString("address");

                Customer customer = new Customer(customerName, customerSurname, customerPatronymic, customerPhone, customerEmail, customerAddress);
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find all customers", e);
        }

        return customers;
    }
}