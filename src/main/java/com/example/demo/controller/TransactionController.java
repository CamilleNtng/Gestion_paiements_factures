package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Transaction;
import com.example.demo.repository.TransactionRepository;


@Controller
public class TransactionController {
	
	@Autowired TransactionRepository transactionRepository;

	@GetMapping("/getAllTransactions")
	public String showTransactions(Model model, Transaction transaction) {
		
		model.addAttribute("transactions", transactionRepository.getTransactions());
		return "gererTransaction.html";
	}
}
