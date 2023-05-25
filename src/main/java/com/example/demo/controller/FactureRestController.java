package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Facture;
import com.example.demo.repository.FactureRepository;


@RestController
public class FactureRestController {

	@Autowired FactureRepository factureRepository;
	
	@GetMapping("/getAllInvoices")
	public List<Facture> getAllInvoices(){
		return factureRepository.findAll();
	}
	
	@PostMapping("/newInvoice")
	public Facture newInvoice(@RequestBody Facture facture) {
		return factureRepository.save(facture);
	}
	
}
