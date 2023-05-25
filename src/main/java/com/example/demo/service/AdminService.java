package com.example.demo.service;
import org.springframework.util.StringUtils;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

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
		
	public List<Facture> showFacture (int CodeClient){
		return factureRepository.getFacture(CodeClient);
	}
	
	public List<Facture> facture (){
		return factureRepository.facture();
	}
	
	public String createLogin(String nom, String prenom) {
		String login = nom+prenom;
		return login;
	}
	
	public String createPassword() {
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < 10; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        
		String password = sb.toString();
		
		return password;
	}
	
	public UUID createCode() {
		UUID code = UUID.randomUUID();
		return code;
	}
	
	public Client createAccount(String nom, String prenom) {
		
		Client client = new Client();
		client.setNom(nom);
		client.setPrenom(prenom);
		String login = createLogin(nom, prenom);
		client.setLoginClient(login);
		String password = createPassword();
		client.setPasswordClient(password);
		UUID code = createCode();
		client.setCodeClient(code);
		clientRepository.save(client);
		
		return client;
	}	
	
}
