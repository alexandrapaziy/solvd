package com.solvd.bank.domain;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "bank")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bank {
    private Long bankId;
    private String bankName;
    private String bankLocation;
    private String bankPhone;

    @XmlElementWrapper(name = "bankBranches")
    @XmlElement(name = "bankBranch")
    private List<BankBranch> bankBranches = new ArrayList<>();

    public Bank() {
    }

    public Bank(String bankName, String bankLocation, String bankPhone) {
        this.bankName = bankName;
        this.bankLocation = bankLocation;
        this.bankPhone = bankPhone;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankLocation() {
        return bankLocation;
    }

    public void setBankLocation(String bankLocation) {
        this.bankLocation = bankLocation;
    }

    public String getBankPhone() {
        return bankPhone;
    }

    public void setBankPhone(String bankPhone) {
        this.bankPhone = bankPhone;
    }

    public List<BankBranch> getBankBranches() {
        return bankBranches;
    }

    public void setBankBranches(List<BankBranch> bankBranches) {
        this.bankBranches = bankBranches;
    }
}