package br.com.nava.controllers;


import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nava.dtos.ProfessorDTO;

import br.com.nava.services.ProfessorService;

@RestController
@RequestMapping("professores")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;
	
	@GetMapping("")
	public ResponseEntity<List<ProfessorDTO>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(professorService.getAll());
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ProfessorDTO> getOne(@PathVariable Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(professorService.getOne(id));
	}
	
	@PostMapping("")
	public ResponseEntity<ProfessorDTO> save(@Valid @RequestBody ProfessorDTO professor) {
		return ResponseEntity.status(HttpStatus.OK).body(professorService.save(professor.toEntity()));
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<ProfessorDTO> update(@PathVariable Integer id, @RequestBody ProfessorDTO professor) {
		return ResponseEntity.status(HttpStatus.OK).body(professorService.update(id, professor.toEntity()));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity <Void> delete(@PathVariable Integer id) {
		professorService.delete(id);
		return  ResponseEntity.ok().build();
	}
	
	@GetMapping(value ="search-by-name/{name}")
	public ResponseEntity<List<ProfessorDTO>> searchByName(@PathVariable String name) {
		
		List<ProfessorDTO> lista = professorService.searchByName(name);
		
		return ResponseEntity.ok().body(lista);
	}
		
	
}
