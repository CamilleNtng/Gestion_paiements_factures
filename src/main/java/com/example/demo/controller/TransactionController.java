package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TransactionController {

	@GetMapping("/getAllTransactions")
	public String showTransactions() {
		return "gererTransaction.html";
	}
}
