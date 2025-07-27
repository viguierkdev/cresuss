package com.viguierk.projetCresuss.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //il y a un lien entre cette classe et une table de la BDD
@Table(name = "user")//la table liée en BDD
@DynamicUpdate // performances, MAJ que des colonnes pertinentes en BDD
@NoArgsConstructor 
@AllArgsConstructor
@Data
@Builder
public class User {

    @Id //la colonne sera une clé primaire
    @Column(name="usr_id") //lien avec champs de la table
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto-incrémentation
    private int usrId;

    @Column(name="nom") //lien avec champs de la table
    private String name;

    @Column(name="prenom") //lien avec champs de la table
    private String firstName;
    
    @Column(name="mail") //lien avec champs de la table
    private String mail;

    @Column(name="password") //lien avec champs de la table
    private String password;
    
    /*  @Column(name="rol_id") //lien avec champs de la table
    private int rolId;*/ // remplacé par jointure
    

    // attribut "role" permettant de lier Des User à Un Role
    @ManyToOne(// Plusieurs User peuvent être liés à un Role
        cascade= {
            CascadeType.PERSIST //Les SAVE sur un User se répercutent sur le Role associé
            , CascadeType.MERGE //Les MODIFICATIONS un User se répercutent sur le Role associé
        }
    ) 
    @JoinColumn (name = "rol_id") // colonne de Role avec laquelle faire la jointure
        // comme bidirectionnel, le @JoinColumn est forcément côté @ManyToOne
    @JsonBackReference//prrévient la récursion
    Role role; 

    // attribut "accounts" permettant de faire le lien entre Un User et Des Account
    @OneToMany( // Un User a plusieurs Account
        mappedBy = "user" //bidirectionnel 
            //on utilise les mêmes paramètres que pour l'attribut "user" dans la classe Account.java
            // le "mappedBy" est forcément côté @OneToMany
        , cascade= CascadeType.ALL //toutes les actions sur un Role se répercutent sur les Account associés
        , orphanRemoval = false // On ne supprime pas les Account sans User
        , fetch = FetchType.EAGER // Quand on récupère un User, ça récupère tous les Account associés
        )
    @JsonManagedReference //prévient la recursion
    private List<Account> accounts = new ArrayList<>();

    // >>> HELPERS POUR JOINTURES <<<
    public void addAccount(Account account) {
        accounts.add(account);
        account.setUser(this);
    }
 
    public void rmvAccount(Account account) {
        accounts.remove(account);
        account.setUser(null);
    }



}
