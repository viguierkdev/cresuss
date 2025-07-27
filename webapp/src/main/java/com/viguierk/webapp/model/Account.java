package com.viguierk.webapp.model;

import java.util.HashSet;

import lombok.Data;


@Data //auto-génère les Getters/Setters 
public class Account {

    private Integer accId;

    private Integer typeId;

    private Integer clientId;

    private double balance;
}
