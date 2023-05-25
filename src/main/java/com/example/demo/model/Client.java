package com.example.demo.model;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Client {
	
	@Id
	private String codeClient;
	
	private String passwordClient;
	private String loginClient;
	
	private String nom;
	private String prenom;
	
	@OneToMany(mappedBy="client")
	private Set<ModePaiement> setModePaiement; 
	
}
