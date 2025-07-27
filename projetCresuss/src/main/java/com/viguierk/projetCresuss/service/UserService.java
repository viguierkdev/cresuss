package com.viguierk.projetCresuss.service;

import java.util.List;
import java.util.Optional;

import com.viguierk.projetCresuss.model.User;
import com.viguierk.projetCresuss.repository.UserRepository;

public interface UserService {

    public List<User> getAllUsers();

    public User getUserById(int id);

    public void deleteUser( int id);

    public User saveUser(User user);

    public UserRepository getUserRepository();

    public void setUserRepository(UserRepository userRepository);
}
