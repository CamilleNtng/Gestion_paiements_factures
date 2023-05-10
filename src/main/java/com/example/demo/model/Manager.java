package com.example.demo.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue(value = "Manager")
public class Manager extends Personne {
	
}
