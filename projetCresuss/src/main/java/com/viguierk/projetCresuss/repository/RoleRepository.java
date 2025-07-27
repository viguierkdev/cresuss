package com.viguierk.projetCresuss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.viguierk.projetCresuss.model.Role;

@Repository // la classe est un bean qui va communiquer avec une source de donnée
public interface RoleRepository
    extends JpaRepository<Role,Integer>{ // on se baser sur le modèle Role
    //dont l'identifiant est de type Integer

        public Iterable<Role> findByName(String name); //requete dérivée (car nom à format spécial) qui sort des Role
        // derivee car find = on cherche / By = on va fournir une colonne / Nom = la colonne
    }

