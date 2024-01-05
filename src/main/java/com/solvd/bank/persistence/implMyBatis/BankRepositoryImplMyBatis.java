package com.solvd.bank.persistence.implMyBatis;

import com.solvd.bank.domain.Bank;
import com.solvd.bank.persistence.BankRepository;
import com.solvd.bank.persistence.config.Config;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BankRepositoryImplMyBatis implements BankRepository {
    @Override
    public void create(Bank bank) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BankRepository bankRepository = sqlSession.getMapper(BankRepository.class);
            bankRepository.create(bank);
        }
    }

    @Override
    public void update(Bank bank) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BankRepository bankRepository = sqlSession.getMapper(BankRepository.class);
            bankRepository.update(bank);
        }
    }

    @Override
    public void deleteById(Long bankId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BankRepository bankRepository = sqlSession.getMapper(BankRepository.class);
            bankRepository.deleteById(bankId);
        }
    }

    @Override
    public Bank findById(Long bankId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BankRepository bankRepository = sqlSession.getMapper(BankRepository.class);
            return bankRepository.findById(bankId);
        }
    }

    @Override
    public List<Bank> findAll() {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BankRepository bankRepository = sqlSession.getMapper(BankRepository.class);
            return bankRepository.findAll();
        }
    }
}