package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Bank;
import com.solvd.bank.domain.exception.PersistenceException;
import com.solvd.bank.persistence.BankRepository;
import com.solvd.bank.persistence.config.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankRepositoryImpl implements BankRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO bank.banks (bank_name, main_location, main_phone) VALUES (?, ?, ?);";
    private static final String UPDATE = "UPDATE bank.banks SET bank_name = ?, main_location = ?, main_phone = ? WHERE bank_id = ?;";
    private static final String DELETE = "DELETE FROM bank.banks WHERE bank_id = ?;";
    private static final String FIND = "SELECT * FROM bank.banks WHERE bank_id = ?;";
    private static final String FIND_ALL = "SELECT * FROM bank.banks;";

    @Override
    public void create(Bank bank) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, bank.getBankName());
            preparedStatement.setString(2, bank.getBankLocation());
            preparedStatement.setString(3, bank.getBankPhone());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                bank.setBankId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create the bank", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Bank bank) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, bank.getBankName());
            preparedStatement.setString(2, bank.getBankLocation());
            preparedStatement.setString(3, bank.getBankPhone());
            preparedStatement.setLong(4, bank.getBankId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to update the bank", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Long bankId) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, bankId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to delete the bank", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Bank findById(Long bankId) {
        Bank bank = null;

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND)) {
            preparedStatement.setLong(1, bankId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String bankName = resultSet.getString("bank_name");
                String bankLocation = resultSet.getString("main_location");
                String bankPhone = resultSet.getString("main_phone");

                bank = new Bank(bankName, bankLocation, bankPhone);
                bank.setBankId(bankId);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find the bank by id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return bank;
    }

    @Override
    public List<Bank> findAll() {
        List<Bank> banks = new ArrayList<>();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long bankId = resultSet.getLong("bank_id");
                String bankName = resultSet.getString("bank_name");
                String bankLocation = resultSet.getString("main_location");
                String bankPhone = resultSet.getString("main_phone");

                Bank bank = new Bank(bankName, bankLocation, bankPhone);
                bank.setBankId(bankId);
                banks.add(bank);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find all banks", e);
        }

        return banks;
    }
}