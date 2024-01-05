package com.solvd.bank.persistence.implMyBatis;

import com.solvd.bank.domain.BankOperationType;
import com.solvd.bank.persistence.BankOperationTypeRepository;
import com.solvd.bank.persistence.config.Config;
import org.apache.ibatis.session.SqlSession;

public class BankOperationTypeRepositoryImplMyBatis implements BankOperationTypeRepository {
    @Override
    public void create(BankOperationType bankOperationType) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BankOperationTypeRepository bankOperationTypeRepository = sqlSession.getMapper(BankOperationTypeRepository.class);
            bankOperationTypeRepository.create(bankOperationType);
        }
    }

    @Override
    public void deleteById(Integer bankOperationTypeId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BankOperationTypeRepository bankOperationTypeRepository = sqlSession.getMapper(BankOperationTypeRepository.class);
            bankOperationTypeRepository.deleteById(bankOperationTypeId);
        }
    }

    @Override
    public BankOperationType findById(Integer bankOperationTypeId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BankOperationTypeRepository bankOperationTypeRepository = sqlSession.getMapper(BankOperationTypeRepository.class);
            return bankOperationTypeRepository.findById(bankOperationTypeId);
        }
    }
}