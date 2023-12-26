package com.solvd.bank.domain.exception;

import java.sql.SQLException;

public class CreateConnectionException extends RuntimeException {
    public CreateConnectionException(String message, SQLException cause) {
        super(message, cause);
    }
}