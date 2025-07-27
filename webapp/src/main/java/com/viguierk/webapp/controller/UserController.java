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
import com.viguierk.webapp.model.User;
import com.viguierk.webapp.service.UserService;

import lombok.Data;

@Data
@Controller // Bean de type Cpntroller, spécialisation de @Component
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/") //type de requete HTTP + URL correspondante
    public String home(Model model){
        Iterable<User> listUser = userService.getAllUsers();
        model.addAttribute("users", listUser); //ajoute un objet à "model". On précise son nom, et l'objet en lui même

        return "home"; //nom du fichier html à appeler (src.main.resources.templates.home.html)
    }

    @GetMapping("/createUser")
	public String createUser(Model model) {
		User newC = new User();
		model.addAttribute("user", newC);
		return "formNewUser";
	}

    @GetMapping("/updateUser/{usrId}")
	public String updateUser(@PathVariable("usrId") final Integer id, Model model) {
		User updatedC = userService.getUserbyId(id);		
		model.addAttribute("user", updatedC);	
		return "formUpdateUser";		
	}

    @GetMapping("/deleteUser/{usrId}") //type de requete HTTP + URL correspondante
    public ModelAndView deleteUser(@PathVariable("usrId") final Integer idVar){ //@PathVariable va prendre la valeur à la place d'idUrl 
        // et en faire une variable idVar
        userService.deleteUser(idVar); //suppression des users d'id = idVar

        return new ModelAndView("redirect:/"); //redirection vers le home
    }

    @GetMapping("/users/accountsOfUser/{usrId}")
	public String accountsOfUser(@PathVariable("usrId") final Integer id, Model model) {
		Iterable<Account> listAccounts = userService.getAccountsOfUser(id);
		model.addAttribute("accounts", listAccounts);
		return "formSeeUserAccounts";
	}

    @PostMapping("/saveUser") //type de requete HTTP + URL correspondante
    public ModelAndView saveUser(@ModelAttribute User user){ //@ModelAttribute va prendre les données du formulaire 
        // et en faire un objet User
		if(user.getUsrId() != null) {
			// User from update form has the password field not filled,
			// so we fill it with the current password.
			User current = userService.getUserbyId(user.getUsrId());
			user.setPassword(current.getPassword());
		}
        userService.saveUser(user); //sauvegarde de User

        return new ModelAndView("redirect:/"); //redirection vers le home
    }
}
