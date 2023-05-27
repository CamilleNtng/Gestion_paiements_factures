package com.example.demo.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Facture;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.FactureRepository;
import com.example.demo.service.FactureService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class FactureController {

	@Autowired FactureRepository factureRepository;
	@Autowired FactureService factureService;
	@Autowired ClientRepository clientRepository;
		
	// vue factures côté admin
	
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
	
	
	// vues des factures côté client
	
	@GetMapping("/invoicesToPay")
	public String showInvoicesToPay(Model model, Facture facture, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String loginSession = (String) session.getAttribute("login");
		System.out.println("login dans la session : " + loginSession);
		String codeClient = clientRepository.getCodeClientFromLogin(loginSession);
		model.addAttribute("factures", factureRepository.getInvoicesToPay(codeClient));
		return "invoicesToPay.html";
	}
	
	@PostMapping("/invoicesToPay")
	public String processPayment(Model model, Facture facture,
								@RequestParam("selectedInvoices") ArrayList<String> selectedInvoices,//list
								RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addFlashAttribute("selectedInvoices", selectedInvoices);
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
	
	@GetMapping("/invoicesPaid")
	public String showInvoicesPaid(Model model, Facture facture, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String loginSession = (String) session.getAttribute("login");
		System.out.println("login dans la session : " + loginSession);
		String codeClient = clientRepository.getCodeClientFromLogin(loginSession);
		model.addAttribute("factures", factureRepository.getPaidInvoices(codeClient));
		return "invoicesPaid.html";
	}
	
	
	@GetMapping("/validation")
	public String changeStatut(Model model) {
		
		List<Integer> selectedInvoices = (List<Integer>) model.getAttribute("selectedInvoices");
		
		for (int invoiceNumber : selectedInvoices) {
			Facture facture = factureRepository.getUneFacture(invoiceNumber);
			facture.setStatut("Payée");
			factureRepository.save(facture);
		}
		
		return "validationPaiement.html";
	}
	
}
