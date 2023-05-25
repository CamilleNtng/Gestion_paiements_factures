package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Facture;

@Repository
public interface FactureRepository extends JpaRepository<Facture, String> {
	 
	@Query("select f.numFacture, f.intitule, f.montantF, f.codeClient from Facture f")
	public List<Object[]> getFactures();
	
	
	/*
	@Query("SELECT f FROM Facture f WHERE f.codeClient = :codeClient")
	List<Facture> getFacture(@Param("codeClient") int codeClient);
	
	@Query("SELECT f FROM Facture f")
	List<Facture> facture();
	*/
}
