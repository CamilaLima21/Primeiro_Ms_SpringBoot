package br.com.nava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.dtos.EnderecoDTO;
import br.com.nava.entities.EnderecoEntity;
import br.com.nava.repositories.EnderecoRepository;

@Service
public class EnderecoService {
 
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public void mostrar() {
		System.out.println("mostrar");
	}
	
	public List<EnderecoDTO> getAll(){
		List<EnderecoEntity> lista = enderecoRepository.findAll();
		List<EnderecoDTO> listaDTO = new ArrayList<>();
		
		for (EnderecoEntity enderecoEntity : lista) {
			listaDTO.add(enderecoEntity.toDTO());
		}
		//return enderecoRepository.findAll();
		return listaDTO;
	}
	
	public EnderecoDTO getOne(int id) {
		Optional<EnderecoEntity> optional = enderecoRepository.findById(id);
		EnderecoEntity endereco = optional.orElse( new EnderecoEntity());
		return endereco.toDTO();
	}
	
	public EnderecoDTO save(EnderecoEntity endereco) {
		return enderecoRepository.save(endereco).toDTO();		
	}
	
	public EnderecoDTO update(int id, EnderecoEntity endereco) {
		// 1º passo: verificar se o registro existe no banco de dados
		Optional<EnderecoEntity> optional = enderecoRepository.findById(id);
		// se existe no banco
		if(optional.isPresent() == true) {
			// atualiza o objeto existente
			EnderecoEntity enderecoBD = optional.get();
			enderecoBD.setRua(endereco.getRua());
			enderecoBD.setNumero(endereco.getNumero());
			enderecoBD.setCep(endereco.getCep());
			enderecoBD.setCidade(endereco.getCidade());			
			enderecoBD.setEstado(endereco.getEstado());
			enderecoBD.setUsuario(endereco.getUsuario());			
			return enderecoRepository.save(enderecoBD).toDTO();
		}
		// caso contrário, retorna um objeto vazio
		else {
			return new EnderecoEntity().toDTO();
		}
	}
	
	public void delete(int id) {
		
		enderecoRepository.deleteById(id);
	}
	
	
}
