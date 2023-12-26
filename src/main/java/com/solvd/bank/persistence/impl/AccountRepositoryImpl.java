package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.*;
import com.solvd.bank.domain.exception.PersistenceException;
import com.solvd.bank.persistence.*;
import com.solvd.bank.persistence.config.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO bank.accounts (customer_id, account_type_id, bank_branch_id, balance) VALUES (?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE bank.accounts SET customer_id = ?, account_type_id = ?, bank_branch_id = ?, balance = ? WHERE account_id = ?;";
    private static final String DELETE = "DELETE FROM bank.accounts WHERE account_id = ?;";
    private static final String FIND = "SELECT * FROM bank.accounts WHERE account_id = ?;";
    private static final String FIND_ALL = "SELECT * FROM bank.accounts;";

    @Override
    public void create(Account account) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, account.getCustomer().getCustomerId());
            preparedStatement.setInt(2, account.getAccountType().getAccountTypeId());
            preparedStatement.setInt(3, account.getBankBranch().getBankBranchId());
            preparedStatement.setDouble(4, account.getBalance());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                account.setAccountId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create the account", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Account account) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setLong(1, account.getCustomer().getCustomerId());
            preparedStatement.setInt(2, account.getAccountType().getAccountTypeId());
            preparedStatement.setInt(3, account.getBankBranch().getBankBranchId());
            preparedStatement.setDouble(4, account.getBalance());
            preparedStatement.setLong(5, account.getAccountId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to update the account", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Long accountId) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, accountId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to delete the account", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Account findById(Long accountId) {
        Account account = null;
        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        AccountTypeRepository accountTypeRepository = new AccountTypeRepositoryImpl();
        BankBranchRepository bankBranchRepository = new BankBranchRepositoryImpl();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND)) {
            preparedStatement.setLong(1, accountId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long customerId = resultSet.getLong("customer_id");
                Integer accountTypeId = resultSet.getInt("account_type_id");
                Integer bankBranchId = resultSet.getInt("bank_branch_id");
                double balance = resultSet.getDouble("balance");

                Customer customer = customerRepository.findById(customerId);
                AccountType accountType = accountTypeRepository.findById(accountTypeId);
                BankBranch bankBranch = bankBranchRepository.findById(bankBranchId);

                account = new Account(customer, accountType, bankBranch, balance);
                account.setAccountId(accountId);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find the account by id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return account;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        AccountTypeRepository accountTypeRepository = new AccountTypeRepositoryImpl();
        BankBranchRepository bankBranchRepository = new BankBranchRepositoryImpl();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long accountId = resultSet.getLong("account_id");
                Long customerId = resultSet.getLong("customer_id");
                Integer accountTypeId = resultSet.getInt("account_type_id");
                Integer bankBranchId = resultSet.getInt("bank_branch_id");
                double balance = resultSet.getDouble("balance");

                Customer customer = customerRepository.findById(customerId);
                AccountType accountType = accountTypeRepository.findById(accountTypeId);
                BankBranch bankBranch = bankBranchRepository.findById(bankBranchId);

                Account account = new Account(customer, accountType, bankBranch, balance);
                account.setAccountId(accountId);

                accounts.add(account);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find all accounts", e);
        }

        return accounts;
    }
}