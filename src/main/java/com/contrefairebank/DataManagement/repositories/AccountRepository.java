package com.contrefairebank.DataManagement.repositories;

import java.util.List;

import com.contrefairebank.DataManagement.entities.Account;
import com.contrefairebank.DataManagement.entities.AccountType;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    
    public List<Account> findByAccountType(AccountType accountType);
    public List<Account> findByBalance(double balance);
}
