package com.thiago.bank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiago.bank.data.CostumerRepository;
import com.thiago.bank.dtos.Login;
import com.thiago.bank.err.CpfInvalid;
import com.thiago.bank.err.PasswordInvalid;
import com.thiago.bank.err.UserExists;
import com.thiago.bank.err.UserNotFound;
import com.thiago.bank.models.Costumer;
import com.thiago.bank.utils.CpfValidation;

@Service
public class CostumerService {
    
    @Autowired
    private CostumerRepository repository;
    @Autowired
    private AccountService accountService;

    public void createUser(Costumer costumer){
        if(!CpfValidation.isCPF(costumer.getCpf())){
            throw new CpfInvalid();
        }else{
            if(repository.existsById(costumer.getCpf())){
                throw new UserExists();
            }else{
                repository.save(costumer);
                accountService.creatAccount(costumer);
            }
        }
    }

    public Costumer login(Login login){
        if(!CpfValidation.isCPF(login.cpf())){
            throw new CpfInvalid();
        }else{
            if(!repository.existsById(login.cpf())){
                throw new UserNotFound();
            }else if(!repository.findById(login.cpf()).get().getPassword().equals(login.password())){
                throw new PasswordInvalid();
            }else{
                return repository.findById(login.cpf()).get();
            }
        }
    }

    public Costumer getId(String cpf){
        return repository.findById(cpf).get();
    }


}
