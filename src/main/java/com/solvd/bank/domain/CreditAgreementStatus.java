package com.solvd.bank.domain;

public class CreditAgreementStatus {
    private Integer creditAgreementStatusId;
    private String creditAgreementStatusName;

    public CreditAgreementStatus() {
    }

    public CreditAgreementStatus(String creditAgreementStatusName) {
        this.creditAgreementStatusName = creditAgreementStatusName;
    }

    public Integer getCreditAgreementStatusId() {
        return creditAgreementStatusId;
    }

    public void setCreditAgreementStatusId(Integer creditAgreementStatusId) {
        this.creditAgreementStatusId = creditAgreementStatusId;
    }

    public String getCreditAgreementStatusName() {
        return creditAgreementStatusName;
    }

    public void setCreditAgreementStatusName(String creditAgreementStatusName) {
        this.creditAgreementStatusName = creditAgreementStatusName;
    }
}