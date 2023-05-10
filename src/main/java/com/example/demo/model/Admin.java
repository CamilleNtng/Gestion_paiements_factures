package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue(value = "Admin")
public class Admin {

	
}
