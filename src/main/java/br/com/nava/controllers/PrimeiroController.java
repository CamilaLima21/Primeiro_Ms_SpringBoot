package br.com.nava.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrimeiroController {
	
	@GetMapping("bem-vindo")
	public String bemVindo() {
		return "Bem Vindo!";
	}
	
	@PostMapping("bem-vindo")
	public String bemVindoPost() {
		return "Bem Vindo POST";
	}
}
