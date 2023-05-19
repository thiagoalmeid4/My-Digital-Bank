package com.thiago.bank.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thiago.bank.models.Account;
import com.thiago.bank.models.Costumer;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
    
    Account findByUser(Costumer user);
}
