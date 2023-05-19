package com.thiago.bank.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.thiago.bank.enums.TypeTransaction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateTransaction;
    private BigDecimal amount;
    @OneToOne(targetEntity = Account.class)
    private Account origin;
    @OneToOne(targetEntity = Account.class)
    private Account destiny;
    private TypeTransaction type;
}
