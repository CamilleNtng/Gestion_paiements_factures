package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="profil")
public class Personne {
	
	@Id
	private int id;
	
	private int password;
	private String nom;
	private String prenom;
	
}
