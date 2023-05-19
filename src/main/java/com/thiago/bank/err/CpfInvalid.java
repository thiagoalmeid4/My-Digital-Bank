package com.thiago.bank.err;

public class CpfInvalid extends RuntimeException{

    @Override
    public String getMessage() {
        return "Cpf inválido";
    }
    
}
