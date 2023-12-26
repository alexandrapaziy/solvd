package com.solvd.bank.domain;

public class CreditApplicationStatus {
    private Integer creditApplicationStatusId;
    private String creditApplicationStatusName;

    public CreditApplicationStatus(String creditApplicationStatusName) {
        this.creditApplicationStatusName = creditApplicationStatusName;
    }

    public Integer getCreditApplicationStatusId() {
        return creditApplicationStatusId;
    }

    public void setCreditApplicationStatusId(Integer creditApplicationStatusId) {
        this.creditApplicationStatusId = creditApplicationStatusId;
    }

    public String getCreditApplicationStatusName() {
        return creditApplicationStatusName;
    }

    public void setCreditApplicationStatusName(String creditApplicationStatusName) {
        this.creditApplicationStatusName = creditApplicationStatusName;
    }
}