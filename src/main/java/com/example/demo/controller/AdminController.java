package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.model.Model;
import jakarta.persistence.Entity;
import jakarta.servlet.http.HttpSession;
import lombok.Data;


@Entity
@Data
@Controller
public class AdminController {

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
	
}
