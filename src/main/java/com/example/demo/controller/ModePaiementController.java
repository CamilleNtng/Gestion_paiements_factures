package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ModePaiementController {
	
	
	@GetMapping("/pay")
	public String estimate() {
		return "pay.html";
	}
	
	@PostMapping("/pay")
	public String estimatePaid(@RequestParam("modepaiement") String modepaiement) {
		
		//afficher montant
		/*
		@RequestParam("sommeMontants") Integer sommeMontants, Model model, RedirectAttributes redirectAttributes
		Object prix = model.getAttribute("sommeMontants");
		model.addAttribute("prix", prix);
		Integer sommeMontants = (Integer) model.getAttribute("sommeMontants");
		redirectAttributes.addFlashAttribute("sommeMontants", sommeMontants);
		System.out.println(sommeMontants);
		Integer sommeMontants = (Integer) redirectAttributes.getFlashAttributes().get("sommeMontants");
		sommeMontants = (Integer) redirectAttributes.getFlashAttributes().get("sommeMontants");
		redirectAttributes.addFlashAttribute("sommeMontants", sommeMontants);
		model.addAttribute("sommeMontants", sommeMontants);
		*/
				
		if (modepaiement.equals("carte")) {			
			return "paiementCarte.html";
	    }
		else if (modepaiement.equals("cheque")) {
	    	return "paiementCheque.html";
	    }
	    else {
	    	// message veuillez sélectionner un mode de paiement
	    	return "test1.html";
	    }
	}
	
}
