package com.thiago.bank.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiago.bank.dtos.BankStatementModel;
import com.thiago.bank.models.Account;
import com.thiago.bank.models.Costumer;
import com.thiago.bank.services.AccountService;

@RestController
@RequestMapping(value = "conta")
public class AccountController {
    
    @Autowired
    private AccountService service;

    // @PostMapping(path = "/gerar")
    // public ResponseEntity<Account> createAccount (@RequestBody Costumer costumer){
    //     return new ResponseEntity<Account>(service.creatAccount(costumer), HttpStatus.CREATED);
    // }

    @GetMapping(path = "/{conta}/extrato")
    public List<BankStatementModel> bankStatement (@PathVariable Long conta ){
        var account = new Account();
        account.setNumber(conta);
        return service.report(account);
    }

    @GetMapping(path = "/{conta}/saldo")
    public BigDecimal getBalance (@PathVariable Long conta){
        return service.getBalance(conta);
    }

    @PostMapping(path = "/user")
    public Account account (@RequestBody Costumer costumer){
        return service.getUser(costumer);
    }

}
