package com.viguierk.projetCresuss.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.viguierk.projetCresuss.model.Account;
import com.viguierk.projetCresuss.model.Role;
import com.viguierk.projetCresuss.model.Type;
import com.viguierk.projetCresuss.model.User;
import com.viguierk.projetCresuss.service.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/users" )// toutes les méthodes de cette classe se basent sur l'URL "/clients"
public class UserController {

    private final RoleController roleController;


    @Autowired
    UserServiceImpl userService;

    UserController(RoleController roleController) {
        this.roleController = roleController;
    }

	@GetMapping
    public List<User> showAllUsers(){
		return this.userService.getAllUsers();
	}
	

	record NewUserRequest(String name, String firstName, String mail, String password) {}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> addUser(@RequestBody NewUserRequest user){
		User newUser = User.builder()
			.name(user.name())
			.firstName(user.firstName())
			.mail(user.mail())
			.password(user.password())
			.build();
		User savedUser = this.userService.saveUser(newUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

	
	record UpdateUserRequest(String name, String firstName, String mail) {}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public User updateUser(@RequestBody UpdateUserRequest user){
		User newUser = User.builder()
			.name(user.name())
			.firstName(user.firstName())
			.mail(user.mail())
			.build();
		User savedUser = this.userService.saveUser(newUser);
        return savedUser;
    }
/*
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User user){
        return this.userService.saveUser(user);
    }
*/
    /**
	 * Update - Update an existing user
	 * @param id - The id of the user to update
	 * @param user - The new user object
	 * @return
	 */
	@PutMapping(path="{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User updateUserFromId(@PathVariable("id") final Integer id,@RequestBody User user){
		User currentUser = userService.getUserById(id);

		if(currentUser == null){
			return null;
		} else {
		
		String firstName = user.getFirstName();
		if(firstName != null) {
			currentUser.setFirstName(firstName);
		}
		String name = user.getName();
		if(name != null) {
			currentUser.setName(name);
		}
		String mail = user.getMail();
		if(mail != null) {
			currentUser.setMail(mail);
		}
		String password = user.getPassword();
		if(password != null) {
			currentUser.setPassword(password);;
		}
		Role role = user.getRole();
		if(role != null) {
			currentUser.setRole(role);
		}
		userService.saveUser(currentUser);
		return currentUser;
		}
    }
	
	@GetMapping("{usrId}")
	public User getUserById(@PathVariable int usrId){
		User optUsr = userService.getUserById(usrId);

		if(optUsr == null){
			return null;
		} else {
			return optUsr;
		}
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") final Integer usrId){
        try {
            this.userService.deleteUser(usrId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@GetMapping("accountsOfUser/{usrId}")
	public List<Account> getAccountsOfUser(@PathVariable int usrId){
		User curUser = getUserById(usrId);
		List<Account> listAccounts = curUser.getAccounts();
        curUser.getAccounts().forEach(account -> System.out.println(account.getAccId()));
		return listAccounts;
	}
}


