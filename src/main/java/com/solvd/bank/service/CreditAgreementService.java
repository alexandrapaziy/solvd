package com.solvd.bank.service;

import com.solvd.bank.domain.CreditAgreement;

import java.util.List;

public interface CreditAgreementService {
    void createCreditAgreement(CreditAgreement creditAgreement);

    void updateCreditAgreement(CreditAgreement creditAgreement);

    void deleteCreditAgreement(Long creditAgreementId);

    CreditAgreement getCreditAgreementById(Long creditAgreementId);

    List<CreditAgreement> getAllCreditAgreement();
}