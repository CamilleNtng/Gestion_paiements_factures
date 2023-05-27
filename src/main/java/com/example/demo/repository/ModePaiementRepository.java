package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ModePaiement;


@Repository
public interface ModePaiementRepository extends JpaRepository<ModePaiement, String>{
	
	@Query("select m.codePaiement, type(m) from ModePaiement m")
	public List<Object[]> getMode();

}
