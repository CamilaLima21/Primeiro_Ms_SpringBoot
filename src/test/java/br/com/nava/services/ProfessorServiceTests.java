package br.com.nava.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;



import br.com.nava.dtos.ProfessorDTO;
import br.com.nava.entities.ProfessorEntity;
import br.com.nava.repositories.ProfessorRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProfessorServiceTests {

	@Autowired
	private ProfessorService professorService;

	// a anotação @MockBean serve para sinalizar que iremos
	// "MOCKAR(SIMULAR) a camada repository"
	@MockBean
	private ProfessorRepository professorRepository;

	@Test
	void getAllTest() {

		// vamos criar uma lista de entidade de professor com o objeto
		// de retornar a mesma quando o professorRepository.findAll()
		// for acionado

		List<ProfessorEntity> listaMockada = new ArrayList<ProfessorEntity>();

//		ProfessorEntity professorEntidade = new ProfessorEntity();
//		professorEntidade.setCep("04567895");
//		professorEntidade.setNome("Professor Teste");
//		professorEntidade.setNumero(3);
//		professorEntidade.setRua("Rua de Teste");
//		professorEntidade.setId(1);

		ProfessorEntity professorEntidade = createValidProfessor();

		listaMockada.add(professorEntidade);

		// quando o repository for acionado, retorno a lista Mockada
		when(professorRepository.findAll()).thenReturn(listaMockada);

		List<ProfessorDTO> retorno = professorService.getAll();

//		assertThat(listaMockada.get(0).getCep()).isEqualTo(retorno.get(0).getCep());
//		assertThat(listaMockada.get(0).getNome()).isEqualTo(retorno.get(0).getNome());
//		assertThat(listaMockada.get(0).getNumero()).isEqualTo(retorno.get(0).getNumero());
//		assertThat(listaMockada.get(0).getRua()).isEqualTo(retorno.get(0).getRua());
//		assertThat(listaMockada.get(0).getId()).isEqualTo(retorno.get(0).getId());
		
		// validar a resposta
		isProfessorValid(retorno.get(0), listaMockada.get(0));

	}

	// quando o objeto é achado no banco de dados
	@Test
	void getOneWhenFoundObjectTest() {

		ProfessorEntity professorEntidade = new ProfessorEntity();
		professorEntidade.setCep("04567895");
		professorEntidade.setNome("Professor Teste");
		professorEntidade.setNumero(3);
		professorEntidade.setRua("Rua de Teste");
		professorEntidade.setId(1);

		Optional<ProfessorEntity> optional = Optional.of(professorEntidade);

		when(professorRepository.findById(1)).thenReturn(optional);

		// execução
		ProfessorDTO obj = professorService.getOne(1);

		// validação
//
//		assertThat(obj.getCep()).isEqualTo(professorEntidade.getCep());
//		assertThat(obj.getNome()).isEqualTo(professorEntidade.getNome());
//		assertThat(obj.getNumero()).isEqualTo(professorEntidade.getNumero());
//		assertThat(obj.getRua()).isEqualTo(professorEntidade.getRua());
//		assertThat(obj.getId()).isEqualTo(professorEntidade.getId());
		
		// validar a resposta
		isProfessorValid(obj, professorEntidade);
	}

	// quando o objeto NÃO é achado no banco de dados
	@Test
	void getOneWhenNotFoundObjectTest() {

		// Optional.empty() -> simulando o caso de NÃO achar o registro no banco de
		// dados
		Optional<ProfessorEntity> optional = Optional.empty();

		when(professorRepository.findById(1)).thenReturn(optional);

		// execução
		ProfessorDTO obj = professorService.getOne(1);

		// objeto com valores "padrão"
		ProfessorEntity professorEntidade = new ProfessorEntity();

		// validação

//		assertThat(obj.getCep()).isEqualTo(professorEntidade.getCep());
//		assertThat(obj.getNome()).isEqualTo(professorEntidade.getNome());
//		assertThat(obj.getNumero()).isEqualTo(professorEntidade.getNumero());
//		assertThat(obj.getRua()).isEqualTo(professorEntidade.getRua());
//		assertThat(obj.getId()).isEqualTo(professorEntidade.getId());
		
		// validar a resposta
		isProfessorValid(obj, professorEntidade);
	}
	
	@Test
	void saveTest() {
		
		// 1- cenário
		
		// Objeto com dados válidos do professor
		ProfessorEntity professorEntidade = createValidProfessor();
		
		// quando o professorRepository
		when(professorRepository.save(professorEntidade)).thenReturn(professorEntidade);
		
		ProfessorDTO professorSalvo = professorService.save(professorEntidade);
		
		// validar a resposta
		isProfessorValid(professorSalvo, professorEntidade);
	}
	
	@Test
	void updateWhenFoundObj() {
		
		// cenário
		ProfessorEntity professorEntidade = createValidProfessor();
		Optional<ProfessorEntity> optional = Optional.of(professorEntidade);
		
		// mocks
		when(professorRepository.findById(professorEntidade.getId())).thenReturn(optional);
		when(professorRepository.save(professorEntidade)).thenReturn(professorEntidade);
		
		// execução
		ProfessorDTO professorAlterado = professorService.update(professorEntidade.getId(), professorEntidade);
		
		// validar a resposta
		isProfessorValid(professorAlterado, professorEntidade);		
	}
	
	@Test
	void updateWhenNotFoundObj() {
		
		// cenário
		Optional<ProfessorEntity> optional = Optional.empty();
		
		ProfessorEntity professorEntidade = createValidProfessor();
		
		// mocks
		when(professorRepository.findById(1)).thenReturn(optional);
		
		// execução
		/* Estamos passando como argumento o ProfessorEntidade pois em suposição ele não estará no banco de dados neste cenário*/
		ProfessorDTO professorAlterado = professorService.update(1, professorEntidade);
		
		// validar a resposta
		isProfessorValid(professorAlterado, new ProfessorEntity());	
		
	}
	
	@Test
	void deleteTest() {
		
		// execução
		// assertDoesNotThrow - espera uma lambda (método sem nome) e verifica se a lambda executa sem erro
		assertDoesNotThrow(()-> professorService.delete(1));
		// verifica se o professorRepository.deleteById foi executado uma única vez
		verify(professorRepository, times(1)).deleteById(1);
	}
	
	private void isProfessorValid(ProfessorDTO obj, ProfessorEntity professorEntidade) {
		
		assertThat(obj.getCep()).isEqualTo(professorEntidade.getCep());
		assertThat(obj.getNome()).isEqualTo(professorEntidade.getNome());
		assertThat(obj.getNumero()).isEqualTo(professorEntidade.getNumero());
		assertThat(obj.getRua()).isEqualTo(professorEntidade.getRua());
		assertThat(obj.getId()).isEqualTo(professorEntidade.getId());
		
	}

	private ProfessorEntity createValidProfessor() {

		ProfessorEntity professorEntidade = new ProfessorEntity();

		professorEntidade.setCep("04567895");
		professorEntidade.setNome("Professor Teste");
		professorEntidade.setNumero(3);
		professorEntidade.setRua("Rua de Teste");
		professorEntidade.setId(1);

		return professorEntidade;
	}

}