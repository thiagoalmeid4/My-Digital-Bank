package com.thiago.bank.services;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiago.bank.data.AccountRepository;
import com.thiago.bank.data.TransactionRepository;
import com.thiago.bank.dtos.BankStatementModel;
import com.thiago.bank.models.Account;
import com.thiago.bank.models.Costumer;
import com.thiago.bank.models.Transaction;
import com.thiago.bank.utils.BankAccountNumberGenerator;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository repository;
    @Autowired
    private TransactionRepository transactionRepository;

    public Account getUser(Costumer user){
        return repository.findByUser(user);
    }

    public Account getId(Long number){
        return repository.findById(number).get();
    }

    public Account creatAccount(Costumer costumer){
        Account account = new Account();
        account.setNumber(Long.parseLong(BankAccountNumberGenerator.generate()));
        account.setUser(costumer);
        account.setBalance(new BigDecimal(200));
        return repository.save(account);
    }

    public boolean checkBalance(BigDecimal amount, Long numberAccount){
        if(repository.findById(numberAccount).get().getBalance().compareTo(amount)>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean executeTransaction(Transaction transaction){
        Account destiny = transaction.getDestiny();
        Account origin = transaction.getOrigin();
        destiny.setBalance(destiny.getBalance().add(transaction.getAmount()));
        origin.setBalance(origin.getBalance().subtract(transaction.getAmount()));
        repository.saveAndFlush(destiny);
        repository.saveAndFlush(origin);
        return true;
    }

    public List<BankStatementModel> report (Account account){    
        List<BankStatementModel> listTransactions = new LinkedList<>();
        for(Transaction x: transactionRepository.findByOriginOrDestiny(account, account)){
            listTransactions.add(new BankStatementModel(repository.findById(x.getOrigin().getNumber()).get().getUser().getName(),
                repository.findById(x.getDestiny().getNumber()).get().getUser().getName(),
             x.getType(), x.getDateTransaction(),x.getAmount()));
        }
        return listTransactions;
    }

    public BigDecimal getBalance (Long number){
        return repository.findById(number).get().getBalance();
    }

}
