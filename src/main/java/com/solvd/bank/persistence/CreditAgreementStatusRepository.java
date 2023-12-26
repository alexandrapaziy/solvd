package com.solvd.bank.persistence;

import com.solvd.bank.domain.CreditAgreementStatus;

public interface CreditAgreementStatusRepository {
    void create(CreditAgreementStatus creditAgreementStatus);

    void deleteById(Integer creditAgreementStatusId);

    CreditAgreementStatus findById(Integer creditAgreementStatusId);
}