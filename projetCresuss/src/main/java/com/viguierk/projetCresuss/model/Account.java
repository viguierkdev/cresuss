package com.viguierk.projetCresuss.model;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //il y a un lien entre cette classe et une table de la BDD
@Table(name = "compte")//la table liée en BDD
@DynamicUpdate // performances, MAJ que des colonnes pertinentes en BDD
@NoArgsConstructor 
@AllArgsConstructor
@Data
@Builder
public class Account {

    @Id //la colonne sera une clé primaire
    @Column(name="com_id") //lien avec champs de la table
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto-incrémentation
    private int accId;

    @Column(name="balance") //lien avec champs de la table
    private double balance;

    /*@Column(name="usr_id") //lien avec champs de la table
    private int usrId;*/ // remplacé par jointure
    
    /*@Column(name="typ_id") //lien avec champs de la table
    private int typId;*/ // remplacé par jointure

    // attribut "type" permettant de lier Des Account à Un Type
    @ManyToOne(// Plusieurs Account peuvent être liés à un Type
        cascade= {
            CascadeType.PERSIST //Les SAVE sur un Account se répercutent sur le Type associé
            , CascadeType.MERGE //Les MODIFICATIONS un Account se répercutent sur le Type associé
        }
    ) 
    @JoinColumn (name = "typ_id") // colonne de Type avec laquelle faire la jointure
        // comme bidirectionnel, le @JoinColumn est forcément côté @ManyToOne
    @JsonBackReference//prrévient la récursion
    Type type; 

    // attribut "user" permettant de lier Des Account à Un User
    @ManyToOne(// Plusieurs Account peuvent être liés à un User
        cascade= {
            CascadeType.PERSIST //Les SAVE sur un Account se répercutent sur l'User associé
            , CascadeType.MERGE //Les MODIFICATIONS un Account se répercutent sur l'User associé
        }
    ) 
    @JoinColumn (name = "usr_id") // colonne de User avec laquelle faire la jointure
        // comme bidirectionnel, le @JoinColumn est forcément côté @ManyToOne
    @JsonBackReference//prrévient la récursion
    User user; 
}
