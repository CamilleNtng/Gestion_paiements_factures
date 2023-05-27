package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ModePaiementService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class ChequeController {

	@Autowired ModePaiementService modepaiementService;
	@Autowired ClientRepository clientRepository;
	
	@GetMapping("/cheque")
	public String pay() {
		return "paiementCheque.html";
	}
	
	@PostMapping("/cheque")
	public String payCheque(@RequestParam("num") String numform, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String loginSession = (String) session.getAttribute("login");
		Client client = clientRepository.getClient(loginSession);
		int num = Integer.parseInt(numform);
        modepaiementService.newCheque(client, num);
		return "validationPaiement.html";
	}
	
}
