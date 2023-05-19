package com.thiago.bank.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiago.bank.data.KeyPixRepository;
import com.thiago.bank.data.TransactionRepository;
import com.thiago.bank.dtos.Key;
import com.thiago.bank.dtos.PixModelTransaction;
import com.thiago.bank.enums.TypeTransaction;
import com.thiago.bank.err.CpfInvalid;
import com.thiago.bank.err.InsufficientFunds;
import com.thiago.bank.err.KeyInvalid;
import com.thiago.bank.models.Costumer;
import com.thiago.bank.models.KeyPix;
import com.thiago.bank.models.Pix;
import com.thiago.bank.utils.CpfValidation;
import com.thiago.bank.utils.PixKeyGenerator;

@Service
public class PixService {
    
    @Autowired
    private KeyPixRepository keyPixRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionRepository transactionRepository;

    public void registerKey(Key key){
        KeyPix keyPix = new KeyPix();
        if(key.type()==0){
            keyPix.setCodeKey(PixKeyGenerator.generate());
        }else if(key.type()==1){
            if(CpfValidation.isCPF(key.costumer().getCpf())){
                keyPix.setCodeKey(key.costumer().getCpf());
            }else{
                throw new CpfInvalid();
            }
        }
        keyPix.setUser(key.costumer());
        keyPixRepository.save(keyPix);
    }

    public String[] keysUser (Costumer costumer){
        List<KeyPix> listKey = keyPixRepository.findByUser(costumer);
        String[] justKeys = new String[listKey.size()];
        for(int i = 0; i!= listKey.size(); i++){
            justKeys[i] = listKey.get(i).getCodeKey();
        }
        return justKeys;
    }

    public void pixMethod(PixModelTransaction model){
        Pix pixTransaction = new Pix();
        if(!keyPixRepository.existsById(model.keyPix())){
            throw new KeyInvalid();
        }else{
            if(!accountService.checkBalance(model.amount(),model.numberAccount())){
                throw new InsufficientFunds();
            }else{
                pixTransaction.setAmount(model.amount());
                pixTransaction.setDateTransaction(LocalDateTime.now());
                pixTransaction.setDestiny(accountService.getUser(keyPixRepository.findById(model.keyPix()).get().getUser()));
                pixTransaction.setOrigin(accountService.getId(model.numberAccount()));
                pixTransaction.setKeyDestiny(keyPixRepository.findById(model.keyPix()).get());
                pixTransaction.setType(TypeTransaction.PIX);
                if(accountService.executeTransaction(pixTransaction)){
                    transactionRepository.save(pixTransaction); 
                }   
            }
        }
    }

    public String nameByKey(String key){
        if(!keyPixRepository.existsById(key)){
            throw new KeyInvalid();
        }else{
            return keyPixRepository.findById(key).get().getUser().getName();
        }
    }
    
}
