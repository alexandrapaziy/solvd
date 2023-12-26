package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.CreditAgreementStatus;
import com.solvd.bank.domain.exception.PersistenceException;
import com.solvd.bank.persistence.CreditAgreementStatusRepository;
import com.solvd.bank.persistence.config.ConnectionPool;

import java.sql.*;

public class CreditAgreementStatusRepositoryImpl implements CreditAgreementStatusRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO bank.credit_agreement_statuses (credit_agreement_status_name) VALUES (?);";
    private static final String DELETE = "DELETE FROM bank.credit_agreement_statuses WHERE credit_agreement_status_id = ?;";
    private static final String FIND = "SELECT * FROM bank.credit_agreement_statuses WHERE credit_agreement_status_id = ?;";

    @Override
    public void create(CreditAgreementStatus creditAgreementStatus) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, creditAgreementStatus.getCreditAgreementStatusName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                creditAgreementStatus.setCreditAgreementStatusId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create the credit agreement status", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Integer creditAgreementStatusId) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, creditAgreementStatusId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to delete the credit agreement status", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public CreditAgreementStatus findById(Integer creditAgreementStatusId) {
        CreditAgreementStatus creditAgreementStatus = null;

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND)) {
            preparedStatement.setInt(1, creditAgreementStatusId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String creditAgreementStatusName = resultSet.getString("credit_agreement_status_name");

                creditAgreementStatus = new CreditAgreementStatus(creditAgreementStatusName);
                creditAgreementStatus.setCreditAgreementStatusId(creditAgreementStatusId);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find the credit agreement status", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return creditAgreementStatus;
    }
}