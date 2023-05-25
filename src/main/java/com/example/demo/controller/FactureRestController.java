package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Facture;
import com.example.demo.repository.FactureRepository;


@RestController
public class FactureRestController {

	@Autowired FactureRepository factureRepository;
	
	/*
	@PostMapping("/getAllInvoices")
	public String Invoice(Model model, Facture facture) {
		factureRepository.save(facture);
		model.addAttribute("factures", factureRepository.getFactures());
		return "gererFacture.html";
	}*/
	
	
	
	/*
	@GetMapping("/getAllInvoices")
	public List<Facture> getAllInvoices(){
		return factureRepository.findAll();
	}
	
	@PostMapping("/newInvoice")
	public Facture newInvoice(@RequestBody Facture facture) {
		return factureRepository.save(facture);
	}
	
	@PutMapping("")
	public Facture updateInvoice(@RequestBody Facture facture) {
		return factureRepository.save(facture);
	}
	
	@DeleteMapping("")
	public void deleteInvoice(@Request) {
		factureRepository.deleteById(id);
	}
	*/
}
