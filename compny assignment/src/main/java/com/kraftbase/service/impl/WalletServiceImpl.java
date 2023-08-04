package com.kraftbase.service.impl;

import com.kraftbase.exceptions.WalletException;
import com.kraftbase.model.Customer;
import com.kraftbase.model.TransactionType;
import com.kraftbase.model.Transactions;
import com.kraftbase.model.Wallet;
import com.kraftbase.repository.CustomerRepo;
import com.kraftbase.repository.WalletRepo;
import com.kraftbase.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepo walletRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Override
    public Wallet addMoney(String email, Float amount) {
        if(email==null || amount == null)
            throw new WalletException("Invalid Details");
        Customer customer= customerRepo.findByEmail(email).orElseThrow(()->new WalletException("No Customer Found"));
        Wallet ob= walletRepo.findById(customer.getWallet().getWalletId()).orElseThrow(()->new WalletException("No Wallet Found"));

        ob.setBalance(ob.getBalance()+amount);
        Transactions trans= new Transactions();
        trans.setTransactionDate(LocalDateTime.now());
        trans.setAmount(amount);
        trans.setType(TransactionType.Credit);
        trans.setCurrentBalance(ob.getBalance());
        ob.getTransactions().add(trans);
        return walletRepo.save(ob);
    }

    @Override
    public Wallet getWallet(String email) {
        if(email==null)
            throw new WalletException("Invalid Details");

        Customer customer= customerRepo.findByEmail(email).orElseThrow(()->new WalletException("No Customer Found"));
        return customer.getWallet();
    }

    @Override
    public List<Transactions> getAllTranactions(Integer walletId) {
        if (walletId == null)
            throw new WalletException("Invalid Details");
        Wallet ob = walletRepo.findById(walletId).orElseThrow(() -> new WalletException("No Wallet Found"));

        return ob.getTransactions();
    }

    @Override
    public Wallet payRideBill(String email, Float bill) {

        if (email == null || bill == null)
            throw new WalletException("Invalid Details");

        Customer customer= customerRepo.findByEmail(email).orElseThrow(()->new WalletException("No Customer Found"));
        Wallet ob= walletRepo.findById(customer.getWallet().getWalletId()).orElseThrow(()->new WalletException("No Wallet Found"));

       if(ob.getBalance()<bill){
           float required= bill - ob.getBalance();
              throw new WalletException("Insufficient Balance, Please add "+required+" to your wallet");
       }

         ob.setBalance(ob.getBalance()-bill);
         Transactions transactions= new Transactions();
            transactions.setTransactionDate(LocalDateTime.now());
            transactions.setAmount(bill);
            transactions.setType(TransactionType.Debit);
            transactions.setCurrentBalance(ob.getBalance());

            ob.getTransactions().add(transactions);

            return walletRepo.save(ob);

    }
}
