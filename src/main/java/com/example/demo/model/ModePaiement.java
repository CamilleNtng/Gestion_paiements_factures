package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;


@Entity
@Data
public class ModePaiement {
	
	@Id
	private int codePaiement;
	
	@ManyToOne
	private Client client;
	
	@ManyToMany
	@JoinTable(name="Transaction", 
				joinColumns= {@JoinColumn(name="codePaiement")},
						inverseJoinColumns= {@JoinColumn(name="numFacture")})
	private Set<Facture> factures= new HashSet<Facture>();
	
}
