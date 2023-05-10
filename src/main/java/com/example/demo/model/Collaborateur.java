package com.example.demo.model;

import java.util.*;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@DiscriminatorValue(value = "Collaborateur")
public class Collaborateur extends Personne{
	
}
