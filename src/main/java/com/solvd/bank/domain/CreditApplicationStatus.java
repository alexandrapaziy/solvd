package com.solvd.bank.domain;

public class CreditApplicationStatus {
    private int creditApplicationStatusId;
    private String creditApplicationName;

    public CreditApplicationStatus(String creditApplicationName) {
        this.creditApplicationName = creditApplicationName;
    }

    public int getCreditApplicationStatusId() {
        return creditApplicationStatusId;
    }

    public void setCreditApplicationStatusId(int creditApplicationStatusId) {
        this.creditApplicationStatusId = creditApplicationStatusId;
    }

    public String getCreditApplicationName() {
        return creditApplicationName;
    }

    public void setCreditApplicationName(String creditApplicationName) {
        this.creditApplicationName = creditApplicationName;
    }
}