package com.solvd.bank;

import com.solvd.bank.domain.*;
import com.solvd.bank.persistence.*;
import com.solvd.bank.persistence.impl.*;
import com.solvd.bank.service.*;
import com.solvd.bank.service.impl.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Application {
    public static void main(String[] args) {
        Bank bank = new Bank("PumbBank", "Kyiv city", "+380956738295");
        BankRepository bankRepository = new BankRepositoryImpl();
        BankService bankService = new BankServiceImpl(bankRepository);
        bankService.createBank(bank);

        BankBranch bankBranch = new BankBranch(bank, "Kyiv city", "380956738295");
        BankBranchRepository bankBranchRepository = new BankBranchRepositoryImpl();
        BankBranchService bankBranchService = new BankBranchServiceImpl(bankBranchRepository, bankService);
        bankBranchService.createBankBranch(bankBranch);

        Position position = new Position("Bank manager");
        PositionRepository positionRepository = new PositionRepositoryImpl();
        PositionService positionService = new PositionServiceImpl(positionRepository);
        positionService.createPosition(position);

        Employee employee = new Employee(bankBranch, position, "Oleksandr", "Franko",
                "Ivanovych", "+380976352849", "oleksandrafranko@gmail.com");
        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
        EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository, bankBranchService, positionService);
        employeeService.createEmployee(employee);

        Customer customer = new Customer("Mykola", "Kylish", "Ivanovych",
                "+380967483474", "mykolaivanovych@gmail.com", "Kyiv city");
        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        CustomerService customerService = new CustomerServiceImpl(customerRepository);
        customerService.createCustomer(customer);

        AccountType accountType = new AccountType("Credit card");
        AccountTypeRepository accountTypeRepository = new AccountTypeRepositoryImpl();
        AccountTypeService accountTypeService = new AccountTypeServiceImpl(accountTypeRepository);
        accountTypeService.createAccountType(accountType);

        Account account = new Account(customer, accountType, bankBranch, 1000);
        AccountRepository accountRepository = new AccountRepositoryImpl();
        AccountService accountService = new AccountServiceImpl(accountRepository, customerService, accountTypeService, bankBranchService);
        accountService.createAccount(account);

        TransactionType transactionType = new TransactionType("Add money");
        TransactionTypeRepository transactionTypeRepository = new TransactionTypeRepositoryImpl();
        TransactionTypeService transactionTypeService = new TransactionTypeServiceImpl(transactionTypeRepository);
        transactionTypeService.createTransactionType(transactionType);

        LocalDateTime localDateTime = LocalDateTime.now();
        Transaction transaction = new Transaction(account, transactionType, 500, localDateTime);
        TransactionRepository transactionRepository = new TransactionRepositoryImpl();
        TransactionService transactionService = new TransactionServiceImpl(transactionRepository, accountService,
                transactionTypeService);
        transactionService.createTransaction(transaction);

        BankOperationType bankOperationType = new BankOperationType("Open credit");
        BankOperationTypeRepository bankOperationTypeRepository = new BankOperationTypeRepositoryImpl();
        BankOperationTypeService bankOperationTypeService = new BankOperationTypeServiceImpl(bankOperationTypeRepository);
        bankOperationTypeService.createBankOperationType(bankOperationType);

        BankOperation bankOperation = new BankOperation(bankOperationType, account, employee, localDateTime);
        BankOperationRepository bankOperationRepository = new BankOperationRepositoryImpl();
        BankOperationService bankOperationService = new BankOperationServiceImpl(bankOperationRepository,
                bankOperationTypeService, accountService);
        bankOperationService.createBankOperation(bankOperation, bankOperation.getEmployee());

        CreditApplicationStatus creditApplicationStatus = new CreditApplicationStatus("OK");
        CreditApplicationStatusRepository creditApplicationStatusRepository = new CreditApplicationStatusRepositoryImpl();
        CreditApplicationStatusService creditApplicationStatusService = new CreditApplicationStatusServiceImpl(creditApplicationStatusRepository);
        creditApplicationStatusService.createCreditApplicationStatus(creditApplicationStatus);

        LocalDate localDate = LocalDate.now();
        CreditApplication creditApplication = new CreditApplication(customer, creditApplicationStatus, localDate);
        CreditApplicationRepository creditApplicationRepository = new CreditApplicationRepositoryImpl();
        CreditApplicationService creditApplicationService = new CreditApplicationServiceImpl(creditApplicationRepository,
                customerService, creditApplicationStatusService);
        creditApplicationService.createCreditApplication(creditApplication);

        CreditAgreementStatus creditAgreementStatus = new CreditAgreementStatus("OK");
        CreditAgreementStatusRepository creditAgreementStatusRepository = new CreditAgreementStatusRepositoryImpl();
        CreditAgreementStatusService creditAgreementStatusService = new CreditAgreementStatusServiceImpl(creditAgreementStatusRepository);
        creditAgreementStatusService.createCreditAgreementStatus(creditAgreementStatus);

        CreditAgreement creditAgreement = new CreditAgreement(creditApplication, creditAgreementStatus, 100_000, 20, 10_000);
        CreditAgreementRepository creditAgreementRepository = new CreditAgreementRepositoryImpl();
        CreditAgreementService creditAgreementService = new CreditAgreementServiceImpl(creditAgreementRepository,
                creditApplicationService, creditAgreementStatusService);
        creditAgreementService.createCreditAgreement(creditAgreement);

        CreditHistory creditHistory = new CreditHistory(creditAgreement, 10_000, localDateTime);
        CreditHistoryRepository creditHistoryRepository = new CreditHistoryRepositoryImpl();
        CreditHistoryService creditHistoryService = new CreditHistoryServiceImpl(creditHistoryRepository, creditAgreementService);
        creditHistoryService.createCreditHistory(creditHistory);

        // deleting

        creditHistoryService.deleteCreditHistory(creditHistory.getPaymentId());
        creditAgreementService.deleteCreditAgreement(creditAgreement.getCreditAgreementId());
        creditApplicationService.deleteCreditApplication(creditApplication.getCreditApplicationId());
        creditAgreementStatusService.deleteCreditAgreementStatus(creditAgreementStatus.getCreditAgreementStatusId());
        creditApplicationStatusService.deleteCreditApplicationStatus(creditApplicationStatus.getCreditApplicationStatusId());
        bankOperationService.deleteBankOperation(bankOperation.getBankOperationId(), bankOperation.getEmployee().getEmployeeId());
        bankOperationTypeService.deleteBankOperationType(bankOperationType.getBankOperationTypeId());
        transactionService.deleteTransaction(transaction.getTransactionId());
        transactionTypeService.deleteTransactionType(transactionType.getTransactionTypeId());
        accountService.deleteAccount(account.getAccountId());
        accountTypeService.deleteAccountType(accountType.getAccountTypeId());
        customerService.deleteCustomer(customer.getCustomerId());
        employeeService.deleteEmployee(employee.getEmployeeId());
        positionService.deletePosition(position.getPositionId());
        bankBranchService.deleteBankBranch(bankBranch.getBankBranchId());
        bankService.deleteBank(bank.getBankId());

    }
}