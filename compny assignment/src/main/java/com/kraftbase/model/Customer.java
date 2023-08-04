package com.kraftbase.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customerId;
    @NotNull(message = "Customer name cannot be null")
    @Size(min = 3, max = 30, message = "Customer name must be between 3 and 30 characters")
    private String name;
    @NotNull(message = "Customer email cannot be null")
    @Size(min = 4, max = 30, message = "Customer email must be between 4 and 30 characters")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Wallet wallet;
    private String role;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    public List<Orders> orders = new ArrayList<>();;
}
