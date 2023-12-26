package com.solvd.bank.service;

import com.solvd.bank.domain.BankOperationType;

public interface BankOperationTypeService {
    void createBankOperationType(BankOperationType bankOperationType);

    void deleteBankOperationType(Integer bankOperationTypeId);

    BankOperationType getBankOperationTypeById(Integer bankOperationTypeId);
}