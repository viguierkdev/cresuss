package com.viguierk.projetCresuss.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //il y a un lien entre cette classe et une table de la BDD
@Table(name = "role")//la table liée en BDD
@DynamicUpdate // performances, MAJ que des colonnes pertinentes en BDD
@NoArgsConstructor 
@AllArgsConstructor
@Data
@Builder
public class Role {

    @Id //la colonne sera une clé primaire
    @Column(name="rol_id") //lien avec champs de la table
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto-incrémentation
    private int rolId;

    @Column(name="nom") //lien avec champs de la table
    private String name;

    // attribut "users" permettant de faire le lien entre Un role et Des users
    @OneToMany( // Un role a plusieurs users
        mappedBy = "role" //bidirectionnel 
            //on utilise les mêmes paramètres que pour l'attribut "role" dans la classe User.java
            // le "mappedBy" est forcément côté @OneToMany
        , cascade= {
            CascadeType.PERSIST //Les SAVE sur un Role se répercutent sur le User associé
            , CascadeType.MERGE //Les MODIFICATIONS un Role se répercutent sur le User associé
        }
        , orphanRemoval = false // On ne supprime pas les User sans Role
        //, fetch = FetchType.EAGER // Quand on récupère un Role, ça récupère tous les User associés; commenté = défaut = LAZY
        )
    @JsonManagedReference //prévient la recursion
    private List<User> users = new ArrayList<>();

        // >>> HELPERS POUR JOINTURES <<<
    public void addUser(User user) {
        users.add(user);
        user.setRole(this);
    }
 
    public void rmvUser(User user) {
        users.remove(user);
        user.setRole(null);
    }
}
