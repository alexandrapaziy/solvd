package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.*;
import com.solvd.bank.domain.exception.PersistenceException;
import com.solvd.bank.persistence.*;
import com.solvd.bank.persistence.config.ConnectionPool;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreditApplicationRepositoryImpl implements CreditApplicationRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO bank.credit_applications (customer_id, credit_application_status_id, date) VALUES (?, ?, ?);";
    private static final String UPDATE = "UPDATE bank.credit_applications SET customer_id = ?, credit_application_status_id = ?, date = ? WHERE application_id = ?;";
    private static final String DELETE = "DELETE FROM bank.credit_applications WHERE application_id = ?;";
    private static final String FIND = "SELECT * FROM bank.credit_applications WHERE application_id = ?;";
    private static final String FIND_ALL = "SELECT * FROM bank.credit_applications;";

    @Override
    public void create(CreditApplication creditApplication) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, creditApplication.getCustomer().getCustomerId());
            preparedStatement.setInt(2, creditApplication.getCreditApplicationStatus().getCreditApplicationStatusId());
            preparedStatement.setDate(3, Date.valueOf(creditApplication.getDate()));
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                creditApplication.setCreditApplicationId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create the credit application", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(CreditApplication creditApplication) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setLong(1, creditApplication.getCustomer().getCustomerId());
            preparedStatement.setInt(2, creditApplication.getCreditApplicationStatus().getCreditApplicationStatusId());
            preparedStatement.setDate(3, Date.valueOf(creditApplication.getDate()));
            preparedStatement.setLong(4, creditApplication.getCreditApplicationId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to update the credit application", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Long creditApplicationId) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, creditApplicationId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to delete the credit application", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public CreditApplication findById(Long creditApplicationId) {
        CreditApplication creditApplication = null;
        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        CreditApplicationStatusRepository creditApplicationStatusRepository = new CreditApplicationStatusRepositoryImpl();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND)) {
            preparedStatement.setLong(1, creditApplicationId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long customerId = resultSet.getLong("customer_id");
                Integer creditApplicationStatusId = resultSet.getInt("credit_application_status_id");
                LocalDate date = resultSet.getDate("date").toLocalDate();

                Customer customer = customerRepository.findById(customerId);
                CreditApplicationStatus creditApplicationStatus = creditApplicationStatusRepository.findById(creditApplicationStatusId);

                creditApplication = new CreditApplication(customer, creditApplicationStatus, date);
                creditApplication.setCreditApplicationId(creditApplicationId);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find the credit application by id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return creditApplication;
    }

    @Override
    public List<CreditApplication> findAll() {
        List<CreditApplication> creditApplications = new ArrayList<>();
        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        CreditApplicationStatusRepository creditApplicationStatusRepository = new CreditApplicationStatusRepositoryImpl();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long creditApplicationId = resultSet.getLong("application_id");
                Long customerId = resultSet.getLong("customer_id");
                Integer creditApplicationStatusId = resultSet.getInt("credit_application_status_id");
                LocalDate date = resultSet.getDate("date").toLocalDate();

                Customer customer = customerRepository.findById(customerId);
                CreditApplicationStatus creditApplicationStatus = creditApplicationStatusRepository.findById(creditApplicationStatusId);

                CreditApplication creditApplication = new CreditApplication(customer, creditApplicationStatus, date);
                creditApplication.setCreditApplicationId(creditApplicationId);

                creditApplications.add(creditApplication);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find all accounts", e);
        }

        return creditApplications;
    }
}