package com.solvd.bank.persistence.implMyBatis;

import com.solvd.bank.domain.BankBranch;
import com.solvd.bank.persistence.BankBranchRepository;
import com.solvd.bank.persistence.config.Config;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BankBranchRepositoryImplMybatis implements BankBranchRepository {
    @Override
    public void create(BankBranch bankBranch) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BankBranchRepository bankBranchRepository = sqlSession.getMapper(BankBranchRepository.class);
            bankBranchRepository.create(bankBranch);
        }
    }

    @Override
    public void update(BankBranch bankBranch) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BankBranchRepository bankBranchRepository = sqlSession.getMapper(BankBranchRepository.class);
            bankBranchRepository.update(bankBranch);
        }
    }

    @Override
    public void deleteById(Integer bankBranchId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BankBranchRepository bankBranchRepository = sqlSession.getMapper(BankBranchRepository.class);
            bankBranchRepository.deleteById(bankBranchId);
        }
    }

    @Override
    public BankBranch findById(Integer bankBranchId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BankBranchRepository bankBranchRepository = sqlSession.getMapper(BankBranchRepository.class);
            return bankBranchRepository.findById(bankBranchId);
        }
    }

    @Override
    public List<BankBranch> findAll() {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BankBranchRepository bankBranchRepository = sqlSession.getMapper(BankBranchRepository.class);
            return bankBranchRepository.findAll();
        }
    }
}