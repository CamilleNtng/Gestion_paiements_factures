package com.example.demo.model;

import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Client {
	
	@Id
	private int codeClient;
	
	private String nom;
	private String prenom;
	private String password;
	
	@OneToMany(mappedBy="client")
	private Set<ModePaiement> setModePaiement; 
	
}
