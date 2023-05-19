package com.thiago.bank.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class KeyPix {
    
    @Id
    private String codeKey;
    @ManyToOne(targetEntity = Costumer.class)
    private Costumer user;
}
