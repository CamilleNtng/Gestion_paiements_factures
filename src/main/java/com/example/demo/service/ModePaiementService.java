package com.example.demo.service;

import java.time.LocalDate;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CarteBancaire;
import com.example.demo.model.Cheque;
import com.example.demo.model.Client;
import com.example.demo.repository.ModePaiementRepository;


@Service
public class ModePaiementService {

	@Autowired ModePaiementRepository modepaiementRepository;
	
	
	// creation d'un code
	public int createCode() {
		
		Random rand = new Random();
		int code = rand.nextInt(1000000000);
		return code;
	}
	
	// creation d'une carte bancaire
	public CarteBancaire newCarte(Client client, int num, LocalDate date) {
		
		CarteBancaire carte = new CarteBancaire();
		int code = createCode();
		carte.setCodePaiement(code);
		carte.setClient(client);
		carte.setNumCarte(num);
		carte.setDateExpiration(date);
		modepaiementRepository.save(carte);
		return carte;
	}
	
	// creation d'un cheque
	public Cheque newCheque(Client client, int num) {
		
		Cheque cheque = new Cheque();
		int code = createCode();
		cheque.setCodePaiement(code);
		cheque.setClient(client);
		cheque.setNumCheque(num);
		modepaiementRepository.save(cheque);
		return cheque;
	}

}
