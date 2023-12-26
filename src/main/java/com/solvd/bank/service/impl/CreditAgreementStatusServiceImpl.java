package com.solvd.bank.service.impl;

import com.solvd.bank.domain.CreditAgreementStatus;
import com.solvd.bank.domain.exception.ServiceException;
import com.solvd.bank.persistence.CreditAgreementStatusRepository;
import com.solvd.bank.service.CreditAgreementStatusService;

public class CreditAgreementStatusServiceImpl implements CreditAgreementStatusService {
    private final CreditAgreementStatusRepository creditAgreementStatusRepository;

    public CreditAgreementStatusServiceImpl(CreditAgreementStatusRepository creditAgreementStatusRepository) {
        this.creditAgreementStatusRepository = creditAgreementStatusRepository;
    }

    @Override
    public void createCreditAgreementStatus(CreditAgreementStatus creditAgreementStatus) {
        try {
            creditAgreementStatusRepository.create(creditAgreementStatus);
        } catch (Exception e) {
            throw new ServiceException("Failed to create credit agreement status", e);
        }
    }

    @Override
    public void deleteCreditAgreementStatus(Integer creditAgreementStatusId) {
        try {
            creditAgreementStatusRepository.deleteById(creditAgreementStatusId);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete credit agreement status", e);
        }
    }

    @Override
    public CreditAgreementStatus getCreditAgreementStatusById(Integer creditAgreementStatusId) {
        try {
            return creditAgreementStatusRepository.findById(creditAgreementStatusId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get credit agreement status by id", e);
        }
    }
}