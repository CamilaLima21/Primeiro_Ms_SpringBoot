package br.com.nava.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.dtos.UsuarioDTO;
import br.com.nava.entities.UsuarioEntity;
import br.com.nava.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void mostrar() {
		System.out.println("Mostrar");
	}
	
	public List<UsuarioDTO> getAll(){
		List<UsuarioEntity> lista = usuarioRepository.findAll();
		List<UsuarioDTO> listaDTO = new ArrayList<>();
		
		for(UsuarioEntity usuarioEntity : lista) {
			listaDTO.add(usuarioEntity.toDTO());
		}
		
		return listaDTO;
	}
	
//	public UsuarioEntity getOne(int id, ArrayList<UsuarioEntity> listaUsuario) {
//		int indice = findIndex(id, listaUsuario);
//		return(indice >= 0 ? listaUsuario.get(indice) : null);
//	}
	
	public UsuarioDTO getOne(int id) {
		Optional<UsuarioEntity> optional = usuarioRepository.findById(id);
		UsuarioEntity usuario = optional.orElse( new UsuarioEntity());
		return usuario.toDTO();
	}
	
	public UsuarioDTO save(UsuarioEntity usuario) {
		return usuarioRepository.save(usuario).toDTO();		
	}
	
//	public int findIndex(int id, ArrayList<UsuarioEntity> listaUsuario) {
//		
//		for (int i = 0; i < listaUsuario.size(); i++) {
//			if(listaUsuario.get(i).getId() == id) {
//				return i;
//			}
//		}
//		return -1;
//	}
	
	//variável usuario contém os dados vindo da requisição
//		public UsuarioEntity update(int id, UsuarioEntity usuario, ArrayList<UsuarioEntity> listaUsuario) {
//		
//		int indice = findIndex(id, listaUsuario);
//		if (indice >= 0) {
//			listaUsuario.get(indice).setId(usuario.getId());
//			listaUsuario.get(indice).setNome(usuario.getNome());
//			listaUsuario.get(indice).setEmail(usuario.getEmail());
//			
//				
//				return listaUsuario.get(indice);
//		 }
//		
//		return null;
//		}
		
		//variável usuario contém os dados vindo da requisição
		public UsuarioDTO update(int id, UsuarioEntity usuario) {
			// 1º passo: verificar se o registro existe no banco de dados
			Optional<UsuarioEntity> optional = usuarioRepository.findById(id);
			// se existe no banco
			if(optional.isPresent() == true) {
				// atualiza o objeto existente
				UsuarioEntity usuarioBD = optional.get();
				usuarioBD.setNome(usuario.getNome());
				usuarioBD.setEmail(usuario.getEmail());
				
				return usuarioRepository.save(usuarioBD).toDTO();
			}
			// caso contrário, retorna um objeto vazio
			else {
				return new UsuarioEntity().toDTO();
			}
		}
		
		
		public void delete(int id) {
			usuarioRepository.deleteById(id);
		}


}
