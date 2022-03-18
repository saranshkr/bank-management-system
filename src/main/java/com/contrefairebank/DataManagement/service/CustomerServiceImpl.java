package com.contrefairebank.DataManagement.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.contrefairebank.DataManagement.entities.Account;
import com.contrefairebank.DataManagement.entities.Customer;
import com.contrefairebank.DataManagement.repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public Customer geCustomer(int customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        return optionalCustomer.orElse(null);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findByCustomerEmail(customer.getCustomerEmail());
        if (optionalCustomer.isPresent()) {
            return null;
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomerDetails(int customerId, Customer updatedCustomer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(updatedCustomer.getCustomerId());
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setCustomerFirstName(updatedCustomer.getCustomerFirstName());
            customer.setCustomerLastName(updatedCustomer.getCustomerLastName());
            customer.setCustomerEmail(updatedCustomer.getCustomerEmail());
            customerRepository.save(customer);
            return customer;
        }
        return null;
    }

    @Override
    @Transactional
    public Account addAccountForCustomer(int customerId, Account account) {
        Customer customer = this.geCustomer(customerId);

        Set<Account> accounts = customer.getAccounts();
        accounts.add(account);
        customer.setAccounts(accounts);

        customerRepository.save(customer);

        return account;
    }
    
    
}
