package com.viguierk.projetCresuss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.viguierk.projetCresuss.model.User;

@Repository // la classe est un bean qui va communiquer avec une source de donnée
public interface UserRepository
    extends JpaRepository<User,Integer>{ // on se baser sur le modèle User
    //dont l'identifiant est de type Integer

        public Iterable<User> findByNameAndFirstName(String name,String firstName); //requete dérivée (car nom à format spécial) qui sort des User
        // derivee car find = on cherche / By = on va fournir une colonne / Nom = la colonne
    }

