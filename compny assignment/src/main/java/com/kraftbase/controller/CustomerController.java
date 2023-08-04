package com.kraftbase.controller;

import com.kraftbase.model.Customer;
import com.kraftbase.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CustomerController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerService customerService;


    @GetMapping("/customers/hello")
    public String testHandler() {
        return "Welcome to Spring Security";
    }

    /*

	  {
        "name": "aman",
        "email": "aman@gmail.com",
        "password": "1234",
        "role": "admin"

    }

	 */

    @GetMapping("/signIn")
    public ResponseEntity<Customer> getLoggedInCustomerDetailsHandler(Authentication auth){


        log.info(auth.getName());

        Customer customer= customerService.getCustomerDetailsByEmail(auth.getName());

        return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
    }
    @PostMapping("/customers")
    public ResponseEntity<Customer> saveCustomerHandler(@RequestBody Customer customer){
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRole("ROLE_"+customer.getRole().toUpperCase());
        return ResponseEntity.ok(customerService.addCustomer(customer));
    }

    @GetMapping("/customers/{email}")
    public ResponseEntity<Customer> getCustomerHandler(@PathVariable("email") String email){
        Customer customer= customerService.getCustomerDetailsByEmail(email);
        return ResponseEntity.ok(customer);
    }


    @PatchMapping("/customers/{email}")
    public ResponseEntity<Customer> updateCustomerHandler(@PathVariable("email") String email,@RequestBody Customer customer){

        return ResponseEntity.ok(customerService.updateCustomer(email,customer));
    }

    @DeleteMapping("/customers/{email}")
    public ResponseEntity<String > deleteCustomerHandler(@PathVariable("email") String email){
        return ResponseEntity.ok(customerService.deleteCustomer(email));
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomersHandler(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
}
