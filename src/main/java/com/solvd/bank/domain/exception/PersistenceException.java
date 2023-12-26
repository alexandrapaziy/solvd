package com.solvd.bank.domain.exception;

import java.sql.SQLException;

public class PersistenceException extends RuntimeException {
    public PersistenceException(String message, SQLException cause) {
        super(message, cause);
    }
}