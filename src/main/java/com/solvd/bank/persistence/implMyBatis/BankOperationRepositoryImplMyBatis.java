package com.solvd.bank.persistence.implMyBatis;

import com.solvd.bank.domain.BankOperation;
import com.solvd.bank.domain.Employee;
import com.solvd.bank.persistence.BankOperationRepository;
import com.solvd.bank.persistence.config.Config;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BankOperationRepositoryImplMyBatis implements BankOperationRepository {
    @Override
    public void create(BankOperation bankOperation, Employee employee) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BankOperationRepository bankOperationRepository = sqlSession.getMapper(BankOperationRepository.class);
            bankOperationRepository.create(bankOperation, employee);
        }
    }

    @Override
    public void update(BankOperation bankOperation, Employee employee) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BankOperationRepository bankOperationRepository = sqlSession.getMapper(BankOperationRepository.class);
            bankOperationRepository.update(bankOperation, employee);
        }
    }

    @Override
    public void deleteById(Integer bankOperationId, Integer employeeId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BankOperationRepository bankOperationRepository = sqlSession.getMapper(BankOperationRepository.class);
            bankOperationRepository.deleteById(bankOperationId, employeeId);
        }
    }

    @Override
    public BankOperation findById(Integer bankOperationId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BankOperationRepository bankOperationRepository = sqlSession.getMapper(BankOperationRepository.class);
            return bankOperationRepository.findById(bankOperationId);
        }
    }

    @Override
    public List<BankOperation> findAll() {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BankOperationRepository bankOperationRepository = sqlSession.getMapper(BankOperationRepository.class);
            return bankOperationRepository.findAll();
        }
    }

    @Override
    public void addEmployeeForBankOperation(BankOperation bankOperation, Employee employee) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BankOperationRepository bankOperationRepository = sqlSession.getMapper(BankOperationRepository.class);
            bankOperationRepository.addEmployeeForBankOperation(bankOperation, employee);
        }
    }

    @Override
    public void removeEmployeeForBankOperation(Integer bankOperationId, Integer employeeId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BankOperationRepository bankOperationRepository = sqlSession.getMapper(BankOperationRepository.class);
            bankOperationRepository.removeEmployeeForBankOperation(bankOperationId, employeeId);
        }
    }

    @Override
    public Employee getCurrentEmployee(Integer bankOperationId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BankOperationRepository bankOperationRepository = sqlSession.getMapper(BankOperationRepository.class);
            return bankOperationRepository.getCurrentEmployee(bankOperationId);
        }
    }
}