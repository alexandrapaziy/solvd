package com.solvd.bank.persistence.implMyBatis;

import com.solvd.bank.domain.Employee;
import com.solvd.bank.persistence.EmployeeRepository;
import com.solvd.bank.persistence.config.Config;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class EmployeeRepositoryImplMyBatis implements EmployeeRepository {
    @Override
    public void create(Employee employee) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            employeeRepository.create(employee);
        }
    }

    @Override
    public void update(Employee employee) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            employeeRepository.update(employee);
        }
    }

    @Override
    public void deleteById(Integer employeeId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            employeeRepository.deleteById(employeeId);
        }
    }

    @Override
    public Employee findById(Integer employeeId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.findById(employeeId);
        }
    }

    @Override
    public List<Employee> findAll() {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.findAll();
        }
    }
}