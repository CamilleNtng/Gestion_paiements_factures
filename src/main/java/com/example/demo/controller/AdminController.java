package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repository.AdminRepository;

import jakarta.persistence.Entity;
import lombok.Data;


@Entity
@Data
public class AdminController {
	
	@Autowired
	AdminRepository adminRepository;

	@GetMapping(value="/connexionAdmin")
	public String ConnexionAdmin() {
		return "connexionAdmin";
	}
	
	@PostMapping(value="/connexionAdmin")
	public ModelAndView verifierConnexion(@RequestParam("loginAdmin") String login, @RequestParam("passwordAdmin") String password) {
		
		ModelAndView m = new ModelAndView();
		List<Object[]> list = adminRepository.getLoginPassword();		
		
//		if(list.contains((login,password))) {
//			
//			m.addObject("identifiant", login);
//			m.setViewName("welcomeAdmin");
//			
//		}
//		else {
//			m.setViewName("connexionAdmin");
//		}
		return m;
	}
}
