package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Facture;
import com.example.demo.repository.ClientRepository;


@Service
public class AdminService {

	@Autowired ClientRepository clientRepository;
	
	public Facture addFacture(int montant, String nom, String prenom) {
		Facture f = new Facture();
		f.setMontantF(montant);
		int numClient = clientRepository.getCodeClient(nom, prenom);
	    f.setCodeClient(numClient);
		f.setCodeClient(numClient);
		return f;
	}
}
