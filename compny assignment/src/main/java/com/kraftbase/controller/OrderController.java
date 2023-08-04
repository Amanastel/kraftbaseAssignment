package com.kraftbase.controller;

import com.kraftbase.model.Orders;
import com.kraftbase.service.CustomerService;
import com.kraftbase.service.OrderService;
import com.kraftbase.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class OrderController {

    @Autowired
    private WalletService walletService;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder/{productId}")
    public ResponseEntity<Orders> placeOrderHandler(@RequestBody Orders orders, @PathVariable Integer productId, Authentication authentication){
        return ResponseEntity.ok(orderService.createOrder(orders,authentication.getName() ,productId));
    }

    @DeleteMapping("/cancelOrder/{orderId}")
    public ResponseEntity<String> cancelOrderHandler(@PathVariable Integer orderId, Authentication authentication){
        return ResponseEntity.ok(orderService.deleteOrder(orderId,authentication.getName()));
    }

   @GetMapping("/getOrder/{id}")
    public ResponseEntity<Orders> getOrderHandler(@PathVariable Integer id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PatchMapping("/updateOrder/{productId}")
    public ResponseEntity<Orders> updateOrderHandler(@RequestBody Orders orders, @PathVariable Integer productId, Authentication authentication){
        return ResponseEntity.ok(orderService.updateOrder(orders,authentication.getName(),productId));
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Orders>> getAllOrdersHandler(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }


}
