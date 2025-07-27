package com.viguierk.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.viguierk.webapp.repository.UserProxy;

@SpringBootApplication
public class WebappApplication implements CommandLineRunner{

	@Autowired
	private CustomProperties props = new CustomProperties(); //lien avec CustomProperties.java
	@Autowired
	UserProxy cp = new UserProxy();

	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(props.getApiUrl());//appel à getApiUrl de CustomProperties.java
		//System.out.println(cp.getAllClients());
	}

}
