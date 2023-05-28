package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

	// affichage premiere page
	@GetMapping("/home")
	public String Home() {
		return "home.html";
	}
	
}
