package com.thiago.bank.err;

public class KeyInvalid extends RuntimeException{

    @Override
    public String getMessage() {
        return "Chave inválida";
    }
    
}
