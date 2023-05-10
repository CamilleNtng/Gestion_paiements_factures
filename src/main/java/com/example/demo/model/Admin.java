package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Admin {

	@Id
	private int id;
	
	private int password;
	private String nom;
	private String prenom;
	
}
