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

import br.com.nava.entities.ProfessorEntity;

// @DataJpaTest - permite manipular o banco de dados com rollback(desfazer uma operação)
@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ProfessorRepositoryTests {

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	void findByIdWhenFoundTest() {

		ProfessorEntity professorEntidade = createValidProfessor();

		// persistir a entiade no banco de dados para testar o findById, ao final dos
		// testes, esta entidade será deletada
		testEntityManager.persist(professorEntidade);

		// buscar a entidade no banco de dados para testar o findById
		// execução do findById
		Optional<ProfessorEntity> professor = professorRepository.findById(professorEntidade.getId());

		// validadnod a resosta - se oobjeto encontrado não é nulo
		assertThat(professor).isNotNull();
	}

	@Test
	void findByIdWhenNotFoundTest() {

		// buscar uma entidade que não exista no banco de dados
		Optional<ProfessorEntity> professor = professorRepository.findById(1);

		// temos que verificar se o opcional não possui valores, ou seja, qdo o
		// isPresent() possui valor falso
		assertThat(professor.isPresent()).isFalse();

	}

	@Test
	void findAllTest() {

		ProfessorEntity professorEntidade = createValidProfessor();

		// salvando temporariamente o professor no banco de dados
		testEntityManager.persist(professorEntidade);

		// execução
		List<ProfessorEntity> professores = this.professorRepository.findAll();

		// verifcar
		assertThat(professores.size()).isEqualTo(1);
	}

	@Test
	void saveTest() {

		ProfessorEntity professorEntidade = createValidProfessor();

		// salvando temporariamente o professor no banco de dados
		testEntityManager.persist(professorEntidade);

		ProfessorEntity professorSalvo = professorRepository.save(professorEntidade);

		assertThat(professorSalvo.getId()).isNotNull();
		assertThat(professorSalvo.getCep()).isEqualTo(professorEntidade.getCep());
		assertThat(professorSalvo.getNome()).isEqualTo(professorEntidade.getNome());
		assertThat(professorSalvo.getNumero()).isEqualTo(professorEntidade.getNumero());
		assertThat(professorSalvo.getRua()).isEqualTo(professorEntidade.getRua());
	}

	@Test
	void deleteById() {

		ProfessorEntity professorEntidade = createValidProfessor();

		// salvando temporariamente o professor no banco de dados
		ProfessorEntity professorTemporario = testEntityManager.persist(professorEntidade);

		// execução
		professorRepository.deleteById(professorTemporario.getId());

		// verificação
		// buscar o professor deletado e comparar a resposta
		Optional<ProfessorEntity> deletado = professorRepository.findById(professorTemporario.getId());

		assertThat(deletado.isPresent()).isFalse();
	}

	private ProfessorEntity createValidProfessor() {

		ProfessorEntity professorEntidade = new ProfessorEntity();

		professorEntidade.setCep("04567895");
		professorEntidade.setNome("Professor Teste");
		professorEntidade.setNumero(3);
		professorEntidade.setRua("Rua de Teste");
		// professorEntidade.setId(1);

		return professorEntidade;
	}

}
