package com.kraftbase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    @NotNull(message = "quantity cannot be null")
    private Integer quantity;
    @NotNull(message = "Total order price cannot be null")
    private Float totalOrderPrice;
    @NotNull(message = "Order date cannot be null")
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JsonIgnore
    private Customer customer;
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

}
