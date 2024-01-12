package com.solvd.bank.persistence.implMyBatis;

import com.solvd.bank.domain.TransactionType;
import com.solvd.bank.persistence.TransactionTypeRepository;
import com.solvd.bank.persistence.config.Config;
import org.apache.ibatis.session.SqlSession;

public class TransactionTypeRepositoryImplMyBatis implements TransactionTypeRepository {
    @Override
    public void create(TransactionType transactionType) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            TransactionTypeRepository transactionTypeRepository = sqlSession.getMapper(TransactionTypeRepository.class);
            transactionTypeRepository.create(transactionType);
        }
    }

    @Override
    public void deleteById(Integer transactionTypeId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            TransactionTypeRepository transactionTypeRepository = sqlSession.getMapper(TransactionTypeRepository.class);
            transactionTypeRepository.deleteById(transactionTypeId);
        }
    }

    @Override
    public TransactionType findById(Integer transactionTypeId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            TransactionTypeRepository transactionTypeRepository = sqlSession.getMapper(TransactionTypeRepository.class);
            return transactionTypeRepository.findById(transactionTypeId);
        }
    }
}