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
@Table(name = "type")//la table liée en BDD
@DynamicUpdate // performances, MAJ que des colonnes pertinentes en BDD
@NoArgsConstructor 
@AllArgsConstructor
@Data
@Builder
public class Type {

    @Id //la colonne sera une clé primaire
    @Column(name="typ_id") //lien avec champs de la table
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto-incrémentation
    private int typId;

    @Column(name="nom") //lien avec champs de la table
    private String name;

    @Column(name="seuil") //lien avec champs de la table
    private double seuil;

    // attribut "accounts" permettant de faire le lien entre Un Type et Des Account
    @OneToMany( // Un type a plusieurs account
        mappedBy = "type" //bidirectionnel 
            //on utilise les mêmes paramètres que pour l'attribut "type" dans la classe Account.java
            // le "mappedBy" est forcément côté @OneToMany
        , cascade= {
            CascadeType.PERSIST //Les SAVE sur un Type se répercutent sur l'Account associé
            , CascadeType.MERGE //Les MODIFICATIONS un Type se répercutent sur l'Account associé
        }
        , orphanRemoval = false // On ne supprime pas les Account sans Type
        //, fetch = FetchType.EAGER // Quand on récupère un Type, ça récupère tous les Account associés; commenté = défaut = LAZY
        )
    @JsonManagedReference //prévient la recursion
    private List<Account> accounts = new ArrayList<>();

    // >>> HELPERS POUR JOINTURES <<<
    public void addAccount(Account account) {
        accounts.add(account);
        account.setType(this);
    }
 
    public void rmvAccount(Account account) {
        accounts.remove(account);
        account.setType(null);
    }

}
