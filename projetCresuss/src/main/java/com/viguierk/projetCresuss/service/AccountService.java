package com.viguierk.projetCresuss.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viguierk.projetCresuss.model.Account;
import com.viguierk.projetCresuss.model.Account;
import com.viguierk.projetCresuss.repository.AccountRepository;
import com.viguierk.projetCresuss.repository.AccountRepository;

public interface AccountService {

        public List<Account> getAllAccounts();

    public Account getAccountById(int id);

    public void deleteAccount( int id);

    public Account saveAccount(Account account);

    public AccountRepository getAccountRepository();

    public void setAccountRepository(AccountRepository accountRepository);
}
