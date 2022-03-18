package com.contrefairebank.DataManagement.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleNotFoundException(NotFoundException e) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse("NOT_FOUND_EXCEPTION", e.getMessage());
        customErrorResponse.setStatus(HttpStatus.NOT_FOUND.toString());
        customErrorResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerOverwriteException.class)
    public ResponseEntity<CustomErrorResponse> handleCustomerOverwriteException(CustomerOverwriteException e) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse("CUSTOMER_OVERWRITE_EXCEPTION", e.getMessage());
        customErrorResponse.setStatus(HttpStatus.CONFLICT.toString());
        customErrorResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<CustomErrorResponse> handleInsufficientFundsException(InsufficientFundsException e) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse("INSUFFICIENT_FUNDS_EXCEPTION", e.getMessage());
        customErrorResponse.setStatus(HttpStatus.BAD_REQUEST.toString());
        customErrorResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdenticalAccountException.class)
    public ResponseEntity<CustomErrorResponse> handleIdenticalAccountException(IdenticalAccountException e) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse("IDENTICAL_ACCOUNTS_EXCEPTION", e.getMessage());
        customErrorResponse.setStatus(HttpStatus.BAD_REQUEST.toString());
        customErrorResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    
}
