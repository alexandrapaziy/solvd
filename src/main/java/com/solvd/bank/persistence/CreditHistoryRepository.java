package com.solvd.bank.persistence;

import com.solvd.bank.domain.CreditHistory;

import java.util.List;

public interface CreditHistoryRepository {
    void create(CreditHistory creditHistory);

    void update(CreditHistory creditHistory);

    void deleteById(Long creditHistoryId);

    CreditHistory findById(Long creditHistoryId);

    List<CreditHistory> findAll();
}