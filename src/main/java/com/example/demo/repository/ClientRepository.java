package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository {

	@Query("SELECT c.codeClient from Client c where c.nom=:NOM and c.prenom=:PRENOM")
	public  int getCodeClient(@Param("NOM") String nom, @Param("PRENOM") String prenom);
}
