package com.kraftbase.repository;


import com.kraftbase.model.Availability;
import com.kraftbase.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findByProductName(String productName);

}
