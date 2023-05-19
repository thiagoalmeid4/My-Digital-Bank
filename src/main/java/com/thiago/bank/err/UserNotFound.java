package com.thiago.bank.err;

public class UserNotFound extends RuntimeException{

    @Override
    public String getMessage() {
        return "Cpf não cadastrado";
    }
    
}
