package br.com.nava.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.nava.entities.ProdutoEntity;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class ProdutoRepositoryTests {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	void findByIdWhenFoundTest() {

		ProdutoEntity produtoEntidade = createValidProduto();
		testEntityManager.persist(produtoEntidade);

		Optional<ProdutoEntity> produto = produtoRepository.findById(produtoEntidade.getId());

		assertThat(produto).isNotNull();
	}
	
	@Test
	void findByIdWhenNotFoundTest() {

		Optional<ProdutoEntity> produto = produtoRepository.findById(1);

		assertThat(produto).isNotPresent();
	}
	
	@Test
	void findAllTest() {
		ProdutoEntity produtoEntidade = createValidProduto();
	
		testEntityManager.persist(produtoEntidade);
		
		List<ProdutoEntity> produtos = this.produtoRepository.findAll();
	
		assertThat(produtos.size()).isEqualTo(1);
	}
	
	@Test
	void saveTest() {
		ProdutoEntity produtoEntidade = createValidProduto();
		
		testEntityManager.persist(produtoEntidade);
		
		ProdutoEntity produtoSalvo = produtoRepository.save(produtoEntidade);
		
		assertThat(produtoSalvo.getId()).isEqualTo(produtoEntidade.getId());;
		assertThat(produtoSalvo.getNome()).isEqualTo(produtoEntidade.getNome());
		assertThat(produtoSalvo.getDescricao()).isEqualTo(produtoEntidade.getDescricao());
		assertThat(produtoSalvo.getPreco()).isEqualTo(produtoEntidade.getPreco());		
	}
	
	@Test
	void deleteById() {
		ProdutoEntity produtoEntidade = createValidProduto();
		
		ProdutoEntity produtoTemporario = testEntityManager.persist(produtoEntidade);
		
		produtoRepository.deleteById(produtoTemporario.getId());
		
		Optional<ProdutoEntity> deletado = produtoRepository.findById(produtoTemporario.getId());
		
		assertThat(deletado).isNotPresent();
	}	
	
	private ProdutoEntity createValidProduto() {

		ProdutoEntity produtoEntidade = new ProdutoEntity();
		produtoEntidade.setNome("Cadeira Escritório");
		produtoEntidade.setDescricao("Cadeira com rodas, apoio de braço e encosto regulável.");
		produtoEntidade.setPreco(375);
		
		return produtoEntidade;
	}
}
