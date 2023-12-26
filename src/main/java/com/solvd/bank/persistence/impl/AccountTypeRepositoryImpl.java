package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.AccountType;
import com.solvd.bank.domain.exception.PersistenceException;
import com.solvd.bank.persistence.AccountTypeRepository;
import com.solvd.bank.persistence.config.ConnectionPool;

import java.sql.*;

public class AccountTypeRepositoryImpl implements AccountTypeRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO bank.account_types (account_type_name) VALUES (?);";
    private static final String DELETE = "DELETE FROM bank.account_types WHERE account_type_id = ?;";
    private static final String FIND = "SELECT * FROM bank.account_types WHERE account_type_id = ?;";

    @Override
    public void create(AccountType accountType) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, accountType.getAccountTypeName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                accountType.setAccountTypeId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create the account type", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Integer accountTypeId) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, accountTypeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to delete the account type", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public AccountType findById(Integer accountTypeId) {
        AccountType accountType = null;

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND)) {
            preparedStatement.setInt(1, accountTypeId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String accountTypeName = resultSet.getString("account_type_name");

                accountType = new AccountType(accountTypeName);
                accountType.setAccountTypeId(accountTypeId);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find the account type", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return accountType;
    }
}