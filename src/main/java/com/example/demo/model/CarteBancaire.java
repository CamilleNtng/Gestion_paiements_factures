package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.Data;


@Data
@Entity
@DiscriminatorValue(value = "CarteBancaire")
public class CarteBancaire extends ModePaiement {
	
	private int numCarte;
	
	private LocalDate dateExpiration;//yyyy-mm-dd
	
}
