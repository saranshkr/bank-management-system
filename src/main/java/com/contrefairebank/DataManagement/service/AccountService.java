package com.contrefairebank.DataManagement.service;

import java.util.List;

import com.contrefairebank.DataManagement.entities.Account;
import com.contrefairebank.DataManagement.entities.Customer;
import com.contrefairebank.DataManagement.entities.FundTransferRequestBody;

public interface AccountService {
    
    public List<Account> getAllAccounts();

    public Account getAccount(int accountId);

    public Account createAccount(Account account);

    public Account addCustomerForAccount(int accountId, Customer customer);

    public String transferFunds(FundTransferRequestBody fundTransferRequestBody);
}
