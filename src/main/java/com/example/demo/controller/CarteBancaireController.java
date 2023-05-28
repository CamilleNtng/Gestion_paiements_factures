package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.CarteBancaire;
import com.example.demo.model.Client;
import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionPK;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.FactureRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.ModePaiementService;
import com.example.demo.service.TransactionService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class CarteBancaireController {

	@Autowired ModePaiementService modepaiementService;
	@Autowired ClientRepository clientRepository;
	@Autowired TransactionRepository transactionRepository;
	@Autowired FactureRepository factureRepository;
	@Autowired TransactionService transactionService;
	
	
	// affichage formulaire pour payer par carte
	@GetMapping("/card")
	public String pay() {
		return "paiementCarte.html";
	}
	
	// ajout de la carte et de la transaction
	@PostMapping("/card")
	public String payCard(@RequestParam("num") String numform,
							@RequestParam("date") String dateform,
							HttpServletRequest request) {
		
		// recuperation des factures selectionnees
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        if (cookie.getName().equals("selectedInvoices")) {
		        	try {
			            String encodedSelectedInvoices = cookie.getValue();
			            String selectedInvoicesString = URLDecoder.decode(encodedSelectedInvoices, "UTF-8");
			            List<String> selectedInvoices = Arrays.asList(selectedInvoicesString.split(","));
			            System.out.println(selectedInvoices);
				        
			            // ajout carte
			            HttpSession session = request.getSession();
			    		String loginSession = (String) session.getAttribute("login");
			    		Client client = clientRepository.getClient(loginSession);
			    		int num = Integer.parseInt(numform);
			    		LocalDate date = LocalDate.parse(dateform + "-01");
			    		
			            CarteBancaire carte = modepaiementService.newCarte(client, num, date); 
			            
			            // ajout transaction (une transaction par facture)
			            for (String invoiceNumber : selectedInvoices) {
			            	
			            	int numF = Integer.parseInt(invoiceNumber);
			            	int montantF = factureRepository.getMontantFacture(numF);
			            	System.out.println(montantF);
			            	
			            	Transaction transaction = new Transaction();
			            	transaction.setMontantT(montantF);
			            	transaction.setDateTranscation(transactionService.dateTransaction());
			                TransactionPK pk = new TransactionPK();
			                pk.setCodePaiement(carte.getCodePaiement());
			                pk.setNumFacture(numF);
			                transaction.setTransactionPK(pk);
			                transactionRepository.save(transaction);
			            }
				            
				        break;
			            
		        	} catch (UnsupportedEncodingException e) {
		    		    e.printStackTrace();
		    		}
			            
			    }
		    }
		    return "validationPaiement.html";
		}
		else {
			return "test1.html";
		}
	}
		
}
