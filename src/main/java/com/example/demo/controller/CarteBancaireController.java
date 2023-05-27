package com.example.demo.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class CarteBancaireController {

	@Autowired ModePaiementService modepaiementService;
	@Autowired ClientRepository clientRepository;
	@Autowired TransactionRepository transactionRepository;
	@Autowired FactureRepository factureRepository;
	@Autowired TransactionService transactionService;
	
	@GetMapping("/card")
	public String pay() {
		return "paiementCarte.html";
	}
	
	@PostMapping("/card")
	public String payCard(@RequestParam("num") String numform,
							@RequestParam("date") String dateform,
							HttpServletRequest request,
							Model model,
							@ModelAttribute("selectedInvoices") ArrayList<String> selectedInvoices) {
		
        /*
          ArrayList<String>
         List<Integer> selectedInvoices = (List<Integer>) model.getAttribute("selectedInvoices");
        List<Integer> invoices = new ArrayList<>();

        for (String x : selectedInvoices) {
            invoices.add(Integer.parseInt(x));
        }*/
        
   
         /*
        HttpSession session = request.getSession();
		String loginSession = (String) session.getAttribute("login");
		Client client = clientRepository.getClient(loginSession);
		int num = Integer.parseInt(numform);
		LocalDate date = LocalDate.parse(dateform + "-01");
		
        CarteBancaire carte = modepaiementService.newCarte(client, num, date); 
        
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
            
        }*/
		
		return "validationPaiement.html";
	}
		
}
