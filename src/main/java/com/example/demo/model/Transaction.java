package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Transaction {
	
	@EmbeddedId
	TransactionPK transactionPK;
	
	private int montantT;
	
	private LocalDate dateTranscation;//yyyy-mm-dd

}
