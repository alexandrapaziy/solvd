package com.solvd.bank.persistence;


import com.solvd.bank.domain.BankOperationType;

public interface BankOperationTypeRepository {
    void create(BankOperationType bankOperationType);

    void deleteById(Integer bankOperationTypeId);

    BankOperationType findById(Integer bankOperationTypeId);
}