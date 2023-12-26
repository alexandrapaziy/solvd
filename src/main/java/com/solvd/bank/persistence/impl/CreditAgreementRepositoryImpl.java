package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.*;
import com.solvd.bank.domain.exception.PersistenceException;
import com.solvd.bank.persistence.*;
import com.solvd.bank.persistence.config.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreditAgreementRepositoryImpl implements CreditAgreementRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO bank.credit_agreements (application_id, credit_agreement_status_id, amount, interest, one_time_payment) VALUES (?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE bank.credit_agreements SET application_id = ?, credit_agreement_status_id = ?, amount = ?, interest = ?, one_time_payment = ? WHERE agreement_id = ?;";
    private static final String DELETE = "DELETE FROM bank.credit_agreements WHERE agreement_id = ?;";
    private static final String FIND = "SELECT * FROM bank.credit_agreements WHERE agreement_id = ?;";
    private static final String FIND_ALL = "SELECT * FROM bank.credit_agreements;";

    @Override
    public void create(CreditAgreement creditAgreement) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, creditAgreement.getCreditApplication().getCreditApplicationId());
            preparedStatement.setInt(2, creditAgreement.getCreditAgreementStatus().getCreditAgreementStatusId());
            preparedStatement.setDouble(3, creditAgreement.getAmount());
            preparedStatement.setInt(4, creditAgreement.getInterest());
            preparedStatement.setDouble(5, creditAgreement.getOneTimePayment());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                creditAgreement.setCreditAgreementId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create the credit agreement", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(CreditAgreement creditAgreement) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setLong(1, creditAgreement.getCreditApplication().getCreditApplicationId());
            preparedStatement.setInt(2, creditAgreement.getCreditAgreementStatus().getCreditAgreementStatusId());
            preparedStatement.setDouble(3, creditAgreement.getAmount());
            preparedStatement.setInt(4, creditAgreement.getInterest());
            preparedStatement.setDouble(5, creditAgreement.getOneTimePayment());
            preparedStatement.setLong(4, creditAgreement.getCreditAgreementId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to update the credit agreement", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Long creditAgreementId) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, creditAgreementId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to delete the credit agreement", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public CreditAgreement findById(Long creditAgreementId) {
        CreditAgreement creditAgreement = null;
        CreditApplicationRepository creditApplicationRepository = new CreditApplicationRepositoryImpl();
        CreditAgreementStatusRepository creditAgreementStatusRepository = new CreditAgreementStatusRepositoryImpl();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND)) {
            preparedStatement.setLong(1, creditAgreementId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long creditApplicationId = resultSet.getLong("application_id");
                Integer creditAgreementStatusId = resultSet.getInt("credit_agreement_status_id");
                double amount = resultSet.getDouble("amount");
                int interest = resultSet.getInt("interest");
                double oneTimePayment = resultSet.getDouble("one_time_payment");

                CreditApplication creditApplication = creditApplicationRepository.findById(creditApplicationId);
                CreditAgreementStatus creditAgreementStatus = creditAgreementStatusRepository.findById(creditAgreementStatusId);

                creditAgreement = new CreditAgreement(creditApplication, creditAgreementStatus, amount, interest, oneTimePayment);
                creditAgreement.setCreditAgreementId(creditAgreementId);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find the credit agreement by id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return creditAgreement;
    }

    @Override
    public List<CreditAgreement> findAll() {
        List<CreditAgreement> creditAgreements = new ArrayList<>();
        CreditApplicationRepository creditApplicationRepository = new CreditApplicationRepositoryImpl();
        CreditAgreementStatusRepository creditAgreementStatusRepository = new CreditAgreementStatusRepositoryImpl();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long creditAgreementId = resultSet.getLong("agreement_id");
                Long creditApplicationId = resultSet.getLong("application_id");
                Integer creditAgreementStatusId = resultSet.getInt("credit_agreement_status_id");
                double amount = resultSet.getDouble("amount");
                int interest = resultSet.getInt("interest");
                double oneTimePayment = resultSet.getDouble("one_time_payment");

                CreditApplication creditApplication = creditApplicationRepository.findById(creditApplicationId);
                CreditAgreementStatus creditAgreementStatus = creditAgreementStatusRepository.findById(creditAgreementStatusId);

                CreditAgreement creditAgreement = new CreditAgreement(creditApplication, creditAgreementStatus, amount, interest, oneTimePayment);
                creditAgreement.setCreditAgreementId(creditAgreementId);

                creditAgreements.add(creditAgreement);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find all credit agreements", e);
        }

        return creditAgreements;
    }
}