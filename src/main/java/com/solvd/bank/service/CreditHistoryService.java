package com.solvd.bank.service;

import com.solvd.bank.domain.CreditHistory;

import java.util.List;

public interface CreditHistoryService {
    void createCreditHistory(CreditHistory creditHistory);

    void updateCreditHistory(CreditHistory creditHistory);

    void deleteCreditHistory(Long creditHistoryId);

    CreditHistory getCreditHistoryById(Long creditHistoryId);

    List<CreditHistory> getAllCreditHistories();
}