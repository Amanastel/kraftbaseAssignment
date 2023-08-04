package com.kraftbase.service.impl;

import com.kraftbase.exceptions.WalletException;
import com.kraftbase.model.*;
import com.kraftbase.repository.CustomerRepo;
import com.kraftbase.repository.OrderRepo;
import com.kraftbase.repository.ProductRepo;
import com.kraftbase.repository.WalletRepo;
import com.kraftbase.service.OrderService;
import com.kraftbase.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private WalletRepo walletRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ProductRepo productRepo;

    @Override
    public Orders createOrder(Orders order, String email,Integer productId) {
        if(order==null || email==null)
            throw new WalletException("Invalid Details");

        Customer customer = customerRepo.findByEmail(email).orElseThrow(()->new WalletException("No Customer Found"));
        Wallet wallet = customer.getWallet();
        List<Product> productList = productRepo.findAll();
        Product product= productRepo.findById(productId).orElseThrow(()->new WalletException("No Product Found"));
        order.setTotalOrderPrice(product.getProductPrice()*order.getQuantity());
        order.setOrderDate(java.time.LocalDateTime.now());
        order.getProducts().add(product);
        order.setCustomer(customer);
        order.setStatus(Status.ORDERED);
//        productList.remove(product);
        product.setProductQuantity(product.getProductQuantity()-order.getQuantity());
        productList.add(product);
        Transactions transactions = new Transactions();
        if(wallet.getBalance()<order.getTotalOrderPrice())
            throw new WalletException("Insufficient Balance");

        wallet.setBalance(wallet.getBalance()-order.getTotalOrderPrice());
        transactions.setAmount(order.getTotalOrderPrice());
        transactions.setTransactionDate(order.getOrderDate());
        transactions.setType(TransactionType.Debit);
        wallet.getTransactions().add(transactions);
        walletRepo.save(wallet);

        return orderRepo.save(order);
    }


    @Override
    public Orders getOrderById(Integer id) {
        if(id==null)
            throw new WalletException("Invalid Details");
        return orderRepo.findById(id).orElseThrow(() -> new WalletException("No Order Found"));
    }

    @Override
    public Orders updateOrder(Orders order,String email,Integer productId) {
        if (order == null)
            throw new WalletException("Invalid Details");

        Orders ob = orderRepo.findById(order.getOrderId()).orElseThrow(() -> new WalletException("No Order Found"));
        ob.setOrderDate(order.getOrderDate());
        ob.setTotalOrderPrice(order.getTotalOrderPrice());
        ob.setStatus(order.getStatus());

        Customer customer = customerRepo.findByEmail(email).orElseThrow(()->new WalletException("No Customer Found"));
        Wallet wallet = customer.getWallet();

        List<Product> productList = productRepo.findAll();
        Product product= productRepo.findById(productId).orElseThrow(()->new WalletException("No Product Found"));
        order.setTotalOrderPrice(product.getProductPrice()*order.getQuantity());
        order.setOrderDate(java.time.LocalDateTime.now());
        order.getProducts().add(product);
        order.setCustomer(customer);
        order.setStatus(Status.ORDERED);
        order.setProducts(ob.getProducts());
//        productList.remove(product);
        product.setProductQuantity(product.getProductQuantity()-order.getQuantity());


        Transactions transactions = new Transactions();
        if(wallet.getBalance()<order.getTotalOrderPrice())
            throw new WalletException("Insufficient Balance");

        wallet.setBalance(wallet.getBalance()-order.getTotalOrderPrice());
        transactions.setAmount(order.getTotalOrderPrice());
        transactions.setTransactionDate(order.getOrderDate());
        transactions.setType(TransactionType.Debit);
        wallet.getTransactions().add(transactions);
        walletRepo.save(wallet);
        return orderRepo.save(ob);
    }

    @Override
    public String deleteOrder(Integer id,String email) {
        if(id==null)
            throw new WalletException("Invalid Details");
        Orders ob = orderRepo.findById(id).orElseThrow(() -> new WalletException("No Order Found"));
        Wallet wallet= customerRepo.findByEmail(email).orElseThrow(()->new WalletException("No Customer Found")).getWallet();

        Transactions transactions = new Transactions();
        if(wallet.getBalance()<ob.getTotalOrderPrice())
            throw new WalletException("Insufficient Balance");

        wallet.setBalance(wallet.getBalance()-ob.getTotalOrderPrice());
        transactions.setAmount(ob.getTotalOrderPrice());
        transactions.setTransactionDate(ob.getOrderDate());
        transactions.setType(TransactionType.Debit);
        wallet.getTransactions().add(transactions);
        walletRepo.save(wallet);
        orderRepo.delete(ob);
        return "Order Deleted Successfully";
    }

    @Override
    public List<Orders> getAllOrders() {
        List<Orders> orders = orderRepo.findAll();
        if(orders.isEmpty())
            throw new WalletException("No Orders Found");
        return orders;
    }
}
