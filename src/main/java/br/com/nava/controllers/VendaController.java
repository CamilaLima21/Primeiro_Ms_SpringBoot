package br.com.nava.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.nava.entities.VendaEntity;
import br.com.nava.services.VendaService;

@RestController
@RequestMapping("vendas")
public class VendaController {

		
	@Autowired
	private VendaService vendaService;
	
	@GetMapping("")
	public List<VendaEntity> getAll() {
		return vendaService.getAll();		
	}
	
	@GetMapping("{id}")
	public VendaEntity getOne(@PathVariable Integer id) {
		return vendaService.getOne(id);		
	}
	
	@PostMapping("")
	public VendaEntity save(@RequestBody VendaEntity venda) {
		return vendaService.save(venda);
	}
	
	@PatchMapping("{id}")
	public VendaEntity update(@PathVariable Integer id, @RequestBody VendaEntity venda) {
		return vendaService.update(id, venda);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		vendaService.delete(id);
	}	
	
}
