package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Facture;

@Repository
public interface FactureRepository extends JpaRepository<Facture, String> {
	 
	@Query("select f.numFacture, f.intitule, f.montantF, f.codeClient, f.statut from Facture f")
	public List<Object[]> getFactures();
	
	@Query("select f.numFacture, f.intitule, f.montantF from Facture f where f.statut='Payée' and f.codeClient=:CODE")
	public List<Object[]> getPaidInvoices(@Param("CODE") String code);
	
	@Query("select f.numFacture, f.intitule, f.montantF from Facture f where f.statut='Non payée' and f.codeClient=:CODE")
	public List<Object[]> getInvoicesToPay(@Param("CODE") String code);
	
}
