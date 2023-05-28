package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Facture;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.FactureRepository;


@Service
public class FactureService {

	@Autowired ClientRepository clientRepository;
	@Autowired FactureRepository factureRepository;
	
	// ajout d'une facture
	public Facture addFacture(int num, String intitule, int montant, String nom, String prenom) {
		
		Facture f = new Facture();
		f.setNumFacture(num);
		f.setMontantF(montant);
		f.setIntitule(intitule);
		String numClient = clientRepository.getCodeClient(nom, prenom);
	    f.setCodeClient(numClient);
		factureRepository.save(f);
		
		return f;
	}
		
}
