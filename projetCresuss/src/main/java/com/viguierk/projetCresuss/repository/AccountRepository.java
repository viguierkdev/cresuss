package com.viguierk.projetCresuss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.viguierk.projetCresuss.model.Account;

@Repository // la classe est un bean qui va communiquer avec une source de donnée
public interface AccountRepository
    extends JpaRepository<Account,Integer>{ // on se baser sur le modèle Account
    //dont l'identifiant est de type Integer

    }

