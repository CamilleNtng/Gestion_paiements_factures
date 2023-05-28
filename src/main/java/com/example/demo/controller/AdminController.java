package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.AdminService;

import jakarta.servlet.http.HttpSession;
import lombok.Data;


@Data
@Controller
public class AdminController {
	
	@Autowired ClientRepository clientRepository;
	@Autowired AdminRepository adminRepository;
	@Autowired AdminService adminService;

	
	// creation de l'admin en dur
	@GetMapping("/connexionAdmin")
	public String ConnexionAdmin() {
		adminService.admin();
		return "connexionAdmin.html";
	}
	
	// connexion admin
	@PostMapping("/connexionAdmin")
	public String authentification(Model model,
									HttpSession session,
								   	@RequestParam("loginAdmin") String login,
								   	@RequestParam("passwordAdmin") String password) {
				
		String loginSession = (String) session.getAttribute("login");
		
		if(loginSession == null) {
			session.setAttribute("login", login);
		}
		System.out.println("login dans la session : " + loginSession);
		
		int n = adminRepository.checkUser(login, password);
		
		if( n == 1) {
			return "welcomeAdmin.html";
		}
		
		else {
			//message d'erreur
			return "connexionAdmin.html";
		}
		
	}
	
	// affichage dashboard admin
	@GetMapping("/welcomeAdmin")
	public String dashboard() {
		return "welcomeAdmin.html";
	}
	
	// deconnexion admin
	@PostMapping("/deconnexionAdmin")
	public String deconnexionAdmin(HttpSession session) {
		
		String loginSession = (String) session.getAttribute("login");
		System.out.println("login dans la session : " + loginSession);
		session.invalidate(); // Supprime toutes les données de session
		System.out.println("login dans la session : " + loginSession);

	    return "connexionAdmin.html";
	}
			
}
