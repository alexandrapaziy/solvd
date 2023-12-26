package com.solvd.bank.service;

import com.solvd.bank.domain.CreditApplication;

import java.util.List;

public interface CreditApplicationService {
    void createCreditApplication(CreditApplication creditApplication);

    void updateCreditApplication(CreditApplication creditApplication);

    void deleteCreditApplication(Long creditApplicationId);

    CreditApplication getCreditApplicationById(Long creditApplicationId);

    List<CreditApplication> getAllCreditApplication();
}