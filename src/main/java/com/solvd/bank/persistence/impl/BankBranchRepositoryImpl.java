package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Bank;
import com.solvd.bank.domain.BankBranch;
import com.solvd.bank.domain.exception.PersistenceException;
import com.solvd.bank.persistence.BankBranchRepository;
import com.solvd.bank.persistence.BankRepository;
import com.solvd.bank.persistence.config.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankBranchRepositoryImpl implements BankBranchRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO bank.bank_branches (bank_id, location, phone) VALUES (?, ?, ?);";
    private static final String UPDATE = "UPDATE bank.bank_branches SET bank_id = ?, location = ?, phone = ? WHERE bank_branch_id = ?;";
    private static final String DELETE = "DELETE FROM bank.bank_branches WHERE bank_branch_id = ?;";
    private static final String FIND = "SELECT * FROM bank.bank_branches WHERE bank_branch_id = ?;";
    private static final String FIND_ALL = "SELECT * FROM bank.bank_branches;";

    @Override
    public void create(BankBranch bankBranch) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, bankBranch.getBank().getBankId());
            preparedStatement.setString(2, bankBranch.getBankBranchLocation());
            preparedStatement.setString(3, bankBranch.getBankBranchPhone());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                bankBranch.setBankBranchId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create the bank branch", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(BankBranch bankBranch) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setLong(1, bankBranch.getBank().getBankId());
            preparedStatement.setString(2, bankBranch.getBankBranchLocation());
            preparedStatement.setString(3, bankBranch.getBankBranchPhone());
            preparedStatement.setLong(4, bankBranch.getBankBranchId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to update the bank branch", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Integer bankBranchId) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, bankBranchId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to delete the bank branch", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public BankBranch findById(Integer bankBranchId) {
        BankBranch bankBranch = null;
        BankRepository bankRepository = new BankRepositoryImpl();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND)) {
            preparedStatement.setLong(1, bankBranchId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long bankId = resultSet.getLong("bank_id");
                String bankBranchLocation = resultSet.getString("location");
                String bankBranchPhone = resultSet.getString("phone");

                Bank bank = bankRepository.findById(bankId);

                bankBranch = new BankBranch(bank, bankBranchLocation, bankBranchPhone);
                bankBranch.setBankBranchId(bankBranchId);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find the bank branch by id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return bankBranch;
    }

    @Override
    public List<BankBranch> findAll() {
        List<BankBranch> bankBranches = new ArrayList<>();
        BankRepository bankRepository = new BankRepositoryImpl();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer bankBranchId = resultSet.getInt("bank_branch_id");
                Long bankId = resultSet.getLong("bank_id");
                String bankBranchLocation = resultSet.getString("location");
                String bankBranchPhone = resultSet.getString("phone");

                Bank bank = bankRepository.findById(bankId);

                BankBranch bankBranch = new BankBranch(bank, bankBranchLocation, bankBranchPhone);
                bankBranch.setBankBranchId(bankBranchId);
                bankBranches.add(bankBranch);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find all bank branches", e);
        }

        return bankBranches;
    }
}