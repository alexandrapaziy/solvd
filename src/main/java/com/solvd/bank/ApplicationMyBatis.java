package com.solvd.bank;

import com.solvd.bank.domain.*;
import com.solvd.bank.persistence.*;

import com.solvd.bank.service.*;
import com.solvd.bank.service.impl.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ApplicationMyBatis {
    public static void main(String[] args) {
        try (InputStream inputStream = Resources.getResourceAsStream("myBatis-config.xml");) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = factory.openSession(true);

            Bank bank = new Bank("PumbBank", "Kyiv city", "+380956738295");
            BankRepository bankRepository = session.getMapper(BankRepository.class);
            BankService bankService = new BankServiceImpl(bankRepository);
            bankService.createBank(bank);

            BankBranch bankBranch = new BankBranch(bank, "Kyiv city", "380956738295");
            BankBranchRepository bankBranchRepository = session.getMapper(BankBranchRepository.class);
            BankBranchService bankBranchService = new BankBranchServiceImpl(bankBranchRepository, bankService);
            bankBranchService.createBankBranch(bankBranch);

            Position position = new Position("Bank manager");
            PositionRepository positionRepository = session.getMapper(PositionRepository.class);
            PositionService positionService = new PositionServiceImpl(positionRepository);
            positionService.createPosition(position);

            Employee employee = new Employee(bankBranch, position, "Oleksandr", "Franko",
                    "Ivanovych", "+380976352849", "oleksandrafranko@gmail.com");
            EmployeeRepository employeeRepository = session.getMapper(EmployeeRepository.class);
            EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository, bankBranchService, positionService);
            employeeService.createEmployee(employee);

            Customer customer = new Customer("Mykola", "Kylish", "Ivanovych",
                    "+380967483474", "mykolaivanovych@gmail.com", "Kyiv city");
            CustomerRepository customerRepository = session.getMapper(CustomerRepository.class);
            CustomerService customerService = new CustomerServiceImpl(customerRepository);
            customerService.createCustomer(customer);

            AccountType accountType = new AccountType("Credit card");
            AccountTypeRepository accountTypeRepository = session.getMapper(AccountTypeRepository.class);
            AccountTypeService accountTypeService = new AccountTypeServiceImpl(accountTypeRepository);
            accountTypeService.createAccountType(accountType);

            Account account = new Account(customer, accountType, bankBranch, 1000);
            AccountRepository accountRepository = session.getMapper(AccountRepository.class);
            AccountService accountService = new AccountServiceImpl(accountRepository, customerService, accountTypeService, bankBranchService);
            accountService.createAccount(account);

            TransactionType transactionType = new TransactionType("Add money");
            TransactionTypeRepository transactionTypeRepository = session.getMapper(TransactionTypeRepository.class);
            TransactionTypeService transactionTypeService = new TransactionTypeServiceImpl(transactionTypeRepository);
            transactionTypeService.createTransactionType(transactionType);

            LocalDateTime localDateTime = LocalDateTime.now();
            Transaction transaction = new Transaction(account, transactionType, 500, localDateTime);
            TransactionRepository transactionRepository = session.getMapper(TransactionRepository.class);
            TransactionService transactionService = new TransactionServiceImpl(transactionRepository, accountService,
                    transactionTypeService);
            transactionService.createTransaction(transaction);

            BankOperationType bankOperationType = new BankOperationType("Open credit");
            BankOperationTypeRepository bankOperationTypeRepository = session.getMapper(BankOperationTypeRepository.class);
            BankOperationTypeService bankOperationTypeService = new BankOperationTypeServiceImpl(bankOperationTypeRepository);
            bankOperationTypeService.createBankOperationType(bankOperationType);

            BankOperation bankOperation = new BankOperation(bankOperationType, account, employee, localDateTime);
            BankOperationRepository bankOperationRepository = session.getMapper(BankOperationRepository.class);
            BankOperationService bankOperationService = new BankOperationServiceImpl(bankOperationRepository,
                    bankOperationTypeService, accountService);
            bankOperationService.createBankOperation(bankOperation, bankOperation.getEmployee());

            CreditApplicationStatus creditApplicationStatus = new CreditApplicationStatus("OK");
            CreditApplicationStatusRepository creditApplicationStatusRepository = session.getMapper(CreditApplicationStatusRepository.class);
            CreditApplicationStatusService creditApplicationStatusService = new CreditApplicationStatusServiceImpl(creditApplicationStatusRepository);
            creditApplicationStatusService.createCreditApplicationStatus(creditApplicationStatus);

            LocalDate localDate = LocalDate.now();
            CreditApplication creditApplication = new CreditApplication(customer, creditApplicationStatus, localDate);
            CreditApplicationRepository creditApplicationRepository = session.getMapper(CreditApplicationRepository.class);
            CreditApplicationService creditApplicationService = new CreditApplicationServiceImpl(creditApplicationRepository,
                    customerService, creditApplicationStatusService);
            creditApplicationService.createCreditApplication(creditApplication);

            CreditAgreementStatus creditAgreementStatus = new CreditAgreementStatus("OK");
            CreditAgreementStatusRepository creditAgreementStatusRepository = session.getMapper(CreditAgreementStatusRepository.class);
            CreditAgreementStatusService creditAgreementStatusService = new CreditAgreementStatusServiceImpl(creditAgreementStatusRepository);
            creditAgreementStatusService.createCreditAgreementStatus(creditAgreementStatus);

            CreditAgreement creditAgreement = new CreditAgreement(creditApplication, creditAgreementStatus, 100_000, 20, 10_000);
            CreditAgreementRepository creditAgreementRepository = session.getMapper(CreditAgreementRepository.class);
            CreditAgreementService creditAgreementService = new CreditAgreementServiceImpl(creditAgreementRepository,
                    creditApplicationService, creditAgreementStatusService);
            creditAgreementService.createCreditAgreement(creditAgreement);

            CreditHistory creditHistory = new CreditHistory(creditAgreement, 10_000, localDateTime);
            CreditHistoryRepository creditHistoryRepository = session.getMapper(CreditHistoryRepository.class);
            CreditHistoryService creditHistoryService = new CreditHistoryServiceImpl(creditHistoryRepository, creditAgreementService);
            creditHistoryService.createCreditHistory(creditHistory);

            // deleting

//            creditHistoryService.deleteById(creditHistory.getPaymentId());
//            creditAgreementService.deleteById(creditAgreement.getCreditAgreementId());
//            creditApplicationRepository.deleteById(creditApplication.getCreditApplicationId());
//            creditAgreementStatusRepository.deleteById(creditAgreementStatus.getCreditAgreementStatusId());
//            creditApplicationStatusRepository.deleteById(creditApplicationStatus.getCreditApplicationStatusId());
//            bankOperationRepository.deleteById(bankOperation.getBankOperationId(), bankOperation.getEmployee().getEmployeeId());
//            bankOperationRepository.removeEmployeeForBankOperation(bankOperation.getBankOperationId(), bankOperation.getEmployee().getEmployeeId());
//            bankOperationTypeRepository.deleteById(bankOperationType.getBankOperationTypeId());
//            transactionRepository.deleteById(transaction.getTransactionId());
//            transactionTypeRepository.deleteById(transactionType.getTransactionTypeId());
//            accountRepository.deleteById(account.getAccountId());
//            accountTypeRepository.deleteById(accountType.getAccountTypeId());
//            customerRepository.deleteById(customer.getCustomerId());
//            employeeRepository.deleteById(employee.getEmployeeId());
//            positionRepository.deleteById(position.getPositionId());
//            bankBranchRepository.deleteById(bankBranch.getBankBranchId());
//            bankRepository.deleteById(bank.getBankId());

            creditHistoryService.deleteCreditHistory(creditHistory.getPaymentId());
            creditAgreementService.deleteCreditAgreement(creditAgreement.getCreditAgreementId());
            creditApplicationService.deleteCreditApplication(creditApplication.getCreditApplicationId());
            creditAgreementStatusService.deleteCreditAgreementStatus(creditAgreementStatus.getCreditAgreementStatusId());
            creditApplicationStatusService.deleteCreditApplicationStatus(creditApplicationStatus.getCreditApplicationStatusId());
            bankOperationRepository.deleteById(bankOperation.getBankOperationId(), bankOperation.getEmployee().getEmployeeId());
            bankOperationRepository.removeEmployeeForBankOperation(bankOperation.getBankOperationId(), bankOperation.getEmployee().getEmployeeId());
            bankOperationTypeRepository.deleteById(bankOperationType.getBankOperationTypeId());
            transactionService.deleteTransaction(transaction.getTransactionId());
            transactionTypeService.deleteTransactionType(transactionType.getTransactionTypeId());
            accountService.deleteAccount(account.getAccountId());
            accountTypeService.deleteAccountType(accountType.getAccountTypeId());
            customerService.deleteCustomer(customer.getCustomerId());
            employeeService.deleteEmployee(employee.getEmployeeId());
            positionService.deletePosition(position.getPositionId());
            bankBranchService.deleteBankBranch(bankBranch.getBankBranchId());
            bankService.deleteBank(bank.getBankId());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}