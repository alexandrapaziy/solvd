package com.solvd.bank.persistence.implMyBatis;

import com.solvd.bank.domain.AccountType;
import com.solvd.bank.persistence.AccountTypeRepository;
import com.solvd.bank.persistence.config.Config;
import org.apache.ibatis.session.SqlSession;

public class AccountTypeRepositoryImplMyBatis implements AccountTypeRepository {
    @Override
    public void create(AccountType accountType) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            AccountTypeRepository accountTypeRepository = sqlSession.getMapper(AccountTypeRepository.class);
            accountTypeRepository.create(accountType);
        }
    }

    @Override
    public void deleteById(Integer accountTypeId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            AccountTypeRepository accountTypeRepository = sqlSession.getMapper(AccountTypeRepository.class);
            accountTypeRepository.deleteById(accountTypeId);
        }
    }

    @Override
    public AccountType findById(Integer accountTypeId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            AccountTypeRepository accountTypeRepository = sqlSession.getMapper(AccountTypeRepository.class);
            return accountTypeRepository.findById(accountTypeId);
        }
    }
}