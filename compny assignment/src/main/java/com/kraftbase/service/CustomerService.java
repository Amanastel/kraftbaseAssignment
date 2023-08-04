package com.kraftbase.service;

import com.kraftbase.model.Customer;

import java.util.List;

public interface CustomerService {
    public Customer  addCustomer(Customer customer);
    public Customer getCustomerDetailsByEmail(String email);
    public Customer updateCustomer(String email,Customer customer);
    public Customer getCustomer(String email);
    public String  deleteCustomer(String email);
    public List<Customer> getAllCustomers();
}
