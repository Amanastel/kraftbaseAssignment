package com.kraftbase.service;

import com.kraftbase.model.Orders;

import java.util.List;

public interface OrderService {

    public Orders createOrder(Orders order,String email,Integer productId);

    public Orders getOrderById(Integer id);

    public Orders updateOrder(Orders order,String email,Integer productId);

    public String deleteOrder(Integer id,String email);

    public List<Orders> getAllOrders();




}
