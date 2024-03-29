package com.example.demo.model;

import java.util.*;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Facture {
	
	@Id
	private int numFacture;
	
	private String intitule;
	private int montantF;
	private String codeClient;
	private String statut = "Non payée";
	
	@ManyToMany(mappedBy="factures")
	private List<ModePaiement> paiementFacture = new ArrayList<ModePaiement>();
	
}
