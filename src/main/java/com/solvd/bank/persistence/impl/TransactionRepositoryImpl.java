package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.*;
import com.solvd.bank.domain.exception.PersistenceException;
import com.solvd.bank.persistence.AccountRepository;
import com.solvd.bank.persistence.TransactionRepository;
import com.solvd.bank.persistence.TransactionTypeRepository;
import com.solvd.bank.persistence.config.ConnectionPool;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepositoryImpl implements TransactionRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO bank.transactions (account_id, transaction_type_id, amount, date) VALUES (?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE bank.transactions SET account_id = ?, transaction_type_id = ?, amount = ?, date = ? WHERE transaction_id = ?;";
    private static final String DELETE = "DELETE FROM bank.transactions WHERE transaction_id = ?;";
    private static final String FIND = "SELECT * FROM bank.transactions WHERE transaction_id = ?;";
    private static final String FIND_ALL = "SELECT * FROM bank.transactions;";

    @Override
    public void create(Transaction transaction) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, transaction.getAccount().getAccountId());
            preparedStatement.setInt(2, transaction.getTransactionType().getTransactionTypeId());
            preparedStatement.setDouble(3, transaction.getAmount());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(transaction.getDate()));
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                transaction.setTransactionId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create the transaction", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Transaction transaction) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setLong(1, transaction.getAccount().getAccountId());
            preparedStatement.setInt(2, transaction.getTransactionType().getTransactionTypeId());
            preparedStatement.setDouble(3, transaction.getAmount());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(transaction.getDate()));
            preparedStatement.setLong(5, transaction.getTransactionId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to update the transaction", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Long transactionId) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, transactionId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to delete the transaction", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Transaction findById(Long transactionId) {
        Transaction transaction = null;
        AccountRepository accountRepository = new AccountRepositoryImpl();
        TransactionTypeRepository transactionTypeRepository = new TransactionTypeRepositoryImpl();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND)) {
            preparedStatement.setLong(1, transactionId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long accountId = resultSet.getLong("account_id");
                Integer transactionTypeId = resultSet.getInt("transaction_type_id");
                Double amount = resultSet.getDouble("amount");
                LocalDateTime date = (LocalDateTime) resultSet.getObject("date");

                Account account = accountRepository.findById(accountId);
                TransactionType transactionType = transactionTypeRepository.findById(transactionTypeId);

                transaction = new Transaction(account, transactionType, amount, date);
                transaction.setTransactionId(transactionId);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find the transaction by id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return transaction;
    }

    @Override
    public List<Transaction> findAll() {
        List<Transaction> transactions = new ArrayList<>();
        AccountRepository accountRepository = new AccountRepositoryImpl();
        TransactionTypeRepository transactionTypeRepository = new TransactionTypeRepositoryImpl();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long transactionId = resultSet.getLong("transaction_id");
                Long accountId = resultSet.getLong("account_id");
                Integer transactionTypeId = resultSet.getInt("transaction_type_id");
                Double amount = resultSet.getDouble("amount");
                LocalDateTime date = (LocalDateTime) resultSet.getObject("date");

                Account account = accountRepository.findById(accountId);
                TransactionType transactionType = transactionTypeRepository.findById(transactionTypeId);

                Transaction transaction = new Transaction(account, transactionType, amount, date);
                transaction.setTransactionId(transactionId);

                transactions.add(transaction);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find all transactions", e);
        }

        return transactions;
    }
}