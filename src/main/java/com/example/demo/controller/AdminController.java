package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Client;
import com.example.demo.model.Admin;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.AdminService;

import jakarta.servlet.http.HttpSession;
import lombok.Data;


@Data
@Controller
public class AdminController {
	
	@Autowired AdminService adminService;
	@Autowired ClientRepository clientRepository;
	@Autowired AdminRepository adminRepository;

	@GetMapping("/connexionAdmin")
	public String ConnexionAdmin() {
		/*
		Admin admin = new Admin();
		admin.setId(0);
		admin.setLoginAdmin("admin");
		admin.setPasswordAdmin("admin");
		admin.setNom("camille");
		admin.setPrenom("florence");
		adminRepository.save(admin);
		*/
		return "connexionAdmin.html";
	}
	
	@PostMapping("/connexionAdmin")
	public String authentification(Model model,
									HttpSession session,
								   	@RequestParam("loginAdmin") String login,
								   	@RequestParam("passwordAdmin") String password) {
				
		String loginSession = (String) session.getAttribute("login");
		
		if(loginSession == null) {
			session.setAttribute("login", login);
		}
		System.out.println("login dans la session : " + (String) session.getAttribute("login"));
		
		int n = adminRepository.checkUser(login, password);
		
		if( n == 1) {
			model.addAttribute("id", adminRepository.getName(loginSession));
			return "welcomeAdmin.html";
		}
		
		else {
			//message d'erreur
			return "connexionAdmin.html";
		}
		
	}
	
	@GetMapping("/newAccount")
	public String newAccount() {
		return "formAccount.html";
	}
	
	@PostMapping("/newAccount")
	public String account(Model model,
							@RequestParam("nom") String nom,
							@RequestParam("prenom") String prenom) {
		
		Client client = adminService.createAccount(nom, prenom);
		String id = client.getCodeClient();
		model.addAttribute("info", clientRepository.getInfo(id));
		return "new.html";
	}
	
	/*
	
	@PostMapping(value="/test1")
	public void test(@RequestParam("montant")int montant, @RequestParam("nom")String nom, @RequestParam("prenom") String prenom) {
		adminService.addFacture(montant,nom,prenom);
	}
	
	@GetMapping(value="/test1")
	public String test() {
		return "test1.html";
	}
	@PostMapping(value="/test1")
	public String test1(@RequestParam("montant") int montant, @RequestParam("num_facture") int numFacture,@RequestParam("nom")String nom ,@RequestParam("prenom") String prenom){
		adminService.addFacture(montant,numFacture,nom,prenom);
		//System.out.println(adminService.addFacture(montant,numFacture,nom,prenom));
		return "test1.html";
		
	}
	
	@PostMapping(value="/test1")
	public void test1() {
		System.out.println(adminService.facture());
	}
	*/
		
}
