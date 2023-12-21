package com.solvd.bank.domain;

public class CreditAgreementStatus {
    private int creditAgreementStatusId;
    private String creditAgreementName;

    public CreditAgreementStatus(String creditAgreementName) {
        this.creditAgreementName = creditAgreementName;
    }

    public int getCreditAgreementStatusId() {
        return creditAgreementStatusId;
    }

    public void setCreditAgreementStatusId(int creditAgreementStatusId) {
        this.creditAgreementStatusId = creditAgreementStatusId;
    }

    public String getCreditAgreementName() {
        return creditAgreementName;
    }

    public void setCreditAgreementName(String creditAgreementName) {
        this.creditAgreementName = creditAgreementName;
    }
}