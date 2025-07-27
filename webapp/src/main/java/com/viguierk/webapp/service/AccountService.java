package com.viguierk.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viguierk.webapp.model.Account;
import com.viguierk.webapp.repository.AccountProxy;

import lombok.Data;

@Service // Bean de type Service, spécialisation de @Component
@Data //auto-génère les Getters/Setters 
public class AccountService {

    @Autowired
    AccountProxy accountProxy; //Injection de AccountProxy, pour y faire appel

    public Account getAccountbyId(final Integer id){
        return accountProxy.getAccountbyId(id);
    }

    public Iterable<Account> getAllAccounts(){
        return accountProxy.getAllAccounts();
    }

    public void deleteAccount(final Integer id){
        accountProxy.deleteAccount(id);
    }

    public Account saveAccount(Account account){
        Account newAccount;

        if(account.getAccId()==null){ //si ID nul, c'est un nouveau account
            newAccount = accountProxy.createAccount(account);
        } else{ //sinon on update un account déja existant
            newAccount = accountProxy.updateAccount(account);
        }

        return newAccount;

    }

}
