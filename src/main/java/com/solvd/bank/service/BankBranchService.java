package com.solvd.bank.service;

import com.solvd.bank.domain.BankBranch;

import java.util.List;

public interface BankBranchService {
    void createBankBranch(BankBranch bankBranch);

    void updateBankBranch(BankBranch bankBranch);

    void deleteBankBranch(Integer bankBranchId);

    BankBranch getBankBranchById(Integer bankBranchId);

    List<BankBranch> getAllABankBranches();
}