package com.contrefairebank.DataManagement.exceptions;

public class CustomerOverwriteException extends RuntimeException {
    public CustomerOverwriteException(String message) {
        super(message);
    }
}
