package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.*;
import com.solvd.bank.domain.exception.PersistenceException;
import com.solvd.bank.persistence.*;
import com.solvd.bank.persistence.config.ConnectionPool;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BankOperationRepositoryImpl implements BankOperationRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO bank.bank_operations (bank_operation_type_id, account_id, date) VALUES (?, ?, ?);";
    private static final String UPDATE = "UPDATE bank.bank_operations SET bank_operation_type_id = ?, account_id = ?, date = ? WHERE bank_operation_id = ?;";
    private static final String DELETE = "DELETE FROM bank.bank_operations WHERE bank_operation_id = ?;";
    private static final String FIND = "SELECT * FROM bank.bank_operations WHERE bank_operation_id = ?;";
    private static final String FIND_ALL = "SELECT * FROM bank.bank_operations;";

    // many-to-many
    private static final String ADD_EMPLOYEE = "INSERT INTO bank.employees_has_bank_operations (employee_id, bank_operation_id) VALUES (?, ?);";
    private static final String REMOVE_EMPLOYEE = "DELETE FROM bank.employees_has_bank_operations WHERE employee_id = ? AND bank_operation_id = ?;";
    private static final String CURRENT_EMPLOYEE = "SELECT * FROM bank.employees_has_bank_operations WHERE bank_operation_id = ?";

    @Override
    public void create(BankOperation bankOperation, Employee employee) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, bankOperation.getBankOperationType().getBankOperationTypeId());
            preparedStatement.setLong(2, bankOperation.getAccount().getAccountId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(bankOperation.getDate()));
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                bankOperation.setBankOperationId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            throw new PersistenceException("Unable to create the bank operation", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(BankOperation bankOperation, Employee employee) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setInt(1, bankOperation.getBankOperationType().getBankOperationTypeId());
            preparedStatement.setLong(2, bankOperation.getAccount().getAccountId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(bankOperation.getDate()));
            preparedStatement.setInt(4, bankOperation.getBankOperationId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new PersistenceException("Unable to update the bank operation", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Integer bankOperationId, Integer employeeId) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, bankOperationId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new PersistenceException("Unable to delete the bank operation", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public BankOperation findById(Integer bankOperationId) {
        BankOperation bankOperation = null;
        BankOperationTypeRepository bankOperationTypeRepository = new BankOperationTypeRepositoryImpl();
        AccountRepository accountRepository = new AccountRepositoryImpl();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND)) {
            preparedStatement.setLong(1, bankOperationId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Integer bankOperationTypeId = resultSet.getInt("bank_operation_type_id");
                Long accountId = resultSet.getLong("account_id");
                LocalDateTime date = (LocalDateTime) resultSet.getObject("date");

                BankOperationType bankOperationType = bankOperationTypeRepository.findById(bankOperationTypeId);
                Account account = accountRepository.findById(accountId);
                Employee employee = getCurrentEmployee(bankOperationId);

                bankOperation = new BankOperation(bankOperationType, account, employee, date);
                bankOperation.setBankOperationId(bankOperationId);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find the bank operation by id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return bankOperation;
    }

    @Override
    public List<BankOperation> findAll() {
        List<BankOperation> bankOperations = new ArrayList<>();
        BankOperationTypeRepository bankOperationTypeRepository = new BankOperationTypeRepositoryImpl();
        AccountRepository accountRepository = new AccountRepositoryImpl();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer bankOperationId = resultSet.getInt("bank_operation_id");
                Integer bankOperationTypeId = resultSet.getInt("bank_operation_type_id");
                Long accountId = resultSet.getLong("account_id");
                LocalDateTime date = (LocalDateTime) resultSet.getObject("date");

                BankOperationType bankOperationType = bankOperationTypeRepository.findById(bankOperationTypeId);
                Account account = accountRepository.findById(accountId);
                Employee employee = getCurrentEmployee(bankOperationId);

                BankOperation bankOperation = new BankOperation(bankOperationType, account, employee, date);
                bankOperation.setBankOperationId(bankOperationId);

                bankOperations.add(bankOperation);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find all bank operations", e);
        }

        return bankOperations;
    }

    // many-to-many

    @Override
    public void addEmployeeForBankOperation(BankOperation bankOperation, Employee employee) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_EMPLOYEE)) {
            preparedStatement.setInt(1, employee.getEmployeeId());
            preparedStatement.setInt(2, bankOperation.getBankOperationId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to add employee for bank operation", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void removeEmployeeForBankOperation(Integer bankOperationId, Integer employeeId) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_EMPLOYEE)) {
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setInt(2, bankOperationId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to remove employee for bank operation", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Employee getCurrentEmployee(Integer bankOperationId) {
        Employee currentEmployee = null;
        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(CURRENT_EMPLOYEE)) {
            preparedStatement.setInt(1, bankOperationId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Integer employeeId = resultSet.getInt("employee_id");
                currentEmployee = employeeRepository.findById(employeeId);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to get current employee for bank operation", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return currentEmployee;
    }

}