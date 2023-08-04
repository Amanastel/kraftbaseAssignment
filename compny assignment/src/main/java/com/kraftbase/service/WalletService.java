package com.kraftbase.service;

import com.kraftbase.model.Transactions;
import com.kraftbase.model.Wallet;

import java.util.List;

public interface WalletService {
    public Wallet addMoney(String email, Float amount);
    public Wallet getWallet(String email);
    public List<Transactions> getAllTranactions(Integer walletId);
    public Wallet payRideBill(String email,Float bill);
}
