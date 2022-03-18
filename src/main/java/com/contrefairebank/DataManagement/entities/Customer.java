package com.contrefairebank.DataManagement.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    @Column
    private String customerFirstName;
    @Column
    private String customerLastName;
    @Column(unique = true)
    private String customerEmail;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "customer_accounts", joinColumns = @JoinColumn(name="customer_id"), inverseJoinColumns = @JoinColumn(name="account_id"))
    private Set<Account> accounts = new HashSet<>();

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accounts == null) ? 0 : accounts.hashCode());
        result = prime * result + ((customerEmail == null) ? 0 : customerEmail.hashCode());
        result = prime * result + ((customerFirstName == null) ? 0 : customerFirstName.hashCode());
        result = prime * result + customerId;
        result = prime * result + ((customerLastName == null) ? 0 : customerLastName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (accounts == null) {
            if (other.accounts != null)
                return false;
        } else if (!accounts.equals(other.accounts))
            return false;
        if (customerEmail == null) {
            if (other.customerEmail != null)
                return false;
        } else if (!customerEmail.equals(other.customerEmail))
            return false;
        if (customerFirstName == null) {
            if (other.customerFirstName != null)
                return false;
        } else if (!customerFirstName.equals(other.customerFirstName))
            return false;
        if (customerId != other.customerId)
            return false;
        if (customerLastName == null) {
            if (other.customerLastName != null)
                return false;
        } else if (!customerLastName.equals(other.customerLastName))
            return false;
        return true;
    }

}
