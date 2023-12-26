package com.solvd.bank.service.impl;

import com.solvd.bank.domain.CreditApplicationStatus;
import com.solvd.bank.domain.exception.ServiceException;
import com.solvd.bank.persistence.CreditApplicationStatusRepository;
import com.solvd.bank.service.CreditApplicationStatusService;

public class CreditApplicationStatusServiceImpl implements CreditApplicationStatusService {
    private final CreditApplicationStatusRepository creditApplicationStatusRepository;

    public CreditApplicationStatusServiceImpl(CreditApplicationStatusRepository creditApplicationStatusRepository) {
        this.creditApplicationStatusRepository = creditApplicationStatusRepository;
    }

    @Override
    public void createCreditApplicationStatus(CreditApplicationStatus creditApplicationStatus) {
        try {
            creditApplicationStatusRepository.create(creditApplicationStatus);
        } catch (Exception e) {
            throw new ServiceException("Failed to create credit application status", e);
        }
    }

    @Override
    public void deleteCreditApplicationStatus(Integer creditApplicationStatusId) {
        try {
            creditApplicationStatusRepository.deleteById(creditApplicationStatusId);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete credit application status", e);
        }
    }

    @Override
    public CreditApplicationStatus getCreditApplicationStatusById(Integer creditApplicationStatusId) {
        try {
            return creditApplicationStatusRepository.findById(creditApplicationStatusId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get credit application status by id", e);
        }
    }
}