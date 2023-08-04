package com.kraftbase.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productID;

    @NotNull(message = "Product name cannot be null")
    private String productName;

    @NotNull(message = "Product price cannot be null")
    private Float productPrice;
    @NotNull(message = "Product quantity cannot be null")
    private Integer productQuantity;

    @Enumerated(EnumType.STRING)
    private Availability availability;

    @ManyToOne
    @JsonIgnore
    private Orders orders;

}
