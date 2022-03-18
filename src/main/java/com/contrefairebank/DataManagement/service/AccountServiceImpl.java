package com.contrefairebank.DataManagement.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.contrefairebank.DataManagement.constants.TransferStatus;
import com.contrefairebank.DataManagement.entities.Account;
import com.contrefairebank.DataManagement.entities.Customer;
import com.contrefairebank.DataManagement.entities.FundTransferRequestBody;
import com.contrefairebank.DataManagement.repositories.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAllAccounts() {
        return (List<Account>) accountRepository.findAll();
    }

    @Override
    public Account getAccount(int accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        return optionalAccount.orElse(null);
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account addCustomerForAccount(int accountId, Customer customer) {
        Account account = this.getAccount(accountId);
        Set<Customer> customers = account.getCustomers();
        customers.add(customer);
        account.setCustomers(customers);

        accountRepository.save(account);

        return account;
    }

    @Override
    public String transferFunds(FundTransferRequestBody fundTransferRequestBody) {
        if (fundTransferRequestBody.getFromAccountNo() == fundTransferRequestBody.getToAccountNo()) {
            return TransferStatus.IDENTICAL_ACCOUNTS;
        }
        Account fromAccount = this.getAccount(fundTransferRequestBody.getFromAccountNo());
        if (fromAccount == null) {
            return TransferStatus.ID_MISMATCH;
        }
        Account toAccount = this.getAccount(fundTransferRequestBody.getToAccountNo());
        if (toAccount == null) {
            return TransferStatus.ID_MISMATCH;
        }
        if (fromAccount.getBalance() < fundTransferRequestBody.getAmount() + 10_000) {
            return TransferStatus.INSUFFICIENT_FUNDS;
        }

        double balance = fromAccount.getBalance();
        balance -= fundTransferRequestBody.getAmount();
        fromAccount.setBalance(balance);

        balance = toAccount.getBalance();
        balance += fundTransferRequestBody.getAmount();
        toAccount.setBalance(balance);

        return TransferStatus.SUCCESS;
    }
    
}
