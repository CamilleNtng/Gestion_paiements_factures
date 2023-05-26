package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Facture;

import com.example.demo.repository.FactureRepository;
import com.example.demo.service.FactureService;


@Controller
public class FactureController {

	@Autowired FactureRepository factureRepository;
	@Autowired FactureService factureService;
		
	
	@GetMapping("/getAllInvoices")
	public String showPageInvoice(Model model, Facture facture) {
		
		/*// Test OK
		Facture fac = new Facture();
		fac.setIntitule("test");
		fac.setMontantF(10);
		fac.setNumFacture(0);
	    fac.setCodeClient("822109a8-6607-4815-a7e2-a4613f6acc7b");
		factureRepository.save(fac);*/
		
		model.addAttribute("factures", factureRepository.getFactures());
		
		return "gererFacture.html";
	}
	
	@GetMapping("/addInvoice")
	public String formAddInvoice() {
		return "addInvoice.html";
	}
	
	@PostMapping("/addInvoice")
	public String gererFacture(Model model,
								@RequestParam("num") int num,
								@RequestParam("intitule") String intitule,
								@RequestParam("montant") int montant,
								@RequestParam("nom") String nom,
								@RequestParam("prenom") String prenom) {
		
		factureService.addFacture(num, intitule, montant, nom, prenom);
		model.addAttribute("factures", factureRepository.getFactures());
		return "gererFacture.html";
	}
	
	/*
	@GetMapping("/getAllInvoices")
	public void testFacture() {
		Facture facture = new Facture();
		facture.setIntitule("test");
		facture.setMontantF(10);
		facture.setNumFacture(0);
		facture.setCodeClient(null);
	}*/
	
	//affecter facture a un client
	
	/*
	@PostMapping(value="/affectInvoiceClient")
	public ModelAndView affectInvoiceClient() { //@Request en arg
		ModelAndView m = new ModelAndView();
		return m;
	}
	
	@GetMapping("/addInvoice")
	public String showInvoices(Model model, Facture facture) {
		model.addAttribute("factures", factureRepository.getFactures());
		return "gererFacture.html";
	}
	
	@PostMapping("/addInvoice")
	public String gererFacture(Model model, Facture facture) {
		factureRepository.save(facture);
		model.addAttribute("factures", factureRepository.getFactures());
		return "gererFacture.html";
	}
	*/
	
}
