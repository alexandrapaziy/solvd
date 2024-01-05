package com.solvd.bank.persistence.implMyBatis;

import com.solvd.bank.domain.CreditApplication;
import com.solvd.bank.persistence.CreditApplicationRepository;
import com.solvd.bank.persistence.config.Config;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CreditApplicationRepositoryImplMyBatis implements CreditApplicationRepository {
    @Override
    public void create(CreditApplication creditApplication) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CreditApplicationRepository creditApplicationRepository = sqlSession.getMapper(CreditApplicationRepository.class);
            creditApplicationRepository.create(creditApplication);
        }
    }

    @Override
    public void update(CreditApplication creditApplication) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CreditApplicationRepository creditApplicationRepository = sqlSession.getMapper(CreditApplicationRepository.class);
            creditApplicationRepository.update(creditApplication);
        }
    }

    @Override
    public void deleteById(Long creditApplicationId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CreditApplicationRepository creditApplicationRepository = sqlSession.getMapper(CreditApplicationRepository.class);
            creditApplicationRepository.deleteById(creditApplicationId);
        }
    }

    @Override
    public CreditApplication findById(Long creditApplicationId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CreditApplicationRepository creditApplicationRepository = sqlSession.getMapper(CreditApplicationRepository.class);
            return creditApplicationRepository.findById(creditApplicationId);
        }
    }

    @Override
    public List<CreditApplication> findAll() {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CreditApplicationRepository creditApplicationRepository = sqlSession.getMapper(CreditApplicationRepository.class);
            return creditApplicationRepository.findAll();
        }
    }
}