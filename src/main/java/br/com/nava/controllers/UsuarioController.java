package br.com.nava.controllers;

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



import java.util.List;

import javax.validation.Valid;

import br.com.nava.dtos.UsuarioDTO;


import br.com.nava.services.UsuarioService;




@RestController
@RequestMapping("usuarios")
public class UsuarioController {
	
	
	
	//private int contador = 1;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("")
	public ResponseEntity<List<UsuarioDTO>> getAll() {
//			UsuarioEntity e = new UsuarioEntity(contador, "Camila" + contador, "cml@gmail.com");
//			listaUsuario.add(e);
//			contador++;
			//return usuarioService.getAll();
			return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<UsuarioDTO> getOne(@PathVariable Integer id) {
//		System.out.println(id);
//		for(int i=0; i < listaUsuario.size(); i++) {
//			if (listaUsuario.get(i).getId() == id) {
//				return listaUsuario.get(i);
//			}
//		}
//		
//		return null;
		//return usuarioService.getOne(id);
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getOne(id));
	}
	
	@PostMapping("")
	public ResponseEntity<UsuarioDTO> save(@Valid @RequestBody UsuarioDTO usuario) {
		
//		System.out.println(usuario);
//		
//		usuario.setId(contador);
//		listaUsuario.add(usuario);
//		contador++;
		
		//return usuarioService.save(usuario);
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuario.toEntity()));
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Integer id, @RequestBody UsuarioDTO usuario) {
//		System.out.println(id);
//		System.out.println(usuario);
//				
//		
//		for (int i = 0; i < listaUsuario.size(); i++) {
//			
//			if(	listaUsuario.get(i).getId() == id) {
//				listaUsuario.get(i).setNome(usuario.getNome());
//				listaUsuario.get(i).setEmail(usuario.getEmail());
//				
//				return listaUsuario.get(i);
//				
//			}
//		}
		
		//return usuarioService.update(id, usuario);
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.update(id, usuario.toEntity()));
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
//		for (int i = 0; i < listaUsuario.size(); i++) {
//			if (listaUsuario.get(i).getId() == id) {
//				listaUsuario.remove(i);
//			}
//		}	
		usuarioService.delete(id);
	}
}
