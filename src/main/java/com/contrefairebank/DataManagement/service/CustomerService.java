package com.contrefairebank.DataManagement.service;

import java.util.List;

import com.contrefairebank.DataManagement.entities.Account;
import com.contrefairebank.DataManagement.entities.Customer;

public interface CustomerService {

    public List<Customer> getAllCustomers();

    public Customer geCustomer(int customerId);

    public Customer createCustomer(Customer customer);
    
    public Customer updateCustomerDetails(int customerId, Customer customer);

    public Account addAccountForCustomer(int customerId, Account account);
}
