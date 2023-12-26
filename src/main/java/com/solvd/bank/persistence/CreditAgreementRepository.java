package com.solvd.bank.persistence;

import com.solvd.bank.domain.CreditAgreement;

import java.util.List;

public interface CreditAgreementRepository {
    void create(CreditAgreement creditAgreement);

    void update(CreditAgreement creditAgreement);

    void deleteById(Long creditAgreementId);

    CreditAgreement findById(Long creditAgreementId);

    List<CreditAgreement> findAll();
}