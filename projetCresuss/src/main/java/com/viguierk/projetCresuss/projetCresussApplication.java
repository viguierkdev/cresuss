package com.viguierk.projetCresuss;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.viguierk.projetCresuss.controller.AccountController;
import com.viguierk.projetCresuss.controller.RoleController;
import com.viguierk.projetCresuss.controller.TypeController;
import com.viguierk.projetCresuss.controller.UserController;
import com.viguierk.projetCresuss.model.Account;
import com.viguierk.projetCresuss.model.Role;
import com.viguierk.projetCresuss.model.Type;
import com.viguierk.projetCresuss.model.User;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class projetCresussApplication {

    @Autowired
    private UserController userController;
    @Autowired
    private AccountController accountController;
    @Autowired
    private TypeController typeController;
    @Autowired
    private RoleController roleController;
    
    public static void main(String[] args) {
        SpringApplication.run(projetCresussApplication.class, args);
    }
}










