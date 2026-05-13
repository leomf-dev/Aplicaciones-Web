package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.client.RolClient;

@Controller
@RequestMapping("/roles")
public class RolController {
	private final RolClient client;
	public RolController(RolClient client) {
		this.client = client;
	}
	
	// Metodos que implementan la interfaz
	// Listar
	@GetMapping
	public String listar(Model model) {
		model.addAttribute("roles", client.listar());
		return "roles";
	}
	
}
