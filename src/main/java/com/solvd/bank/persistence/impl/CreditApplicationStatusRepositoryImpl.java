package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.CreditApplicationStatus;
import com.solvd.bank.domain.exception.PersistenceException;
import com.solvd.bank.persistence.CreditApplicationStatusRepository;
import com.solvd.bank.persistence.config.ConnectionPool;

import java.sql.*;

public class CreditApplicationStatusRepositoryImpl implements CreditApplicationStatusRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO bank.credit_application_statuses (credit_application_status_name) VALUES (?);";
    private static final String DELETE = "DELETE FROM bank.credit_application_statuses WHERE credit_application_status_id = ?;";
    private static final String FIND = "SELECT * FROM bank.credit_application_statuses WHERE credit_application_status_id = ?;";

    @Override
    public void create(CreditApplicationStatus creditApplicationStatus) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, creditApplicationStatus.getCreditApplicationStatusName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                creditApplicationStatus.setCreditApplicationStatusId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create the credit application status", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Integer creditApplicationStatusId) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, creditApplicationStatusId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to delete the credit application status", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public CreditApplicationStatus findById(Integer creditApplicationStatusId) {
        CreditApplicationStatus creditApplicationStatus = null;

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND)) {
            preparedStatement.setInt(1, creditApplicationStatusId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String creditApplicationStatusName = resultSet.getString("credit_application_status_name");

                creditApplicationStatus = new CreditApplicationStatus(creditApplicationStatusName);
                creditApplicationStatus.setCreditApplicationStatusId(creditApplicationStatusId);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find the credit application status", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return creditApplicationStatus;
    }
}