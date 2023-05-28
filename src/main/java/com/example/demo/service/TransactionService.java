package com.example.demo.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;


@Service
public class TransactionService {
	
	/* 
	// PAS BESOIN
	public int verification(int montantFacture, int montantTransaction) {
		
		if(montantFacture == montantTransaction) {
			return 1;
		}
		else {
			return 0;
		}
		
	}
	*/

	// date de la transaction = date a laquelle elle est faite
	public LocalDate dateTransaction() {
		return LocalDate.now();
	}
	
}
