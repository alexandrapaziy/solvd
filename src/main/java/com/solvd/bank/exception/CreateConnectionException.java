package com.solvd.bank.exception;

import java.sql.SQLException;

public class CreateConnectionException extends RuntimeException {
    public CreateConnectionException(String message, SQLException cause) {
        super(message, cause);
    }
}