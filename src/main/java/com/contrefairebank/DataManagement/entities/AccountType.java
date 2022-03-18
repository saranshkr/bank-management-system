package com.contrefairebank.DataManagement.entities;

public enum AccountType {
    CURRENT("Current"),
    SAVINGS("Savings");

    private final String accountType;

    private AccountType(String accountType) {
        this.accountType = accountType;
    }
}
