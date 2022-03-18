package com.contrefairebank.DataManagement.controllers;

import java.util.List;

import com.contrefairebank.DataManagement.entities.Account;
import com.contrefairebank.DataManagement.entities.Customer;
import com.contrefairebank.DataManagement.exceptions.CustomerOverwriteException;
import com.contrefairebank.DataManagement.exceptions.NotFoundException;
import com.contrefairebank.DataManagement.service.AccountService;
import com.contrefairebank.DataManagement.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/customers")
    public List<Customer> getAllCustomers() {
        List<Customer> allCustomers = customerService.getAllCustomers();
        return allCustomers;
    }

    @GetMapping(value = "/customers/{customerId}")
    public Customer geCustomer(@PathVariable int customerId) {
        Customer customer = customerService.geCustomer(customerId);
        if (customer == null) {
            throw new NotFoundException("Customer not found.");
        }
        return customer;
    }

    @PostMapping(value = "/customers")
    public Customer createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        if (createdCustomer == null) {
            throw new CustomerOverwriteException("Cannot create customer. Customer already exists.");
        }
        return createdCustomer;
    }

    @PutMapping(value = "/customers/{customerId}")
    public Customer updateCustomerDetails(@PathVariable int customerId, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomerDetails(customerId, customer);
        if (updatedCustomer == null) {
            throw new NotFoundException("Customer not found.");
        }
        return updatedCustomer;
    }

    @PostMapping(value = "/customers/{customerId}/accounts")
    public Account addAccount(@PathVariable int customerId, @RequestBody Account account) {
        Customer customer = customerService.geCustomer(customerId);
        if (customer == null) {
            throw new NotFoundException("Customer not found.");
        }
        Account acc = accountService.getAccount(account.getAccountId());
        if (acc == null) {
            accountService.createAccount(account);
        }
        acc = accountService.addCustomerForAccount(account.getAccountId(), customer);
        return customerService.addAccountForCustomer(customerId, acc);
    }
}
