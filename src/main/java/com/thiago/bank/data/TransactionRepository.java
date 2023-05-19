package com.thiago.bank.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thiago.bank.models.Account;
import com.thiago.bank.models.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    
    List<Transaction> findByOriginOrDestiny(Account accountOrigin, Account accountDestiny);
}
