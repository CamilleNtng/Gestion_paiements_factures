package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin, String>{

	@Query("select a.login from Admin a")
	public List<Object[]> ConnexionAdmin();
	
}