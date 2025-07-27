package com.viguierk.webapp.model;

import java.util.HashSet;

import lombok.Data;


@Data //auto-génère les Getters/Setters 
public class User {

    private Integer usrId;

    private String name;

    private String firstName;

    private String mail;

    private String password;

    private int rolId;

}
