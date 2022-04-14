package br.com.nava.controllers;


import java.util.List;

import javax.validation.Valid;

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

import br.com.nava.dtos.ProfessorDTO;

import br.com.nava.services.ProfessorService;

@RestController
@RequestMapping("professores")
public class ProfessorController {
	
	
	
	//private int cont = 0;
	
	//ProfessorService professorService = new ProfessorService();
	@Autowired
	private ProfessorService professorService;
	
	@GetMapping("")
	public ResponseEntity<List<ProfessorDTO>> getAll(){
//		professorService.mostrar();
//		return listaProfessor;		
		return ResponseEntity.status(HttpStatus.OK).body(professorService.getAll());
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ProfessorDTO> getOne(@PathVariable Integer id) {
		//System.out.println(id);
//		for(int i=0; i < listaProfessor.size(); i++) {
//			if (listaProfessor.get(i).getId() == id) {
//				return listaProfessor.get(i);
//			}
//		}
		
		//int indice = findIndex(id);
//		if(indice >= 0) {
//			return listaProfessor.get(indice);
//		}
//		return null;
		
		//return(indice >= 0 ? listaProfessor.get(indice) : null);
		//return professorService.getOne(id, listaProfessor);
		//return professorService.getOne(id);
		return ResponseEntity.status(HttpStatus.OK).body(professorService.getOne(id));
	}
	
	@PostMapping("")
	public ResponseEntity<ProfessorDTO> save(@Valid @RequestBody ProfessorDTO professor) {
		//System.out.println(professor);
//		
//		professor.setId(cont);
//		listaProfessor.add(professor);
//		cont++;
		
		//return professorService.save(professor.toEntity());
		return ResponseEntity.status(HttpStatus.OK).body(professorService.save(professor.toEntity()));
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<ProfessorDTO> update(@PathVariable Integer id, @RequestBody ProfessorDTO professor) {
//		System.out.println(id);
//		System.out.println(professor);
//		
//		for (int i = 0; i < listaProfessor.size(); i++) {
//			if (listaProfessor.get(i).getId() == id) {
//				listaProfessor.get(i).setNome(professor.getNome());
//				listaProfessor.get(i).setCpf(professor.getCpf());
//				listaProfessor.get(i).setRua(professor.getRua());
//				listaProfessor.get(i).setNumero(professor.getNumero());
//				listaProfessor.get(i).setCep(professor.getCep());
//				
//				return listaProfessor.get(i);
//				
//			}
//		}
//		int indice = findIndex(id);
//		if (indice >= 0) {
//			 	listaProfessor.get(indice).setNome(professor.getNome());
//			 	listaProfessor.get(indice).setCpf(professor.getCpf());
//				listaProfessor.get(indice).setRua(professor.getRua());
//				listaProfessor.get(indice).setNumero(professor.getNumero());
//				listaProfessor.get(indice).setCep(professor.getCep());
//				
//				return listaProfessor.get(indice);
//		 }
//		
//		return null;
		
		//return professorService.update(id, professor.toEntity());
		return ResponseEntity.status(HttpStatus.OK).body(professorService.update(id, professor.toEntity()));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity <Void> delete(@PathVariable Integer id) {
//		for (int i = 0; i < listaProfessor.size(); i++) {
//			if (listaProfessor.get(i).getId() == id) {
//				listaProfessor.remove(i);
//			}
//		}	
		
		//int indice = findIndex(id);
		//if(indice >= 0 ) listaProfessor.remove(indice);
//		professorService.delete(id, listaProfessor);
		return  ResponseEntity.ok().build();
	}
	
	
	
}
