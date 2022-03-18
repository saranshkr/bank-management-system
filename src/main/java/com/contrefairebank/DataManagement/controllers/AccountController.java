package com.contrefairebank.DataManagement.controllers;

import java.util.List;

import com.contrefairebank.DataManagement.constants.TransferStatus;
import com.contrefairebank.DataManagement.entities.Account;
import com.contrefairebank.DataManagement.entities.FundTransferRequestBody;
import com.contrefairebank.DataManagement.exceptions.IdenticalAccountException;
import com.contrefairebank.DataManagement.exceptions.InsufficientFundsException;
import com.contrefairebank.DataManagement.exceptions.NotFoundException;
import com.contrefairebank.DataManagement.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    
    private static final String INSUFFICIENT_FUNDS = "Insufficient funds";
    private static final String IDENTICAL_ACCOUNT_NUMBERS = "Identical account numbers";
    private static final String ACCOUNT_NOT_FOUND = "Account not found";

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/accounts")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping(value = "/accounts/{accountId}")
    public Account getAccount(@PathVariable int accountId) {
        Account account = accountService.getAccount(accountId);
        if (account == null) {
            throw new NotFoundException(ACCOUNT_NOT_FOUND);
        }
        return account;
    }

    public Account createAccount(Account account) {
        return accountService.createAccount(account);
    }

    @PutMapping(value = "/accounts/transferFunds")
    public String transferFunds(@RequestBody FundTransferRequestBody fundTransferRequestBody) {
        String fundTransferStatus = accountService.transferFunds(fundTransferRequestBody);
        if (fundTransferStatus.equals(TransferStatus.IDENTICAL_ACCOUNTS)) {
            throw new IdenticalAccountException(IDENTICAL_ACCOUNT_NUMBERS);
        }
        if (fundTransferStatus.equals(TransferStatus.ID_MISMATCH)) {
            throw new NotFoundException(ACCOUNT_NOT_FOUND);
        }
        if (fundTransferStatus.equals(TransferStatus.INSUFFICIENT_FUNDS)) {
            throw new InsufficientFundsException(INSUFFICIENT_FUNDS);
        }
        return "Fund transfer successful";
    }
}
