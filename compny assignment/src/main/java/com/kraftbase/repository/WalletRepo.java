package com.kraftbase.repository;


import com.kraftbase.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepo extends JpaRepository<Wallet, Integer> {

}
