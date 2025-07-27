package com.viguierk.projetCresuss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.viguierk.projetCresuss.model.Type;

@Repository // la classe est un bean qui va communiquer avec une source de donnée
public interface TypeRepository
    extends JpaRepository<Type,Integer>{ // on se baser sur le modèle Type
    //dont l'identifiant est de type Integer

    public Iterable<Type> findByName(String name); //requete dérivée (car nom à format spécial) qui sort des Role
        // derivee car find = on cherche / By = on va fournir une colonne / Nom = la colonne
    }

