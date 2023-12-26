package com.solvd.bank.persistence;

import com.solvd.bank.domain.CreditApplication;

import java.util.List;

public interface CreditApplicationRepository {
    void create(CreditApplication creditApplication);

    void update(CreditApplication creditApplication);

    void deleteById(Long creditApplicationId);

    CreditApplication findById(Long creditApplicationId);

    List<CreditApplication> findAll();
}