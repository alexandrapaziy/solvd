package com.solvd.bank.service;

import com.solvd.bank.domain.CreditApplicationStatus;

public interface CreditApplicationStatusService {
    void createCreditApplicationStatus(CreditApplicationStatus creditApplicationStatus);

    void deleteCreditApplicationStatus(Integer creditApplicationStatusId);

    CreditApplicationStatus getCreditApplicationStatusById(Integer creditApplicationStatusId);
}