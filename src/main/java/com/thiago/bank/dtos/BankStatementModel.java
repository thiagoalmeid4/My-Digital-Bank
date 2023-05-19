package com.thiago.bank.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.thiago.bank.enums.TypeTransaction;

public record BankStatementModel(

    String nameOrigin,
    String nameDestiny,
    TypeTransaction type,
    LocalDateTime trasactionDate,
    BigDecimal amount

) {
    
}
