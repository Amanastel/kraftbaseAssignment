package com.kraftbase.security;


import com.kraftbase.model.Customer;
import com.kraftbase.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepo customerRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Optional<Customer> user = customerRepo.findByEmail(username);
        if (user.isPresent()) {

            Customer users = user.get();

            List<GrantedAuthority> authorities = new ArrayList<>();

            SimpleGrantedAuthority sga = new SimpleGrantedAuthority(users.getRole());
            authorities.add(sga);

            return new User(users.getEmail(), users.getPassword(), authorities);


        } else
            throw new BadCredentialsException("User Details not found with this username: " + username);

    }
}
