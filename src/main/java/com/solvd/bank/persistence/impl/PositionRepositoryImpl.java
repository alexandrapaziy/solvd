package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.Position;
import com.solvd.bank.domain.exception.PersistenceException;
import com.solvd.bank.persistence.PositionRepository;
import com.solvd.bank.persistence.config.ConnectionPool;

import java.sql.*;

public class PositionRepositoryImpl implements PositionRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO bank.positions (position_name) VALUES (?);";
    private static final String DELETE = "DELETE FROM bank.positions WHERE position_id = ?;";
    private static final String FIND = "SELECT * FROM bank.positions WHERE position_id = ?;";

    @Override
    public void create(Position position) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, position.getPositionName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                position.setPositionId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create the position", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Integer positionId) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, positionId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to delete the position", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Position findById(Integer positionId) {
        Position position = null;

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND)) {
            preparedStatement.setInt(1, positionId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String positionName = resultSet.getString("position_name");

                position = new Position(positionName);
                position.setPositionId(positionId);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find the position", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return position;
    }
}