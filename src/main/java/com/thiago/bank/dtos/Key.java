package com.thiago.bank.dtos;

import com.thiago.bank.models.Costumer;

public record Key(
    Costumer costumer,
    String key,
    int type
) {}
