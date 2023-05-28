package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.AdminService;

import jakarta.servlet.http.HttpSession;
import lombok.Data;


@Data
@Controller
public class ClientController {

	@Autowired ClientRepository clientRepository;
	@Autowired AdminService adminService;

	
	// formulaire connexion client
	@GetMapping("/connexionClient")
	public String ConnexionClient() {
				
		return "connexionClient.html";
	}
	
	// connexion client
	@PostMapping("/connexionClient")
	public String authentification(Model model,
								   HttpSession session,
								   @RequestParam("loginClient") String login,
								   @RequestParam("passwordClient") String password) {
		
		String loginSession = (String) session.getAttribute("login");
		
		if(loginSession == null) {
			session.setAttribute("login", login);
		}
		
		System.out.println("login dans la session : " + loginSession);
		
		int n = clientRepository.checkUser(login, password);
		
		if( n == 1) {
			return "welcomeClient.html";
		}
		
		else {
			// est cense n'y avoir qu'un profil
			return "error.html";
		}
	}
	
	// formulaire creation de compte client
	@GetMapping("/newAccount")
	public String newAccount() {
		return "formAccount.html";
	}
	
	// creation compte client par admin
	@PostMapping("/newAccount")
	public String account(Model model,
							@RequestParam("nom") String nom,
							@RequestParam("prenom") String prenom) {
		
		Client client = adminService.createAccount(nom, prenom);
		String id = client.getCodeClient();
		model.addAttribute("info", clientRepository.getInfo(id));
		return "new.html";
	}
	
	// demande creation compte client
	@GetMapping("/askAccount")
	public String askAccount() {
		return "askAccount.html";
	}
	
	// deconnexion client
	@PostMapping("/deconnexionClient")
	public String deconnexionClient(HttpSession session) {
			
		String loginSession = (String) session.getAttribute("login");
		System.out.println("login dans la session : " + loginSession);
		session.invalidate(); //supprime toutes les données de session
		System.out.println("login dans la session : " + loginSession);

	    return "connexionClient.html";
	}
	
	// retour au dashboard client
		@PostMapping("/dashboardClient")
		public String dasboardClient() {
				
		    return "welcomeClient.html";
		}
	
}
