package com.thiago.bank.err;

public class UserExists extends RuntimeException{

    @Override
    public String getMessage() {
        return "Cpf já cadastrado";
    }
    
    
}
