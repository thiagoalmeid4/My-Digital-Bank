package com.thiago.bank.err;

public class InsufficientFunds extends RuntimeException {

    @Override
    public String getMessage() {
        return "Saldo insuficiente";
    }
    
}
