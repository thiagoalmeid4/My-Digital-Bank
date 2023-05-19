package com.thiago.bank.err;

public class KeyExists extends RuntimeException{

    @Override
    public String getMessage() {
        return "Chave já registrada";
    }
    
}
