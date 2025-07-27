package com.viguierk.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viguierk.webapp.model.Account;
import com.viguierk.webapp.model.User;
import com.viguierk.webapp.repository.UserProxy;

import lombok.Data;

@Service // Bean de type Service, spécialisation de @Component
@Data //auto-génère les Getters/Setters 
public class UserService {

    @Autowired
    UserProxy userProxy; //Injection de UserProxy, pour y faire appel

    public User getUserbyId(final Integer usrId){
        return userProxy.getUserbyId(usrId);
    }

    public Iterable<User> getAllUsers(){
        return userProxy.getAllUsers();
    }

    public void deleteUser(final Integer usrId){
        userProxy.deleteUser(usrId);
    }

    public User saveUser(User user){
        User newUser;

        user.setName(user.getName().toUpperCase()); //règle de gestion, nom de famille commence par Majuscule

        if(user.getUsrId()==null){ //si ID nul, c'est un nouveau user
            newUser = userProxy.createUser(user);
        } else{ //sinon on update un user déja existant
            newUser = userProxy.updateUser(user);
        }

        return newUser;

    }

    public Iterable<Account> getAccountsOfUser(Integer id) {
        return userProxy.getAccountsOfUser(id);
    }

}
