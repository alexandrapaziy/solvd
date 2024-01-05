package com.solvd.bank.persistence.implMyBatis;

import com.solvd.bank.domain.Transaction;
import com.solvd.bank.persistence.TransactionRepository;
import com.solvd.bank.persistence.config.Config;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TransactionRepositoryImplMyBatis implements TransactionRepository {
    @Override
    public void create(Transaction transaction) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            TransactionRepository transactionRepository = sqlSession.getMapper(TransactionRepository.class);
            transactionRepository.create(transaction);
        }
    }

    @Override
    public void update(Transaction transaction) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            TransactionRepository transactionRepository = sqlSession.getMapper(TransactionRepository.class);
            transactionRepository.update(transaction);
        }
    }

    @Override
    public void deleteById(Long transactionId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            TransactionRepository transactionRepository = sqlSession.getMapper(TransactionRepository.class);
            transactionRepository.deleteById(transactionId);
        }
    }

    @Override
    public Transaction findById(Long transactionId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            TransactionRepository transactionRepository = sqlSession.getMapper(TransactionRepository.class);
            return transactionRepository.findById(transactionId);
        }
    }

    @Override
    public List<Transaction> findAll() {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            TransactionRepository transactionRepository = sqlSession.getMapper(TransactionRepository.class);
            return transactionRepository.findAll();
        }
    }
}