package com.solvd.bank.persistence.implMyBatis;

import com.solvd.bank.domain.Account;
import com.solvd.bank.persistence.AccountRepository;
import com.solvd.bank.persistence.config.Config;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AccountRepositoryImplMyBatis implements AccountRepository {
    @Override
    public void create(Account account) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);
            accountRepository.create(account);
        }
    }

    @Override
    public void update(Account account) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);
            accountRepository.update(account);
        }
    }

    @Override
    public void deleteById(Long accountId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);
            accountRepository.deleteById(accountId);
        }
    }

    @Override
    public Account findById(Long accountId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);
            return accountRepository.findById(accountId);
        }
    }

    @Override
    public List<Account> findAll() {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);
            return accountRepository.findAll();
        }
    }
}