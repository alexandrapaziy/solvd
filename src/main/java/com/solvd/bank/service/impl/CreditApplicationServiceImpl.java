package com.solvd.bank.service.impl;

import com.solvd.bank.domain.*;
import com.solvd.bank.domain.exception.ServiceException;
import com.solvd.bank.persistence.CreditApplicationRepository;
import com.solvd.bank.service.CreditApplicationService;
import com.solvd.bank.service.CreditApplicationStatusService;
import com.solvd.bank.service.CustomerService;

import java.util.List;

public class CreditApplicationServiceImpl implements CreditApplicationService {
    private final CreditApplicationRepository creditApplicationRepository;
    private final CustomerService customerService;
    private final CreditApplicationStatusService creditApplicationStatusService;

    public CreditApplicationServiceImpl(CreditApplicationRepository creditApplicationRepository,
                                        CustomerService customerService,
                                        CreditApplicationStatusService creditApplicationStatusService) {
        this.creditApplicationRepository = creditApplicationRepository;
        this.customerService = customerService;
        this.creditApplicationStatusService = creditApplicationStatusService;
    }

    @Override
    public void createCreditApplication(CreditApplication creditApplication) {
        try {
            if (creditApplication.getCustomer() != null && creditApplication.getCreditApplicationStatus() != null) {
                Customer customer = customerService.getCustomerById(creditApplication.getCustomer().getCustomerId());
                CreditApplicationStatus creditApplicationStatus = creditApplicationStatusService.getCreditApplicationStatusById(creditApplication.getCreditApplicationStatus().getCreditApplicationStatusId());

                creditApplication.setCustomer(customer);
                creditApplication.setCreditApplicationStatus(creditApplicationStatus);
            }

            creditApplicationRepository.create(creditApplication);
        } catch (Exception e) {
            throw new ServiceException("Failed to create credit application", e);
        }
    }

    @Override
    public void updateCreditApplication(CreditApplication creditApplication) {
        try {
            if (creditApplication.getCustomer() != null && creditApplication.getCreditApplicationStatus() != null) {
                Customer customer = customerService.getCustomerById(creditApplication.getCustomer().getCustomerId());
                CreditApplicationStatus creditApplicationStatus = creditApplicationStatusService.getCreditApplicationStatusById(creditApplication.getCreditApplicationStatus().getCreditApplicationStatusId());

                creditApplication.setCustomer(customer);
                creditApplication.setCreditApplicationStatus(creditApplicationStatus);
            }

            creditApplicationRepository.update(creditApplication);
        } catch (Exception e) {
            throw new ServiceException("Failed to update credit application", e);
        }
    }

    @Override
    public void deleteCreditApplication(Long creditApplicationId) {
        try {
            creditApplicationRepository.deleteById(creditApplicationId);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete credit application", e);
        }
    }

    @Override
    public CreditApplication getCreditApplicationById(Long creditApplicationId) {
        try {
            return creditApplicationRepository.findById(creditApplicationId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get credit application by id", e);
        }
    }

    @Override
    public List<CreditApplication> getAllCreditApplication() {
        try {
            return creditApplicationRepository.findAll();
        } catch (Exception e) {
            throw new ServiceException("Failed to get all credit applications", e);
        }
    }
}