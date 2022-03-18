package com.contrefairebank.DataManagement.repositories;

import java.util.Optional;

import com.contrefairebank.DataManagement.entities.Customer;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    
    public Optional<Customer> findByCustomerFirstName(String firstName); // return type list<customer>
    public Optional<Customer> findByCustomerLastName(String lastName);
    public Optional<Customer> findByCustomerEmail(String email);
}
