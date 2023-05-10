package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue(value = "Cheque")
public class Cheque extends ModePaiement {
	
	@Id
	private int numCheque;

}
