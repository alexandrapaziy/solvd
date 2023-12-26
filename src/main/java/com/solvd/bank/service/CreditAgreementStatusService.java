package com.solvd.bank.service;

import com.solvd.bank.domain.CreditAgreementStatus;

public interface CreditAgreementStatusService {
    void createCreditAgreementStatus(CreditAgreementStatus creditAgreementStatus);

    void deleteCreditAgreementStatus(Integer creditAgreementStatusId);

    CreditAgreementStatus getCreditAgreementStatusById(Integer creditAgreementStatusId);
}