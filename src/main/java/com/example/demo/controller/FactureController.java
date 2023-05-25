package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Facture;
import com.example.demo.repository.FactureRepository;


@Controller
public class FactureController {

@Autowired FactureRepository factureRepository;
	
	@PostMapping(value="/affectInvoiceClient")
	public ModelAndView affectInvoiceClient() { //@Request en arg
		ModelAndView m = new ModelAndView();
		return m;
	}
	
	/*
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
