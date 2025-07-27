package com.viguierk.projetCresuss.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viguierk.projetCresuss.model.User;
import com.viguierk.projetCresuss.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    //les méthodes userRepository.<methode> sont désormais possibles car UserRepository extends CrudRepository

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id){
        User user = userRepository.findById(id).orElse(null);
 		if(user == null){
			return null;
		} else {
			return user;
		}
    }

    @Override
    public void deleteUser( int id){
        userRepository.delete(userRepository.findById(id).get());
    }

    @Override
    public User saveUser(User user){
        userRepository.save(user);
        return user;
    }

    @Override
    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
