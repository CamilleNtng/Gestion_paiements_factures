package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, String>{

	@Query("SELECT c.codeClient from Client c where c.nom=:NOM and c.prenom=:PRENOM")
	public String getCodeClient(@Param("NOM") String nom, @Param("PRENOM") String prenom);
	
	@Query("SELECT count(*) from Client c WHERE c.loginClient=:LOGIN AND c.passwordClient=:PASSWORD")
	public int checkUser(@Param("LOGIN") String login, @Param("PASSWORD") String password);
	
	@Query("SELECT c.loginClient, c.passwordClient from Client c WHERE c.codeClient=:CODE")
	public List<Object[]> getInfo(@Param("CODE") String code);
	
	@Query("SELECT c.codeClient from Client c where c.loginClient=:LOGIN")
	public String getCodeClientFromLogin(@Param("LOGIN") String login);
	
	@Query("SELECT c from Client c where c.loginClient=:LOGIN")
	public Client getClient(@Param("LOGIN") String login);
	
}
