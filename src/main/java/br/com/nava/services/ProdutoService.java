package br.com.nava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.dtos.ProdutoDTO;
import br.com.nava.entities.ProdutoEntity;

import br.com.nava.repositories.ProdutoRepository;


@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<ProdutoDTO> getAll(){
		List<ProdutoEntity> lista = produtoRepository.findAll();
		List<ProdutoDTO> listaDTO = new ArrayList<>();
		
		for (ProdutoEntity produtoEntity : lista) {
			listaDTO.add(produtoEntity.toDTO());
		}

		return listaDTO;
	}
	
	public ProdutoDTO getOne(int id) {
		Optional<ProdutoEntity> optional = produtoRepository.findById(id);
		ProdutoEntity produto = optional.orElse( new ProdutoEntity());

		return produto.toDTO();
	}
	
	public ProdutoDTO save(ProdutoEntity produto) {
		return produtoRepository.save(produto).toDTO();		
	}
	
	public ProdutoDTO update(int id, ProdutoEntity produto) {

		Optional<ProdutoEntity> optional = produtoRepository.findById(id);

		if(optional.isPresent()) {

			ProdutoEntity produtoBD = optional.get();
			produtoBD.setNome(produto.getNome());
			produtoBD.setDescricao(produto.getDescricao());
			produtoBD.setPreco(produto.getPreco());
			
			return produtoRepository.save(produtoBD).toDTO();
		}

		else {
			return new ProdutoEntity().toDTO();
		}
	}
	
	public void delete(int id) {
		
		produtoRepository.deleteById(id);
	}
}
