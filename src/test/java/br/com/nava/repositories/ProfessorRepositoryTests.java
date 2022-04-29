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


@DataJpaTest
@ExtendWith(SpringExtension.class)
class ProfessorRepositoryTests {

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	void findByIdWhenFoundTest() {

		ProfessorEntity professorEntidade = createValidProfessor();

		testEntityManager.persist(professorEntidade);

		Optional<ProfessorEntity> professor = professorRepository.findById(professorEntidade.getId());

		assertThat(professor).isNotNull();
	}

	@Test
	void findByIdWhenNotFoundTest() {

		Optional<ProfessorEntity> professor = professorRepository.findById(1);

		assertThat(professor).isNotPresent();

	}

	@Test
	void findAllTest() {

		ProfessorEntity professorEntidade = createValidProfessor();

		testEntityManager.persist(professorEntidade);

		List<ProfessorEntity> professores = this.professorRepository.findAll();

		assertThat(professores.size()).isEqualTo(1);
	}

	@Test
	void saveTest() {

		ProfessorEntity professorEntidade = createValidProfessor();

		testEntityManager.persist(professorEntidade);

		ProfessorEntity professorSalvo = professorRepository.save(professorEntidade);

		assertThat(professorSalvo.getId()).isEqualTo(professorEntidade.getId());;
		assertThat(professorSalvo.getCep()).isEqualTo(professorEntidade.getCep());
		assertThat(professorSalvo.getNome()).isEqualTo(professorEntidade.getNome());
		assertThat(professorSalvo.getNumero()).isEqualTo(professorEntidade.getNumero());
		assertThat(professorSalvo.getRua()).isEqualTo(professorEntidade.getRua());
	}

	@Test
	void deleteById() {

		ProfessorEntity professorEntidade = createValidProfessor();

		ProfessorEntity professorTemporario = testEntityManager.persist(professorEntidade);

		professorRepository.deleteById(professorTemporario.getId());

		Optional<ProfessorEntity> deletado = professorRepository.findById(professorTemporario.getId());

		assertThat(deletado).isNotPresent();
	}

	private ProfessorEntity createValidProfessor() {

		ProfessorEntity professorEntidade = new ProfessorEntity();

		professorEntidade.setCep("04567895");
		professorEntidade.setNome("Professor Teste");
		professorEntidade.setNumero(3);
		professorEntidade.setRua("Rua de Teste");
		

		return professorEntidade;
	}

}
