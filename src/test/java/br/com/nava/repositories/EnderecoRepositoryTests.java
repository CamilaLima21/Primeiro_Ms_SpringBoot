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

import br.com.nava.entities.EnderecoEntity;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class EnderecoRepositoryTests {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	void findByIdWhenFoundTest() {

		EnderecoEntity enderecoEntidade = createValidEndereco();
		testEntityManager.persist(enderecoEntidade);

		Optional<EnderecoEntity> endereco = enderecoRepository.findById(enderecoEntidade.getId());

		assertThat(endereco).isNotNull();		
	}
	
	@Test
	void findByIdWhenNotFoundTest() {

		Optional<EnderecoEntity> endereco = enderecoRepository.findById(1);

		assertThat(endereco).isNotPresent();
	}
	
	@Test
	void findAllTest() {
		EnderecoEntity enderecoEntidade = createValidEndereco();

		testEntityManager.persist(enderecoEntidade);

		List<EnderecoEntity> enderecos = this.enderecoRepository.findAll();

		assertThat(enderecos.size()).isEqualTo(1);		
	}
	
	@Test
	void saveTest() {
		
		EnderecoEntity enderecoEntidade = createValidEndereco();

		testEntityManager.persist(enderecoEntidade);
		
		EnderecoEntity enderecoSalvo = enderecoRepository.save(enderecoEntidade);
		
		assertThat(enderecoSalvo.getId()).isEqualTo(enderecoEntidade.getId());
		assertThat(enderecoSalvo.getRua()).isEqualTo(enderecoEntidade.getRua());
		assertThat(enderecoSalvo.getNumero()).isEqualTo(enderecoEntidade.getNumero());
		assertThat(enderecoSalvo.getCep()).isEqualTo(enderecoEntidade.getCep());
		assertThat(enderecoSalvo.getCidade()).isEqualTo(enderecoEntidade.getCidade());
		assertThat(enderecoSalvo.getEstado()).isEqualTo(enderecoEntidade.getEstado());		
	}
	
	@Test
	void deleteById() {
		 EnderecoEntity enderecoEntidade = createValidEndereco();

		 EnderecoEntity enderecoTemporario = testEntityManager.persist(enderecoEntidade);

		 enderecoRepository.deleteById(enderecoTemporario.getId());

		 Optional<EnderecoEntity> deletado = enderecoRepository.findById(enderecoTemporario.getId());
		 
		 assertThat(deletado).isNotPresent();
	}

	private EnderecoEntity createValidEndereco() {
		
		EnderecoEntity enderecoEntidade = new EnderecoEntity();
		enderecoEntidade.setRua("Rua Seis");
		enderecoEntidade.setNumero(666);
		enderecoEntidade.setCep("66666-666");
		enderecoEntidade.setCidade("Curitiba");
		enderecoEntidade.setEstado("PR");
		return enderecoEntidade;
	}
}
