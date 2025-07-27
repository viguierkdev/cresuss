package com.viguierk.projetCresuss.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viguierk.projetCresuss.model.Account;
import com.viguierk.projetCresuss.model.User;
import com.viguierk.projetCresuss.model.Account;
import com.viguierk.projetCresuss.repository.AccountRepository;
import com.viguierk.projetCresuss.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    //les méthodes accountRepository.<methode> sont désormais possibles car AccountRepository extends CrudRepository
    
    @Override
    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(int id){
        Account account = accountRepository.findById(id).orElse(null);
 		if(account == null){
			return null;
		} else {
			return account;
		}
    }

    @Override
    public void deleteAccount( int id){
        accountRepository.delete(accountRepository.findById(id).get());
    }

    @Override
    public Account saveAccount(Account account){
        accountRepository.save(account);
        return account;
    }

    @Override
    public AccountRepository getAccountRepository() {
        return accountRepository;
    }

    @Override
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

}
