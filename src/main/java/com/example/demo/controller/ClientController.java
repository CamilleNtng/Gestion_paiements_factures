package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.ClientRepository;
import com.example.demo.model.Client;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import lombok.Data;


@Data
@Controller
public class ClientController {

	@Autowired ClientRepository clientRepository;

	@GetMapping("/connexionClient")
	public String ConnexionClient() {
		
		Client test = new Client();
		test.setCodeClient(null);
		test.setLoginClient("test");
		test.setPasswordClient("test");
		test.setPrenom("patient");
		test.setNom("zero");
		clientRepository.save(test);
		
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
