package com.solvd.bank.persistence.implMyBatis;

import com.solvd.bank.domain.CreditAgreementStatus;
import com.solvd.bank.persistence.CreditAgreementStatusRepository;
import com.solvd.bank.persistence.config.Config;
import org.apache.ibatis.session.SqlSession;

public class CreditAgreementStatusRepositoryImplMyBatis implements CreditAgreementStatusRepository {
    @Override
    public void create(CreditAgreementStatus creditAgreementStatus) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CreditAgreementStatusRepository creditAgreementStatusRepository = sqlSession.getMapper(CreditAgreementStatusRepository.class);
            creditAgreementStatusRepository.create(creditAgreementStatus);
        }
    }

    @Override
    public void deleteById(Integer creditAgreementStatusId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CreditAgreementStatusRepository creditAgreementStatusRepository = sqlSession.getMapper(CreditAgreementStatusRepository.class);
            creditAgreementStatusRepository.deleteById(creditAgreementStatusId);
        }
    }

    @Override
    public CreditAgreementStatus findById(Integer creditAgreementStatusId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CreditAgreementStatusRepository creditAgreementStatusRepository = sqlSession.getMapper(CreditAgreementStatusRepository.class);
            return creditAgreementStatusRepository.findById(creditAgreementStatusId);
        }
    }
}