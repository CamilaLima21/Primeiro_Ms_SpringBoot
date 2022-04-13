package br.com.nava.controllers;

import javax.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nava.dtos.EnderecoDTO;

import br.com.nava.services.EnderecoService;


@RestController
@RequestMapping("enderecos")
public class EnderecoController {
	
	
	
	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping("")
	public ResponseEntity<List<EnderecoDTO>> getAll() {
		//return enderecoService.getAll();		
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.getAll());
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<EnderecoDTO> getOne(@PathVariable Integer id) {
		//return enderecoService.getOne(id);	
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.getOne(id));
	}
	
	@PostMapping("")
	public ResponseEntity<EnderecoDTO> save(@Valid @RequestBody EnderecoDTO endereco) {
		//return enderecoService.save(endereco);
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.save(endereco.toEntity()));
	}
	
	@PatchMapping(value = "{id}")
	public ResponseEntity<EnderecoDTO> update(@PathVariable Integer id, @RequestBody EnderecoDTO endereco) {
		//return enderecoService.update(id, endereco);
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.update(id, endereco.toEntity()));
	}
	
	@DeleteMapping(value = "{id}")
	public void delete(@PathVariable Integer id) {
		enderecoService.delete(id);
	}

}
