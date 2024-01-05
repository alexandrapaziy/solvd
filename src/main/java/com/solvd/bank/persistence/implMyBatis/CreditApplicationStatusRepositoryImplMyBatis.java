package com.solvd.bank.persistence.implMyBatis;

import com.solvd.bank.domain.CreditApplicationStatus;
import com.solvd.bank.persistence.CreditApplicationStatusRepository;
import com.solvd.bank.persistence.config.Config;
import org.apache.ibatis.session.SqlSession;

public class CreditApplicationStatusRepositoryImplMyBatis implements CreditApplicationStatusRepository {
    @Override
    public void create(CreditApplicationStatus creditApplicationStatus) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CreditApplicationStatusRepository creditApplicationStatusRepository = sqlSession.getMapper(CreditApplicationStatusRepository.class);
            creditApplicationStatusRepository.create(creditApplicationStatus);
        }
    }

    @Override
    public void deleteById(Integer creditApplicationStatusId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CreditApplicationStatusRepository creditApplicationStatusRepository = sqlSession.getMapper(CreditApplicationStatusRepository.class);
            creditApplicationStatusRepository.deleteById(creditApplicationStatusId);
        }
    }

    @Override
    public CreditApplicationStatus findById(Integer creditApplicationStatusId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CreditApplicationStatusRepository creditApplicationStatusRepository = sqlSession.getMapper(CreditApplicationStatusRepository.class);
            return creditApplicationStatusRepository.findById(creditApplicationStatusId);
        }
    }
}