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

import com.thiago.bank.dtos.Key;
import com.thiago.bank.dtos.PixModelTransaction;
import com.thiago.bank.models.Costumer;
import com.thiago.bank.services.PixService;

@RestController
@RequestMapping(value = "pix")
public class PixController {
    
    @Autowired
    private PixService service;

    @PostMapping(path = "/registrar-chave")
    public ResponseEntity<?> saveKey(@RequestBody Key key){
        service.registerKey(key);
        return new ResponseEntity<>("Nova chave registrada", HttpStatus.CREATED);
    }

    @PostMapping(path = "/transação")
    public ResponseEntity<?> pixTransaction(@RequestBody PixModelTransaction modelTransaction){
        service.pixMethod(modelTransaction);
        return new ResponseEntity<>("Transação realizada", HttpStatus.OK );
    }

    @PostMapping(path = "/minhas-chaves")
    public String[] pixKeys(@RequestBody Costumer costumer){
        return service.keysUser(costumer);
    }

    @GetMapping(path = "/buscar-chave/{key}")
    public String getNameByKey(@PathVariable String key){
        return service.nameByKey(key);
    }
}
