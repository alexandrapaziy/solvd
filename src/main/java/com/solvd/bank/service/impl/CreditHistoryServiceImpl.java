package com.solvd.bank.service.impl;

import com.solvd.bank.domain.*;
import com.solvd.bank.domain.exception.ServiceException;
import com.solvd.bank.persistence.CreditHistoryRepository;
import com.solvd.bank.service.CreditAgreementService;
import com.solvd.bank.service.CreditHistoryService;

import java.util.List;

public class CreditHistoryServiceImpl implements CreditHistoryService {
    private final CreditHistoryRepository creditHistoryRepository;
    private final CreditAgreementService creditAgreementService;

    public CreditHistoryServiceImpl(CreditHistoryRepository creditHistoryRepository,
                                    CreditAgreementService creditAgreementService) {
        this.creditHistoryRepository = creditHistoryRepository;
        this.creditAgreementService = creditAgreementService;
    }


    @Override
    public void createCreditHistory(CreditHistory creditHistory) {
        try {
            if (creditHistory.getCreditAgreement() != null) {
                CreditAgreement creditAgreement = creditAgreementService.getCreditAgreementById(creditHistory.getCreditAgreement().getCreditAgreementId());

                creditHistory.setCreditAgreement(creditAgreement);
            }

            creditHistoryRepository.create(creditHistory);
        } catch (Exception e) {
            throw new ServiceException("Failed to create credit history", e);
        }
    }

    @Override
    public void updateCreditHistory(CreditHistory creditHistory) {
        try {
            if (creditHistory.getCreditAgreement() != null) {
                CreditAgreement creditAgreement = creditAgreementService.getCreditAgreementById(creditHistory.getCreditAgreement().getCreditAgreementId());

                creditHistory.setCreditAgreement(creditAgreement);
            }

            creditHistoryRepository.update(creditHistory);
        } catch (Exception e) {
            throw new ServiceException("Failed to update credit history", e);
        }
    }

    @Override
    public void deleteCreditHistory(Long creditHistoryId) {
        try {
            creditHistoryRepository.deleteById(creditHistoryId);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete credit history", e);
        }
    }

    @Override
    public CreditHistory getCreditHistoryById(Long creditHistoryId) {
        try {
            return creditHistoryRepository.findById(creditHistoryId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get credit history by id", e);
        }
    }

    @Override
    public List<CreditHistory> getAllCreditHistories() {
        try {
            return creditHistoryRepository.findAll();
        } catch (Exception e) {
            throw new ServiceException("Failed to get all credit histories", e);
        }
    }
}