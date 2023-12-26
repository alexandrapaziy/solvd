package com.solvd.bank.persistence.impl;

import com.solvd.bank.domain.*;
import com.solvd.bank.domain.exception.PersistenceException;
import com.solvd.bank.persistence.*;
import com.solvd.bank.persistence.config.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO bank.employees (bank_branch_id, position_id, name, surname, patronymic, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE bank.employees SET bank_branch_id = ?, position_id = ?, name = ?, surname = ?, patronymic = ?, phone = ?, email = ? WHERE employee_id = ?;";
    private static final String DELETE = "DELETE FROM bank.employees WHERE employee_id = ?;";
    private static final String FIND = "SELECT * FROM bank.employees WHERE employee_id = ?;";
    private static final String FIND_ALL = "SELECT * FROM bank.employees;";

    @Override
    public void create(Employee employee) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, employee.getBankBranch().getBankBranchId());
            preparedStatement.setInt(2, employee.getPosition().getPositionId());
            preparedStatement.setString(3, employee.getEmployeeName());
            preparedStatement.setString(4, employee.getEmployeeSurname());
            preparedStatement.setString(5, employee.getEmployeePatronymic());
            preparedStatement.setString(6, employee.getEmployeePhone());
            preparedStatement.setString(7, employee.getEmployeeEmail());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                employee.setEmployeeId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create the employee", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Employee employee) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setInt(1, employee.getBankBranch().getBankBranchId());
            preparedStatement.setInt(2, employee.getPosition().getPositionId());
            preparedStatement.setString(3, employee.getEmployeeName());
            preparedStatement.setString(4, employee.getEmployeeSurname());
            preparedStatement.setString(5, employee.getEmployeePatronymic());
            preparedStatement.setString(6, employee.getEmployeePhone());
            preparedStatement.setString(7, employee.getEmployeeEmail());
            preparedStatement.setLong(8, employee.getEmployeeId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to update the employee", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Integer employeeId) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, employeeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to delete the employee", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Employee findById(Integer employeeId) {
        Employee employee = null;
        BankBranchRepository bankBranchRepository = new BankBranchRepositoryImpl();
        PositionRepository positionRepository = new PositionRepositoryImpl();


        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND)) {
            preparedStatement.setLong(1, employeeId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Integer bankBranchId = resultSet.getInt("bank_branch_id");
                Integer positionId = resultSet.getInt("position_id");
                String employeeName = resultSet.getString("name");
                String employeeSurname = resultSet.getString("surname");
                String employeePatronymic = resultSet.getString("patronymic");
                String employeePhone = resultSet.getString("email");
                String employeeEmail = resultSet.getString("phone");

                BankBranch bankBranch = bankBranchRepository.findById(bankBranchId);
                Position position = positionRepository.findById(positionId);

                employee = new Employee(bankBranch, position, employeeName, employeeSurname, employeePatronymic,
                        employeePhone, employeeEmail);
                employee.setEmployeeId(employeeId);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find the employee by id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return employee;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        BankBranchRepository bankBranchRepository = new BankBranchRepositoryImpl();
        PositionRepository positionRepository = new PositionRepositoryImpl();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer employeeId = resultSet.getInt("employee_id");
                Integer bankBranchId = resultSet.getInt("bank_branch_id");
                Integer positionId = resultSet.getInt("position_id");
                String employeeName = resultSet.getString("name");
                String employeeSurname = resultSet.getString("surname");
                String employeePatronymic = resultSet.getString("patronymic");
                String employeePhone = resultSet.getString("email");
                String employeeEmail = resultSet.getString("phone");

                BankBranch bankBranch = bankBranchRepository.findById(bankBranchId);
                Position position = positionRepository.findById(positionId);

                Employee employee = new Employee(bankBranch, position, employeeName, employeeSurname, employeePatronymic,
                        employeePhone, employeeEmail);
                employee.setEmployeeId(employeeId);

                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find all employees", e);
        }

        return employees;
    }
}