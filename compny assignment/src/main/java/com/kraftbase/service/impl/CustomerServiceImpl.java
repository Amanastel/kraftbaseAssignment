package com.kraftbase.service.impl;

import com.kraftbase.exceptions.CustomerException;
import com.kraftbase.model.Customer;
import com.kraftbase.model.Wallet;
import com.kraftbase.repository.CustomerRepo;
import com.kraftbase.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepository;

    @Override
    public Customer addCustomer(Customer customer) {
        if(customer == null)
            throw new CustomerException("Invalid customer details");
        Wallet wallet = new Wallet();
        wallet.setBalance(0.0f);
        wallet.setCustomer(customer);
        customer.setWallet(wallet);
        return customerRepository.save(customer);

    }

    @Override
    public Customer getCustomerDetailsByEmail(String email) {
        return customerRepository.findByEmail(email).orElseThrow(() -> new CustomerException("Customer not found with email: " + email));
    }

    @Override
    public Customer updateCustomer(String email,Customer customer) {
        Customer customer1 = customerRepository.findByEmail(email).orElseThrow(() -> new CustomerException("Customer not found with email: " + email));
        customer1.setName(customer.getName());
        customer1.setEmail(customer.getEmail());
        return customerRepository.save(customer1);
    }

    @Override
    public Customer getCustomer(String email) {
        return customerRepository.findByEmail(email).orElseThrow(() -> new CustomerException("Customer not found with email: " + email));
    }

    @Override
    public String deleteCustomer(String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(() -> new CustomerException("Customer not found with email: " + email));
        customerRepository.delete(customer);
        return "Customer deleted successfully";

    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        if(customerList.isEmpty())
            throw new CustomerException("No customer found");
        return customerList;
    }
}
