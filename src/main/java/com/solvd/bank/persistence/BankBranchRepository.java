package com.solvd.bank.persistence;

import com.solvd.bank.domain.BankBranch;

import java.util.List;

public interface BankBranchRepository {
    void create(BankBranch bankBranch);

    void update(BankBranch bankBranch);

    void deleteById(Integer bankBranchId);

    BankBranch findById(Integer bankBranchId);

    List<BankBranch> findAll();
}