package com.viguierk.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.viguierk.webapp.model.Account;
import com.viguierk.webapp.service.AccountService;
import com.viguierk.webapp.service.AccountService;

import lombok.Data;

@Data
@Controller // Bean de type Cpntroller, spécialisation de @Component
public class AccountController {
    

    @Autowired
    AccountService accountService;

    @GetMapping("/createAccount")
	public String createAccount(Model model) {
		Account newC = new Account();
		model.addAttribute("account", newC);
		return "formNewAccount";
	}

    @GetMapping("/updateAccount/{id}")
	public String updateAccount(@PathVariable("id") final Integer id, Model model) {
		Account updatedC = accountService.getAccountbyId(id);		
		model.addAttribute("account", updatedC);	
		return "formUpdateAccount";		
	}

    @GetMapping("/deleteAccount/{idUrl}") //type de requete HTTP + URL correspondante
    public ModelAndView deleteAccount(@PathVariable("idUrl") final Integer idVar){ //@PathVariable va prendre la valeur à la place d'idUrl 
        // et en faire une variable idVar
        accountService.deleteAccount(idVar); //suppression des accounts d'id = idVar

        return new ModelAndView("redirect:/"); //redirection vers le home
    }

    @PostMapping("/saveAccount") //type de requete HTTP + URL correspondante
    public ModelAndView saveAccount(@ModelAttribute Account account){ //@ModelAttribute va prendre les données du formulaire 
        // et en faire un objet Account
        accountService.saveAccount(account); //sauvegarde de Account

        return new ModelAndView("redirect:/"); //redirection vers le home
    }
}
