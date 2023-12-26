package com.solvd.bank.persistence;

import com.solvd.bank.domain.CreditApplicationStatus;

public interface CreditApplicationStatusRepository {
    void create(CreditApplicationStatus creditApplicationStatus);

    void deleteById(Integer creditApplicationStatusId);

    CreditApplicationStatus findById(Integer creditApplicationStatusId);
}