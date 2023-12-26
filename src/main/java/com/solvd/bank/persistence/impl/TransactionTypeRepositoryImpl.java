package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.TransactionType;
import com.solvd.bank.domain.exception.PersistenceException;
import com.solvd.bank.persistence.TransactionTypeRepository;
import com.solvd.bank.persistence.config.ConnectionPool;

import java.sql.*;

public class TransactionTypeRepositoryImpl implements TransactionTypeRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO bank.transaction_types (transaction_type_name) VALUES (?);";
    private static final String DELETE = "DELETE FROM bank.transaction_types WHERE transaction_type_id = ?;";
    private static final String FIND = "SELECT * FROM bank.transaction_types WHERE transaction_type_id = ?;";

    @Override
    public void create(TransactionType transactionType) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, transactionType.getTransactionTypeName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                transactionType.setTransactionTypeId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create the transaction type", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Integer transactionTypeId) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, transactionTypeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to delete the transaction type", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public TransactionType findById(Integer transactionTypeId) {
        TransactionType transactionType = null;

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND)) {
            preparedStatement.setInt(1, transactionTypeId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String transactionTypeName = resultSet.getString("transaction_type_name");

                transactionType = new TransactionType(transactionTypeName);
                transactionType.setTransactionTypeId(transactionTypeId);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find the transaction type", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return transactionType;
    }
}