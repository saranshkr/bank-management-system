package com.contrefairebank.DataManagement.exceptions;

public class IdenticalAccountException extends RuntimeException {
    public IdenticalAccountException(String message) {
        super(message);
    }
}
