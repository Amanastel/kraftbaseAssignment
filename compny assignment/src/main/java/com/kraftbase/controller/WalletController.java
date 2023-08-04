package com.kraftbase.controller;

import com.kraftbase.model.Wallet;
import com.kraftbase.service.CustomerService;
import com.kraftbase.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class WalletController {

    @Autowired
    private WalletService walletService;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/addMoney")
    public ResponseEntity<Wallet> addMoneyHandler(Authentication auth,@RequestParam("amount") Float amount){
        return ResponseEntity.ok(walletService.addMoney(auth.getName(),amount));
    }

    @GetMapping("/getWallet")
    public ResponseEntity<Wallet> getWalletHandler(Authentication auth){
        return ResponseEntity.ok(customerService.getCustomerDetailsByEmail(auth.getName()).getWallet());
    }

    @GetMapping("/Transactions")
    public ResponseEntity<Wallet> getTransactionsHandler(Authentication auth){
        return ResponseEntity.ok(customerService.getCustomerDetailsByEmail(auth.getName()).getWallet());
    }

    @PostMapping("/billPayment")
    public ResponseEntity<Wallet> payBillHandler(Authentication auth,@RequestParam("BillAmount") Float billAmount){
        return ResponseEntity.ok(walletService.payRideBill(auth.getName(),billAmount));
    }
}
