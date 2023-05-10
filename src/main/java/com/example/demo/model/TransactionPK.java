package com.example.demo.model;

import jakarta.persistence.Embeddable;
import lombok.Data;


@Embeddable
@Data
public class TransactionPK {

	private int codePaiement;
	private int numFacture;
	
}
