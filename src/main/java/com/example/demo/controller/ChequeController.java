package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Cheque;
import com.example.demo.model.Client;
import com.example.demo.model.Facture;
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
public class ChequeController {

	@Autowired ModePaiementService modepaiementService;
	@Autowired ClientRepository clientRepository;
	@Autowired FactureRepository factureRepository;
	@Autowired TransactionService transactionService;
	@Autowired TransactionRepository transactionRepository;
	
	
	// affichage formulaire pour payer par cheque
	@GetMapping("/cheque")
	public String pay() {
		return "paiementCheque.html";
	}
	
	// ajout du cheque et de la transaction
	@PostMapping("/cheque")
	public String payCheque(@RequestParam("num") String numform, HttpServletRequest request) {
		
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
					        
			            // ajout cheque
			            HttpSession session = request.getSession();
			    		String loginSession = (String) session.getAttribute("login");
			    		Client client = clientRepository.getClient(loginSession);
			    		int num = Integer.parseInt(numform);
			    		Cheque cheque = modepaiementService.newCheque(client, num);
			    		
			            // ajout transaction (une transaction par facture)
			            for (String invoiceNumber : selectedInvoices) {
				            	
			            	int numF = Integer.parseInt(invoiceNumber);
			            	int montantF = factureRepository.getMontantFacture(numF);
			            	System.out.println(montantF);
					            	
			            	Transaction transaction = new Transaction();
			            	transaction.setMontantT(montantF);
			            	transaction.setDateTranscation(transactionService.dateTransaction());
			                TransactionPK pk = new TransactionPK();
			                pk.setCodePaiement(cheque.getCodePaiement());
			                pk.setNumFacture(numF);
			                transaction.setTransactionPK(pk);
			                transactionRepository.save(transaction);
			                
			                // change statut de la facture
			                Facture facture = factureRepository.getUneFacture(numF);
			                facture.setStatut("Payée");
			    			factureRepository.save(facture);
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
			return "error.html";
		}
	}
	
}
