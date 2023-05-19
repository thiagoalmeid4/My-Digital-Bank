package com.thiago.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiago.bank.dtos.Login;
import com.thiago.bank.models.Costumer;
import com.thiago.bank.services.CostumerService;

@RestController
@RequestMapping(value = "user")
public class CostumerController {
    
    @Autowired
    private CostumerService service;

    @PostMapping(path = "/registrar")
    public ResponseEntity<?> newUser(@RequestBody Costumer costumer){
        service.createUser(costumer);
        return new ResponseEntity<>("Conta criada com sucesso", HttpStatus.CREATED);
    }

    @PostMapping(path = "/autenticação")
    public ResponseEntity<Costumer> login (@RequestBody Login login){
        return new ResponseEntity<Costumer>(service.login(login),HttpStatus.OK);
    }

    @GetMapping(path = "/{cpf}")
    public Costumer getUser(@PathVariable String cpf){
        return service.getId(cpf);
    }

}
