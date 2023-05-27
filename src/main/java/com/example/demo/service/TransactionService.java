package com.example.demo.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;


@Service
public class TransactionService {
	
	public int verification(int montantFacture, int montantTransaction) {
		
		if(montantFacture == montantTransaction) {
			return 1;
		}
		else {
			return 0;
		}
		
	}

	public LocalDate dateTransaction() {
		return LocalDate.now();
	}
	
}