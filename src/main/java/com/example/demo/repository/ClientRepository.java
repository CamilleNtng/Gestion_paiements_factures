package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, String>{

	@Query("SELECT c.codeClient from Client c where c.nom=:NOM and c.prenom=:PRENOM")
	public int getCodeClient(@Param("NOM") String nom, @Param("PRENOM") String prenom);
	
	@Query("SELECT count(*) from Client c WHERE c.loginClient=:LOGIN AND c.passwordClient=:PASSWORD")
	public int checkUser(@Param("LOGIN") String login, @Param("PASSWORD") String password);
}