package com.viguierk.projetCresuss.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viguierk.projetCresuss.model.Account;
import com.viguierk.projetCresuss.model.Role;
import com.viguierk.projetCresuss.model.Type;
import com.viguierk.projetCresuss.model.User;
import com.viguierk.projetCresuss.service.AccountService;

@RestController
@RequestMapping("/accounts")// toutes les méthodes de cette classe se basent sur l'URL "/accounts"
public class AccountController {

    @Autowired
    AccountService accountService;

	@GetMapping
    public List<Account> showAllAccounts(){
		return this.accountService.getAllAccounts();
	}
	
	record NewAccountRequest(int balance) {}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> addAccount(@RequestBody NewAccountRequest account){
		Account newAccount = Account.builder()
			.balance(account.balance())
			.build();
		Account savedAccount = this.accountService.saveAccount(newAccount);
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }


		
	record UpdateAccountRequest(int accId,int balance) {}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public Account updateAccount(@RequestBody UpdateAccountRequest account){
		Account newAccount = Account.builder()
			.accId(account.accId())
			.balance(account.balance())
			.build();
		Account savedAccount = this.accountService.saveAccount(newAccount);
        return savedAccount;
    }
	/*
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Account updateAccount(@RequestBody Account account){
        return this.accountService.saveAccount(account);
    }*/

    /**
	 * Update - Update an existing account
	 * @param id - The id of the account to update
	 * @param account - The new account object
	 * @return
	 */
	@PutMapping(path="{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Account updateAccountFromId(@PathVariable("id") final Integer id,@RequestBody Account account){
		Account currentAccount = accountService.getAccountById(id);

		if(currentAccount == null){
			return null;
		} else {
			User user = account.getUser();
			if(user != null) {
				currentAccount.setUser(user);
			}
			Type type = account.getType();
			if(type != null) {
				currentAccount.setType(type);
			}
			double balance = account.getBalance();
			if(type != null) {
				currentAccount.setBalance(balance);
			}
			accountService.saveAccount(currentAccount);
			return currentAccount;
		}
    }
	
	@GetMapping("{id}")
	public Account getAccountById(@PathVariable int id){
        Account optAccount = accountService.getAccountById(id);
		if(optAccount == null){
			return null;
		} else {
			return optAccount;
		}
			
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("id") final Integer usrId){
        try {
            this.accountService.deleteAccount(usrId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
