package com.solvd.bank.service.impl;

import com.solvd.bank.domain.*;
import com.solvd.bank.domain.exception.ServiceException;
import com.solvd.bank.persistence.CreditAgreementRepository;
import com.solvd.bank.service.CreditAgreementService;
import com.solvd.bank.service.CreditAgreementStatusService;
import com.solvd.bank.service.CreditApplicationService;

import java.util.List;

public class CreditAgreementServiceImpl implements CreditAgreementService {
    private final CreditAgreementRepository creditAgreementRepository;
    private final CreditApplicationService creditApplicationService;
    private final CreditAgreementStatusService creditAgreementStatusService;

    public CreditAgreementServiceImpl(CreditAgreementRepository creditAgreementRepository,
                                      CreditApplicationService creditApplicationService,
                                      CreditAgreementStatusService creditAgreementStatusService) {
        this.creditAgreementRepository = creditAgreementRepository;
        this.creditApplicationService = creditApplicationService;
        this.creditAgreementStatusService = creditAgreementStatusService;
    }

    @Override
    public void createCreditAgreement(CreditAgreement creditAgreement) {
        try {
            if (creditAgreement.getCreditApplication() != null && creditAgreement.getCreditAgreementStatus() != null) {
                CreditApplication creditApplication = creditApplicationService.getCreditApplicationById(creditAgreement.getCreditApplication().getCreditApplicationId());
                CreditAgreementStatus creditAgreementStatus = creditAgreementStatusService.getCreditAgreementStatusById(creditAgreement.getCreditAgreementStatus().getCreditAgreementStatusId());

                creditAgreement.setCreditApplication(creditApplication);
                creditAgreement.setCreditAgreementStatus(creditAgreementStatus);
            }

            creditAgreementRepository.create(creditAgreement);
        } catch (Exception e) {
            throw new ServiceException("Failed to create credit agreement", e);
        }
    }

    @Override
    public void updateCreditAgreement(CreditAgreement creditAgreement) {
        try {
            if (creditAgreement.getCreditApplication() != null && creditAgreement.getCreditAgreementStatus() != null) {
                CreditApplication creditApplication = creditApplicationService.getCreditApplicationById(creditAgreement.getCreditApplication().getCreditApplicationId());
                CreditAgreementStatus creditAgreementStatus = creditAgreementStatusService.getCreditAgreementStatusById(creditAgreement.getCreditAgreementStatus().getCreditAgreementStatusId());

                creditAgreement.setCreditApplication(creditApplication);
                creditAgreement.setCreditAgreementStatus(creditAgreementStatus);
            }

            creditAgreementRepository.update(creditAgreement);
        } catch (Exception e) {
            throw new ServiceException("Failed to update credit agreement", e);
        }
    }

    @Override
    public void deleteCreditAgreement(Long creditAgreementId) {
        try {
            creditAgreementRepository.deleteById(creditAgreementId);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete credit agreement", e);
        }
    }

    @Override
    public CreditAgreement getCreditAgreementById(Long creditAgreementId) {
        try {
            return creditAgreementRepository.findById(creditAgreementId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get credit agreement by id", e);
        }
    }

    @Override
    public List<CreditAgreement> getAllCreditAgreement() {
        try {
            return creditAgreementRepository.findAll();
        } catch (Exception e) {
            throw new ServiceException("Failed to get all credit agreement", e);
        }
    }
}