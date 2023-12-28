package com.solvd.bank;

import com.solvd.bank.domain.*;
import com.solvd.bank.persistence.*;

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
            bankRepository.create(bank);

            BankBranch bankBranch = new BankBranch(bank, "Kyiv city", "380956738295");
            BankBranchRepository bankBranchRepository = session.getMapper(BankBranchRepository.class);
            bankBranchRepository.create(bankBranch);

            Position position = new Position("Bank manager");
            PositionRepository positionRepository = session.getMapper(PositionRepository.class);
            positionRepository.create(position);

            Employee employee = new Employee(bankBranch, position, "Oleksandr", "Franko",
                    "Ivanovych", "+380976352849", "oleksandrafranko@gmail.com");
            EmployeeRepository employeeRepository = session.getMapper(EmployeeRepository.class);
            employeeRepository.create(employee);

            Customer customer = new Customer("Mykola", "Kylish", "Ivanovych",
                    "+380967483474", "mykolaivanovych@gmail.com", "Kyiv city");
            CustomerRepository customerRepository = session.getMapper(CustomerRepository.class);
            customerRepository.create(customer);

            AccountType accountType = new AccountType("Credit card");
            AccountTypeRepository accountTypeRepository = session.getMapper(AccountTypeRepository.class);
            accountTypeRepository.create(accountType);

            Account account = new Account(customer, accountType, bankBranch, 1000);
            AccountRepository accountRepository = session.getMapper(AccountRepository.class);
            accountRepository.create(account);

            TransactionType transactionType = new TransactionType("Add money");
            TransactionTypeRepository transactionTypeRepository = session.getMapper(TransactionTypeRepository.class);
            transactionTypeRepository.create(transactionType);

            LocalDateTime localDateTime = LocalDateTime.now();
            Transaction transaction = new Transaction(account, transactionType, 500, localDateTime);
            TransactionRepository transactionRepository = session.getMapper(TransactionRepository.class);
            transactionRepository.create(transaction);

            BankOperationType bankOperationType = new BankOperationType("Open credit");
            BankOperationTypeRepository bankOperationTypeRepository = session.getMapper(BankOperationTypeRepository.class);
            bankOperationTypeRepository.create(bankOperationType);

            BankOperation bankOperation = new BankOperation(bankOperationType, account, employee, localDateTime);
            BankOperationRepository bankOperationRepository = session.getMapper(BankOperationRepository.class);
            bankOperationRepository.create(bankOperation, employee);
            bankOperationRepository.addEmployeeForBankOperation(bankOperation, employee);

            CreditApplicationStatus creditApplicationStatus = new CreditApplicationStatus("OK");
            CreditApplicationStatusRepository creditApplicationStatusRepository = session.getMapper(CreditApplicationStatusRepository.class);
            creditApplicationStatusRepository.create(creditApplicationStatus);

            LocalDate localDate = LocalDate.now();
            CreditApplication creditApplication = new CreditApplication(customer, creditApplicationStatus, localDate);
            CreditApplicationRepository creditApplicationRepository = session.getMapper(CreditApplicationRepository.class);
            creditApplicationRepository.create(creditApplication);

            CreditAgreementStatus creditAgreementStatus = new CreditAgreementStatus("OK");
            CreditAgreementStatusRepository creditAgreementStatusRepository = session.getMapper(CreditAgreementStatusRepository.class);
            creditAgreementStatusRepository.create(creditAgreementStatus);

            CreditAgreement creditAgreement = new CreditAgreement(creditApplication, creditAgreementStatus, 100_000, 20, 10_000);
            CreditAgreementRepository creditAgreementRepository = session.getMapper(CreditAgreementRepository.class);
            creditAgreementRepository.create(creditAgreement);

            CreditHistory creditHistory = new CreditHistory(creditAgreement, 10_000, localDateTime);
            CreditHistoryRepository creditHistoryRepository = session.getMapper(CreditHistoryRepository.class);
            creditHistoryRepository.create(creditHistory);

            // deleting

            creditHistoryRepository.deleteById(creditHistory.getPaymentId());
            creditAgreementRepository.deleteById(creditAgreement.getCreditAgreementId());
            creditApplicationRepository.deleteById(creditApplication.getCreditApplicationId());
            creditAgreementStatusRepository.deleteById(creditAgreementStatus.getCreditAgreementStatusId());
            creditApplicationStatusRepository.deleteById(creditApplicationStatus.getCreditApplicationStatusId());
            bankOperationRepository.deleteById(bankOperation.getBankOperationId(), bankOperation.getEmployee().getEmployeeId());
            bankOperationRepository.removeEmployeeForBankOperation(bankOperation.getBankOperationId(), bankOperation.getEmployee().getEmployeeId());
            bankOperationTypeRepository.deleteById(bankOperationType.getBankOperationTypeId());
            transactionRepository.deleteById(transaction.getTransactionId());
            transactionTypeRepository.deleteById(transactionType.getTransactionTypeId());
            accountRepository.deleteById(account.getAccountId());
            accountTypeRepository.deleteById(accountType.getAccountTypeId());
            customerRepository.deleteById(customer.getCustomerId());
            employeeRepository.deleteById(employee.getEmployeeId());
            positionRepository.deleteById(position.getPositionId());
            bankBranchRepository.deleteById(bankBranch.getBankBranchId());
            bankRepository.deleteById(bank.getBankId());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}