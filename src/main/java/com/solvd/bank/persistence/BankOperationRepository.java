package com.solvd.bank.persistence;

import com.solvd.bank.domain.BankOperation;
import com.solvd.bank.domain.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BankOperationRepository {
    void create(@Param("bankOperation") BankOperation bankOperation, @Param("employee") Employee employee);


    void update(@Param("bankOperation") BankOperation bankOperation, @Param("employee") Employee employee);

    void deleteById(@Param("bankOperationId")Integer bankOperationId, @Param("employeeId")Integer employeeId);

    BankOperation findById(Integer bankOperationId);

    List<BankOperation> findAll();

    // many-to-many

    void addEmployeeForBankOperation(@Param("bankOperation") BankOperation bankOperation, @Param("employee") Employee employee);

    void removeEmployeeForBankOperation(@Param("bankOperationId")Integer bankOperationId, @Param("employeeId")Integer employeeId);

    Employee getCurrentEmployee(Integer bankOperationId);
}