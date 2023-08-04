package com.kraftbase.repository;


import com.kraftbase.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepo extends JpaRepository<Orders, Integer> {
}
