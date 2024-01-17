package com.solvd.bank.pattern.factory;

import com.solvd.bank.persistence.BankRepository;
import com.solvd.bank.persistence.impl.BankRepositoryImpl;
import com.solvd.bank.persistence.implMyBatis.BankRepositoryImplMyBatis;

public class RepositoriesFactory {
    public static BankRepository createRepository(RepositoryType repositoryType) {
        BankRepository bankRepository;

        switch (repositoryType) {
            case JDBC:
                bankRepository = new BankRepositoryImpl();
                break;
            case MyBatis:
                bankRepository = new BankRepositoryImplMyBatis();
                break;
            default:
                throw new IllegalArgumentException("Unknown repository type: " + repositoryType);
        }

        return bankRepository;
    }
}