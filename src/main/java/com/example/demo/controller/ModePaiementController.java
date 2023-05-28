package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ModePaiementController {
	
	// choix du mode de paiement
	@GetMapping("/pay")
	public String estimate() {
		return "pay.html";
	}
	
	// recuperation du mode de paiement choisi
	@PostMapping("/pay")
	public String estimatePaid(@RequestParam("modepaiement") String modepaiement, Model model) {
				
		if (modepaiement.equals("carte")) {			
			return "paiementCarte.html";
	    }
		else if (modepaiement.equals("cheque")) {
	    	return "paiementCheque.html";
	    }
	    else {
	    	// aucun choix de paiement
	    	return "error.html";
	    }
	}
	
}
