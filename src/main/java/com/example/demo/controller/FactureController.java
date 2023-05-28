package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Facture;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.FactureRepository;
import com.example.demo.service.FactureService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
public class FactureController {

	@Autowired FactureRepository factureRepository;
	@Autowired FactureService factureService;
	@Autowired ClientRepository clientRepository;
		
	
	///////////// vue factures côté admin
	
	// affichage des factures
	@GetMapping("/getAllInvoices")
	public String showPageInvoice(Model model, Facture facture) {
				
		model.addAttribute("factures", factureRepository.getFactures());
		
		return "gererFacture.html";
	}
	
	// ajout facture par admin
	@GetMapping("/addInvoice")
	public String formAddInvoice() {
		return "addInvoice.html";
	}
	
	// affichage des factures mis a jour
	@PostMapping("/addInvoice")
	public String gererFacture(Model model,
								@RequestParam("num") int num,
								@RequestParam("intitule") String intitule,
								@RequestParam("montant") int montant,
								@RequestParam("nom") String nom,
								@RequestParam("prenom") String prenom) {
		
		factureService.addFacture(num, intitule, montant, nom, prenom);
		model.addAttribute("factures", factureRepository.getFactures());
		return "gererFacture.html";
	}
	
	
	
	///////////// vues des factures côté client
	
	
	// affichage des factures a payer
	@GetMapping("/invoicesToPay")
	public String showInvoicesToPay(Model model, Facture facture, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String loginSession = (String) session.getAttribute("login");
		System.out.println("login dans la session : " + loginSession);
		String codeClient = clientRepository.getCodeClientFromLogin(loginSession);
		model.addAttribute("factures", factureRepository.getInvoicesToPay(codeClient));
		return "invoicesToPay.html";
	}
	
	// affichage des factures selectionnees pour payer
	@PostMapping("/invoicesToPay")
	public String processPayment(Model model, Facture facture,
								@RequestParam("selectedInvoices") List<String> selectedInvoices,
								HttpServletResponse response) {
		
		// recuperation des factures selectionnees pour les utiliser ailleurs
		try {
		    String selectedInvoicesString = String.join(",", selectedInvoices);
		    String encodedSelectedInvoices = URLEncoder.encode(selectedInvoicesString, "UTF-8");
		    Cookie cookie = new Cookie("selectedInvoices", encodedSelectedInvoices);
		    response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
		}

		System.out.println(selectedInvoices);
		
		// affichage du devis
		model.addAttribute("selectedInvoices", selectedInvoices);
		List<Object[]> factures = new ArrayList<Object[]>();
		
	    if (selectedInvoices != null) {
	    		    	
	        for (String invoiceNumber : selectedInvoices) {
	        	factures.addAll(factureRepository.getInvoicesByNum(invoiceNumber));
	            System.out.println("Facture sélectionnée : " + invoiceNumber);
	        }
	        model.addAttribute("choix", factures);
	        
	        int sommeMontants = 0;
	        for (Object[] c : factures) {
	            int montant = Integer.parseInt(c[2].toString());
	            sommeMontants += montant;
	        }
	        model.addAttribute("sommeMontants", sommeMontants);
	        	        
	        return "pay.html";
	    }
	    else {
	    	// messgage comme quoi il a rien selectionne le couz (sur meme vue ou ailleurs)
	    	return "test1.html";
	    }
	}
	
	// affichage des factures payees
	@GetMapping("/invoicesPaid")
	public String showInvoicesPaid(Model model, Facture facture, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String loginSession = (String) session.getAttribute("login");
		System.out.println("login dans la session : " + loginSession);
		String codeClient = clientRepository.getCodeClientFromLogin(loginSession);
		model.addAttribute("factures", factureRepository.getPaidInvoices(codeClient));
		return "invoicesPaid.html";
	}
	
	/*
	// change le statut de la facture quand elle est payee
	@GetMapping("/validation")
	public String changeStatut(HttpServletRequest request) {
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        if (cookie.getName().equals("selectedInvoices")) {
		        	try {
			            String encodedSelectedInvoices = cookie.getValue();
			            String selectedInvoicesString = URLDecoder.decode(encodedSelectedInvoices, "UTF-8");
			            List<String> selectedInvoices = Arrays.asList(selectedInvoicesString.split(","));
			            System.out.println(selectedInvoices);
			            
			            List<Integer> selectedInvoicesInt = new ArrayList<>();

			            for (String invoice : selectedInvoices) {
			                selectedInvoicesInt.add(Integer.parseInt(invoice));
			            }
			            
			            for (int invoiceNumber : selectedInvoicesInt) {
			    			Facture facture = factureRepository.getUneFacture(invoiceNumber);
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
			return "test1.html";
		}
	}
	*/	
	
}
