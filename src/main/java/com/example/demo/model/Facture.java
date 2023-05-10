package com.example.demo.model;

import java.util.*;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Facture {
	
	@Id
	private int numFacture;
	
	private int montantF;
	
	@ManyToMany(mappedBy="modePaiement")
	private List<ModePaiement> paiementFacture = new ArrayList<ModePaiement>();
	
}
