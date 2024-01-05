package com.solvd.bank.persistence.implMyBatis;

import com.solvd.bank.domain.CreditHistory;
import com.solvd.bank.persistence.CreditHistoryRepository;
import com.solvd.bank.persistence.config.Config;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CreditHistoryRepositoryImplMyBatis implements CreditHistoryRepository {
    @Override
    public void create(CreditHistory creditHistory) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CreditHistoryRepository creditHistoryRepository = sqlSession.getMapper(CreditHistoryRepository.class);
            creditHistoryRepository.create(creditHistory);
        }
    }

    @Override
    public void update(CreditHistory creditHistory) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CreditHistoryRepository creditHistoryRepository = sqlSession.getMapper(CreditHistoryRepository.class);
            creditHistoryRepository.update(creditHistory);
        }
    }

    @Override
    public void deleteById(Long creditHistoryId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CreditHistoryRepository creditHistoryRepository = sqlSession.getMapper(CreditHistoryRepository.class);
            creditHistoryRepository.deleteById(creditHistoryId);
        }
    }

    @Override
    public CreditHistory findById(Long creditHistoryId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CreditHistoryRepository creditHistoryRepository = sqlSession.getMapper(CreditHistoryRepository.class);
            return creditHistoryRepository.findById(creditHistoryId);
        }
    }

    @Override
    public List<CreditHistory> findAll() {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CreditHistoryRepository creditHistoryRepository = sqlSession.getMapper(CreditHistoryRepository.class);
            return creditHistoryRepository.findAll();
        }
    }
}