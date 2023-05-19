package com.thiago.bank.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thiago.bank.models.Costumer;
import com.thiago.bank.models.KeyPix;

@Repository
public interface KeyPixRepository extends JpaRepository<KeyPix, String >{
    
    List<KeyPix> findByUser(Costumer user);
    
}
