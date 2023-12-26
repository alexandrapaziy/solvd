package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.*;
import com.solvd.bank.domain.exception.PersistenceException;
import com.solvd.bank.persistence.*;
import com.solvd.bank.persistence.config.ConnectionPool;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CreditHistoryRepositoryImpl implements CreditHistoryRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO bank.credit_histories (agreement_id, amount, date) VALUES (?, ?, ?);";
    private static final String UPDATE = "UPDATE bank.credit_histories SET agreement_id = ?, amount = ?, date = ? WHERE payment_id = ?;";
    private static final String DELETE = "DELETE FROM bank.credit_histories WHERE payment_id = ?;";
    private static final String FIND = "SELECT * FROM bank.credit_histories WHERE payment_id = ?;";
    private static final String FIND_ALL = "SELECT * FROM bank.credit_histories;";

    @Override
    public void create(CreditHistory creditHistory) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, creditHistory.getCreditAgreement().getCreditAgreementId());
            preparedStatement.setDouble(2, creditHistory.getAmount());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(creditHistory.getDate()));

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                creditHistory.setPaymentId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create the credit history", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(CreditHistory creditHistory) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setLong(1, creditHistory.getCreditAgreement().getCreditAgreementId());
            preparedStatement.setDouble(2, creditHistory.getAmount());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(creditHistory.getDate()));

            preparedStatement.setLong(4, creditHistory.getPaymentId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to update the credit history", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Long creditHistoryId) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, creditHistoryId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to delete the credit history", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public CreditHistory findById(Long creditHistoryId) {
        CreditHistory creditHistory = null;
        CreditAgreementRepository creditAgreementRepository = new CreditAgreementRepositoryImpl();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND)) {
            preparedStatement.setLong(1, creditHistoryId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long creditAgreementId = resultSet.getLong("agreement_id");
                double amount = resultSet.getDouble("amount");
                LocalDateTime date = (LocalDateTime) resultSet.getObject("date");

                CreditAgreement creditAgreement = creditAgreementRepository.findById(creditAgreementId);

                creditHistory = new CreditHistory(creditAgreement, amount, date);
                creditHistory.setPaymentId(creditHistoryId);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find the credit history by id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return creditHistory;
    }

    @Override
    public List<CreditHistory> findAll() {
        List<CreditHistory> creditHistories = new ArrayList<>();
        CreditAgreementRepository creditAgreementRepository = new CreditAgreementRepositoryImpl();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long creditHistoryId = resultSet.getLong("payment_id");
                Long creditAgreementId = resultSet.getLong("agreement_id");
                double amount = resultSet.getDouble("amount");
                LocalDateTime date = (LocalDateTime) resultSet.getObject("date");


                CreditAgreement creditAgreement = creditAgreementRepository.findById(creditAgreementId);

                CreditHistory creditHistory = new CreditHistory(creditAgreement, amount, date);
                creditHistory.setPaymentId(creditHistoryId);

                creditHistories.add(creditHistory);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find all credit histories", e);
        }

        return creditHistories;
    }
}