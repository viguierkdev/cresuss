package com.viguierk.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.viguierk.webapp.CustomProperties;
import com.viguierk.webapp.model.Account;
import com.viguierk.webapp.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j //Simple Logging Facade for Java, abstraction pour divers framework de logging
@Component // bean de type composant
public class UserProxy {

    @Autowired
    private CustomProperties props; //= new CustomProperties(); //lien avec CustomProperties.java

    public Iterable<User> getAllUsers(){
        String baseApiUrl = props.getApiUrl(); // pour récupérer l'URL de l'API
        String getUsersUrl = baseApiUrl+"/users"; // on complète avec le PATH

        RestTemplate restTemplate = new RestTemplate(); //RestTemplate exécute une requête HTTP
        ResponseEntity<Iterable<User>> response = restTemplate.exchange(
            getUsersUrl //URL a appeler
            , HttpMethod.GET // type de requete
            , null // objet HttpEntity envoyé (null donc comportement par défaut)
            , new ParameterizedTypeReference<Iterable<User>>() {} //objet retourné
            );
        
        log.debug("Get Users call "+response.getStatusCode().toString());

        return response.getBody(); // getBody récupère l'objet contenu dans response
            // RestTemplate convertit automatiquement le JSON en objet JAVA
    }

    public User getUserbyId(Integer usrId){
        String baseApiUrl = props.getApiUrl(); // pour récupérer l'URL de l'API
        String getUsersUrl = baseApiUrl+"/users/"+usrId; // on complète avec le PATH

        RestTemplate restTemplate = new RestTemplate(); //RestTemplate exécute une requête HTTP
        ResponseEntity<User> response = restTemplate.exchange(
            getUsersUrl //URL a appeler
            , HttpMethod.GET // type de requete
            , null // objet HttpEntity envoyé (l'ID du user)
            , User.class //objet retourné
            );
        
        log.debug("Get User By ID call "+response.getStatusCode().toString());

        return response.getBody(); // getBody récupère l'objet contenu dans response
            // RestTemplate convertit automatiquement le JSON en objet JAVA
    }

    public User createUser(User c){
        String baseApiUrl = props.getApiUrl(); // pour récupérer l'URL de l'API
        String createUsersUrl = baseApiUrl+"/users"; // on complète avec le PATH

        RestTemplate restTemplate = new RestTemplate(); //RestTemplate exécute une requête HTTP 
        HttpEntity<User> request = new HttpEntity<User>(c); // l'entité HTTP qui va être envoyée est le User c
        ResponseEntity<User> response = restTemplate.exchange(
            createUsersUrl //URL a appeler
            , HttpMethod.POST // type de requete
            , request // objet HttpEntity (le user)
            , User.class //objet retourné
            );
    
        log.debug("Create User call "+response.getStatusCode().toString());
        
        return response.getBody(); // getBody récupère l'objet contenu dans response
            // RestTemplate convertit automatiquement le JSON en objet JAVA
    }

    public void deleteUser(Integer usrId){
        String baseApiUrl = props.getApiUrl(); // pour récupérer l'URL de l'API
        String deleteUsersUrl = baseApiUrl+"/users/"+usrId; // on complète avec le PATH

        RestTemplate restTemplate = new RestTemplate(); //RestTemplate exécute une requête HTTP
        ResponseEntity<Void> response = restTemplate.exchange(
            deleteUsersUrl //URL a appeler
            , HttpMethod.DELETE // type de requete
            , null // objet HttpEntity envoyé (l'ID du user)
            , void.class//objet retourné
            );
        
        log.debug("Delete User call "+response.getStatusCode().toString());
    }
        
    public User updateUser(User c){
        String baseApiUrl = props.getApiUrl(); // pour récupérer l'URL de l'API
        String updateUsersUrl = baseApiUrl+"/users/"+c.getUsrId(); // on complète avec le PATH

        RestTemplate restTemplate = new RestTemplate(); //RestTemplate exécute une requête HTTP 
        HttpEntity<User> request = new HttpEntity<User>(c); // l'entité HTTP qui va être envoyée est le User c
        ResponseEntity<User> response = restTemplate.exchange(
            updateUsersUrl //URL a appeler
            , HttpMethod.PUT // type de requete
            , request // objet HttpEntity (le user)
            , User.class //objet retourné
            );
    
        log.debug("Update User call "+response.getStatusCode().toString());
        
        return response.getBody(); // getBody récupère l'objet contenu dans response
            // RestTemplate convertit automatiquement le JSON en objet JAVA
    }

    public Iterable<Account> getAccountsOfUser(Integer usrId){
        String baseApiUrl = props.getApiUrl(); // pour récupérer l'URL de l'API
        String getUsersUrl = baseApiUrl+"/users/accountsOfUser/"+usrId; // on complète avec le PATH

        RestTemplate restTemplate = new RestTemplate(); //RestTemplate exécute une requête HTTP
        ResponseEntity<Iterable<Account>> response = restTemplate.exchange(
            getUsersUrl //URL a appeler
            , HttpMethod.GET // type de requete
            , null // objet HttpEntity envoyé (null donc comportement par défaut)
            , new ParameterizedTypeReference<Iterable<Account>>() {} //objet retourné
            );
        
        log.debug("Get Accounts Of User call "+response.getStatusCode().toString());

        return response.getBody(); // getBody récupère l'objet contenu dans response
            // RestTemplate convertit automatiquement le JSON en objet JAVA
    }
}
