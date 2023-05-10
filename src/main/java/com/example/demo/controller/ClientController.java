package com.example.demo.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.persistence.Entity;
import lombok.Data;


@Entity
@Data
public class ClientController {

	@GetMapping(value="/connexionClient")
	public String ConnexionClient() {
		return "connexionClient";
	}
	
	@PostMapping(value="/connexionClient")
	public ModelAndView verifierConnexion(@RequestParam("loginClient") String login, @ RequestParam("passwordClient") String password) {
		
		ModelAndView m = new ModelAndView();
		
		if(password.equals(login+"123")) {
			
			m.addObject("identifiant", login);
			m.setViewName("welcomeClient");
			
		}
		else {
			m.setViewName("connexionClient");
		}
		return m;
	}
	
//	@PostMapping(value="/connexion")
//	public ModelAndView connexion(@RequestParam("login") String login,
//								  @RequestParam("password") String password{
//		
//		ModelAndView m = new ModelAndView();
//		
//		// recherche de la personne s'il elle existe
//		Optional<Personne> p = personneRepository.findById(nom);
//		
//		if(p.isPresent()) {
//			// la personne existe
//			
//			
//		} 
//		else {
//			
//		}
//		m.setViewName("");
//		return m;
//	}
}
