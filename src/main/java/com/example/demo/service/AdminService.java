package com.example.demo.service;
import org.springframework.util.StringUtils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Client;
import com.example.demo.model.Facture;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.FactureRepository;


@Service
public class AdminService {

	@Autowired ClientRepository clientRepository;
	@Autowired FactureRepository factureRepository;
	public Facture addFacture(int montant,int numFacture, String nom, String prenom) {
		Facture f = new Facture();
		f.setMontantF(montant);
		f.setNumFacture(numFacture);
		int numClient = clientRepository.getCodeClient(nom, prenom);
	    f.setCodeClient(numClient);
		f.setCodeClient(numClient);
		factureRepository.save(f);
		return f;
	}
	
	public Client addClient (String nom, String prenom, String login) {
		Client c = new Client();
		int CodeClient= 1111;
		//to do + mettre mdp aleatoire comme code client
		return c;
		
	}
	
	public List<Facture> showFacture (int CodeClient){
		return factureRepository.getFacture(CodeClient);
	}
	public List<Facture> facture (){
		return factureRepository.facture();
	}
}
