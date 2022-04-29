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

import br.com.nava.entities.VendaEntity;


@DataJpaTest
@ExtendWith(SpringExtension.class)
class VendaRepositoryTests {
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	void findByIdWhenFoundTest() {
		VendaEntity vendaEntidade = createValidVenda();
		
		testEntityManager.persist(vendaEntidade);
		
		Optional<VendaEntity> venda = vendaRepository.findById(vendaEntidade.getId());
		
		assertThat(venda).isNotNull();
	}
	
	@Test
	void findByIdWhenNotFoundTest() {
		
		Optional<VendaEntity> venda = vendaRepository.findById(1);
		
		assertThat(venda).isNotPresent();
	}
	
	@Test
	void findAllTest() {
		VendaEntity vendaEntidade = createValidVenda();
		
		testEntityManager.persist(vendaEntidade);
		
		List<VendaEntity> vendas = this.vendaRepository.findAll();
		
		assertThat(vendas.size()).isEqualTo(1);		
	}
	
	@Test
	void saveTest() {
		VendaEntity vendaEntidade = createValidVenda();
		
		testEntityManager.persist(vendaEntidade);
		VendaEntity vendaSalva = vendaRepository.save(vendaEntidade);
		
		assertThat(vendaSalva.getId()).isEqualTo(vendaEntidade.getId());;
		assertThat(vendaSalva.getValorTotal()).isEqualTo(vendaEntidade.getValorTotal());
	}
	
	@Test
	void deletById() {
		VendaEntity vendaEntidade = createValidVenda();
		
		VendaEntity vendaTemporaria = testEntityManager.persist(vendaEntidade);
		
		vendaRepository.deleteById(vendaTemporaria.getId());
		
		Optional<VendaEntity> deletado = vendaRepository.findById(vendaTemporaria.getId());
		assertThat(deletado).isNotPresent();
	}

	
	private VendaEntity createValidVenda() {
		VendaEntity vendaEntidade = new VendaEntity();
		
		vendaEntidade.setValorTotal(Float.valueOf(1200));
		
		return vendaEntidade;
	}
}
