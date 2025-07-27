package com.viguierk.webapp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data // pour les getters/setters
@Configuration // bean utilisé pour la configuration de l'application
@ConfigurationProperties(prefix="com.viguierk.webapp") // met en parallèle avec "application.properties"
// et scan les propriétés préfixées par "com.viguierk.webapp"
public class CustomProperties {

    private String apiUrl;//correspond à la propriété spécifiée dans application.properties

}
