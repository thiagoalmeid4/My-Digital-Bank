package com.thiago.bank.err;

public class PasswordInvalid extends RuntimeException{

    @Override
    public String getMessage() {
        return "Senha inválida";
    }
    
}
