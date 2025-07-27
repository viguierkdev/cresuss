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
import com.viguierk.webapp.model.Account;

import lombok.extern.slf4j.Slf4j;

@Slf4j //Simple Logging Facade for Java, abstraction pour divers framework de logging
@Component // bean de type composant
public class AccountProxy {

    @Autowired
    private CustomProperties props; //= new CustomProperties(); //lien avec CustomProperties.java

    public Iterable<Account> getAllAccounts(){
        String baseApiUrl = props.getApiUrl(); // pour récupérer l'URL de l'API
        String getAccountsUrl = baseApiUrl+"/accounts"; // on complète avec le PATH

        RestTemplate restTemplate = new RestTemplate(); //RestTemplate exécute une requête HTTP
        ResponseEntity<Iterable<Account>> response = restTemplate.exchange(
            getAccountsUrl //URL a appeler
            , HttpMethod.GET // type de requete
            , null // objet HttpEntity envoyé (null donc comportement par défaut)
            , new ParameterizedTypeReference<Iterable<Account>>() {} //objet retourné
            );
        
        log.debug("Get Accounts call "+response.getStatusCode().toString());

        return response.getBody(); // getBody récupère l'objet contenu dans response
            // RestTemplate convertit automatiquement le JSON en objet JAVA
    }

    public Account getAccountbyId(Integer id){
        String baseApiUrl = props.getApiUrl(); // pour récupérer l'URL de l'API
        String getAccountsUrl = baseApiUrl+"/accounts/"+id; // on complète avec le PATH

        RestTemplate restTemplate = new RestTemplate(); //RestTemplate exécute une requête HTTP
        ResponseEntity<Account> response = restTemplate.exchange(
            getAccountsUrl //URL a appeler
            , HttpMethod.GET // type de requete
            , null // objet HttpEntity envoyé (l'ID du account)
            , Account.class //objet retourné
            );
        
        log.debug("Get Account By ID call "+response.getStatusCode().toString());

        return response.getBody(); // getBody récupère l'objet contenu dans response
            // RestTemplate convertit automatiquement le JSON en objet JAVA
    }

    public Account createAccount(Account c){
        String baseApiUrl = props.getApiUrl(); // pour récupérer l'URL de l'API
        String createAccountsUrl = baseApiUrl+"/accounts"; // on complète avec le PATH

        RestTemplate restTemplate = new RestTemplate(); //RestTemplate exécute une requête HTTP 
        HttpEntity<Account> request = new HttpEntity<Account>(c); // l'entité HTTP qui va être envoyée est le Account c
        ResponseEntity<Account> response = restTemplate.exchange(
            createAccountsUrl //URL a appeler
            , HttpMethod.POST // type de requete
            , request // objet HttpEntity (le account)
            , Account.class //objet retourné
            );
    
        log.debug("Create Account call "+response.getStatusCode().toString());
        
        return response.getBody(); // getBody récupère l'objet contenu dans response
            // RestTemplate convertit automatiquement le JSON en objet JAVA
    }

    public void deleteAccount(Integer id){
        String baseApiUrl = props.getApiUrl(); // pour récupérer l'URL de l'API
        String deleteAccountsUrl = baseApiUrl+"/accounts/"+id; // on complète avec le PATH

        RestTemplate restTemplate = new RestTemplate(); //RestTemplate exécute une requête HTTP
        ResponseEntity<Void> response = restTemplate.exchange(
            deleteAccountsUrl //URL a appeler
            , HttpMethod.DELETE // type de requete
            , null // objet HttpEntity envoyé (l'ID du account)
            , void.class//objet retourné
            );
        
        log.debug("Delete Account call "+response.getStatusCode().toString());
    }
        
    public Account updateAccount(Account c){
        String baseApiUrl = props.getApiUrl(); // pour récupérer l'URL de l'API
        String updateAccountsUrl = baseApiUrl+"/accounts/"+c.getAccId(); // on complète avec le PATH

        RestTemplate restTemplate = new RestTemplate(); //RestTemplate exécute une requête HTTP 
        HttpEntity<Account> request = new HttpEntity<Account>(c); // l'entité HTTP qui va être envoyée est le Account c
        ResponseEntity<Account> response = restTemplate.exchange(
            updateAccountsUrl //URL a appeler
            , HttpMethod.PUT // type de requete
            , request // objet HttpEntity (le account)
            , Account.class //objet retourné
            );
    
        log.debug("Update Account call "+response.getStatusCode().toString());
        
        return response.getBody(); // getBody récupère l'objet contenu dans response
            // RestTemplate convertit automatiquement le JSON en objet JAVA
    }
}
