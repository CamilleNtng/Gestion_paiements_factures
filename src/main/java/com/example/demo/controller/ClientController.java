package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import lombok.Data;


@Data
@Controller
public class ClientController {

	@GetMapping(value="/connexionClient")
	public String ConnexionClient() {
		return "connexionClient.html";
	}
	
	@PostMapping("/connexionClient")
	public String authentification(Model model,
								   HttpSession session,
								   @RequestParam("loginClient") String login) {
		
		String loginSession = (String) session.getAttribute("login");
		
		if(loginSession == null) {
			session.setAttribute("login", login);
		}
		
		System.out.println("login dans la session : " + (String) session.getAttribute("login"));
		return "welcomeClient.html";
	}
	
}
