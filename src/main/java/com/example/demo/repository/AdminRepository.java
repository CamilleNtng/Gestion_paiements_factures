package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin, String>{

	@Query("select a.loginAdmin from Admin a")
	public List<Object[]> ConnexionAdmin();
	
	@Query("SELECT count(*) from Admin a WHERE a.loginAdmin=:LOGIN AND a.passwordAdmin=:PASSWORD")
	public int checkUser(@Param("LOGIN") String login, @Param("PASSWORD") String password);
	
	@Query("SELECT a.prenom from Admin a WHERE a.loginAdmin=:LOGIN")
	public String getName(@Param("LOGIN") String login);
	
}
