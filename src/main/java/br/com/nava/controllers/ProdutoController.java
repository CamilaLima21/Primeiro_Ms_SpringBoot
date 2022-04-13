package br.com.nava.controllers;



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

import br.com.nava.dtos.ProdutoDTO;


import br.com.nava.services.ProdutoService;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

	
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping("")
	public ResponseEntity<List<ProdutoDTO>> getAll() {
		//return produtoService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(produtoService.getAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ProdutoDTO> getOne(@PathVariable Integer id) {
		//return produtoService.getOne(id);	
		return ResponseEntity.status(HttpStatus.OK).body(produtoService.getOne(id));
	}
	
	@PostMapping("")
	public ResponseEntity<ProdutoDTO> save(@RequestBody ProdutoDTO produto) {
		//return produtoService.save(produto);
		return ResponseEntity.status(HttpStatus.OK).body(produtoService.save(produto.toEntity()));
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<ProdutoDTO> update(@PathVariable Integer id, @RequestBody ProdutoDTO produto) {
		//return produtoService.update(id, produto);
		return ResponseEntity.status(HttpStatus.OK).body(produtoService.update(id, produto.toEntity()));
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		produtoService.delete(id);
	}
}
