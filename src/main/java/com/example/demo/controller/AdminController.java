package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.ClientRepository;
import com.example.demo.service.AdminService;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import lombok.Data;


@Data
@Controller
public class AdminController {
	@Autowired AdminService adminService;
	@Autowired ClientRepository clientRepository;
	@GetMapping(value="/connexionAdmin")
	public String ConnexionAdmin() {
		return "connexionAdmin.html";
	}
	
	@PostMapping("/connexionAdmin")
	public String authentification(Model model,
								   HttpSession session,
								   @RequestParam("loginAdmin") String login) {
		
		String loginSession = (String) session.getAttribute("login");
		
		if(loginSession == null) {
			session.setAttribute("login", login);
		}
		
		System.out.println("login dans la session : " + (String) session.getAttribute("login"));
		return "welcomeAdmin.html";
	}
	/*
	@PostMapping(value="/test1")
	public void test(@RequestParam("montant")int montant, @RequestParam("nom")String nom, @RequestParam("prenom") String prenom) {
		adminService.addFacture(montant,nom,prenom);
	}
	*/
	
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
	/*
	@PostMapping(value="/test1")
	public void test1() {
		System.out.println(adminService.facture());
	}
	*/
}
