package com.solvd.bank.persistence.implMyBatis;

import com.solvd.bank.domain.CreditAgreement;
import com.solvd.bank.persistence.CreditAgreementRepository;
import com.solvd.bank.persistence.config.Config;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CreditAgreementRepositoryImplMyBatis implements CreditAgreementRepository {
    @Override
    public void create(CreditAgreement creditAgreement) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CreditAgreementRepository creditAgreementRepository = sqlSession.getMapper(CreditAgreementRepository.class);
            creditAgreementRepository.create(creditAgreement);
        }
    }

    @Override
    public void update(CreditAgreement creditAgreement) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CreditAgreementRepository creditAgreementRepository = sqlSession.getMapper(CreditAgreementRepository.class);
            creditAgreementRepository.update(creditAgreement);
        }
    }

    @Override
    public void deleteById(Long creditAgreementId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CreditAgreementRepository creditAgreementRepository = sqlSession.getMapper(CreditAgreementRepository.class);
            creditAgreementRepository.deleteById(creditAgreementId);
        }
    }

    @Override
    public CreditAgreement findById(Long creditAgreementId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CreditAgreementRepository creditAgreementRepository = sqlSession.getMapper(CreditAgreementRepository.class);
            return creditAgreementRepository.findById(creditAgreementId);
        }
    }

    @Override
    public List<CreditAgreement> findAll() {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CreditAgreementRepository creditAgreementRepository = sqlSession.getMapper(CreditAgreementRepository.class);
            return creditAgreementRepository.findAll();
        }
    }
}