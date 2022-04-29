package br.com.nava.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.entities.ProdutoEntity;
import br.com.nava.entities.UsuarioEntity;
import br.com.nava.entities.VendaEntity;
import br.com.nava.repositories.ProdutoRepository;
import br.com.nava.repositories.UsuarioRepository;
import br.com.nava.repositories.VendaRepository;

@Service
public class BDService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private VendaRepository vendaRepository;

	public void inserirVendas() {
		

		List<UsuarioEntity> usuarios = usuarioRepository.findAll();

		List<ProdutoEntity> produtos = produtoRepository.findAll();
		
		VendaEntity venda = new VendaEntity();
		venda.setValorTotal(Float.valueOf(120));		

		venda.setUsuario(usuarios.get(0));

		venda.setProdutos(produtos);
		
		VendaEntity vendaSalva = vendaRepository.save(venda);
		
		List<VendaEntity> listaVendas = new ArrayList<>();
		listaVendas.add(vendaSalva);
		

		for (int i = 0; i < produtos.size(); i++) {
			produtos.get(i).setVendas(listaVendas);

			produtoRepository.save(produtos.get(i));
		}
	}
}
