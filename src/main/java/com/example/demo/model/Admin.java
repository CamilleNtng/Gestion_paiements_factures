package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Admin {

	@Id
	private int id;
	
	private String passwordAdmin;
	private String loginAdmin;
	
	private String nom;
	private String prenom;
	
}
