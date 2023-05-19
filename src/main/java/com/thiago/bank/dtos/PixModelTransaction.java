package com.thiago.bank.dtos;

import java.math.BigDecimal;


public record PixModelTransaction(
    Long numberAccount,
    BigDecimal amount,
    String keyPix
) {}
