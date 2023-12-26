package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.BankOperationType;
import com.solvd.bank.domain.exception.PersistenceException;
import com.solvd.bank.persistence.BankOperationTypeRepository;
import com.solvd.bank.persistence.config.ConnectionPool;

import java.sql.*;

public class BankOperationTypeRepositoryImpl implements BankOperationTypeRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO bank.bank_operation_types (bank_operation_type_name) VALUES (?);";
    private static final String DELETE = "DELETE FROM bank.bank_operation_types WHERE bank_operation_type_id = ?;";
    private static final String FIND = "SELECT * FROM bank.bank_operation_types WHERE bank_operation_type_id = ?;";

    @Override
    public void create(BankOperationType bankOperationType) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, bankOperationType.getBankOperationTypeName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                bankOperationType.setBankOperationTypeId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create the bank operation type", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Integer bankOperationTypeId) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, bankOperationTypeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to delete the bank operation type", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public BankOperationType findById(Integer bankOperationTypeId) {
        BankOperationType bankOperationType = null;

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND)) {
            preparedStatement.setInt(1, bankOperationTypeId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String bankOperationTypeName = resultSet.getString("bank_operation_type_name");

                bankOperationType = new BankOperationType(bankOperationTypeName);
                bankOperationType.setBankOperationTypeId(bankOperationTypeId);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find the bank operation type", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return bankOperationType;
    }
}