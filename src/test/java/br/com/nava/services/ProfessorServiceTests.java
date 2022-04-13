package br.com.nava.services;



import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ProfessorServiceTests {

	@Autowired
	private ProfessorService professorService;	
	
	// a anotação MockBean serve para sinalizar que iremos "Mockar"(simular) a camada repository;
	@MockBean
	private ProfessorRepository professorRepository;
	
	@Test
	void getAllTests() {
		
		// vamos criar uma lista de entidade de professor com o objeto de retornar a mesma qdo o professorRepository.findAll() for caionado
		
		List<ProfessorEntity> listaMockada = new ArrayList<ProfessorEntity>();
		
		ProfessorEntity professorEntidade = new ProfessorEntity();
		professorEntidade.setCep("01234567");
		professorEntidade.setNome("Professor Teste");
		professorEntidade.setRua("Rua teste");
		professorEntidade.setNumero(35);
		professorEntidade.setId(5);
		
		listaMockada.add(professorEntidade);
		
		// quando o repository for acionado, retorno a lista Mockada
		when(professorRepository.findAll()).thenReturn(listaMockada);
		
		List<ProfessorDTO> retorno = professorService.getAll();
		
		assertThat(listaMockada.get(0).getCep()).isEqualTo(retorno.get(0).getCep());
		assertThat(listaMockada.get(0).getNome()).isEqualTo(retorno.get(0).getNome());
		assertThat(listaMockada.get(0).getRua()).isEqualTo(retorno.get(0).getRua());
		assertThat(listaMockada.get(0).getNumero()).isEqualTo(retorno.get(0).getNumero());
		assertThat(listaMockada.get(0).getId()).isEqualTo(retorno.get(0).getId());
		
	}
	
	// quando o objeto é encontrado no banco de dados
	@Test
	void getOneWhenFoundObjectTests() {
		
		ProfessorEntity professorEntidade = new ProfessorEntity();
		professorEntidade.setCep("01234567");
		professorEntidade.setNome("Professor Teste");
		professorEntidade.setRua("Rua teste");
		professorEntidade.setNumero(35);
		professorEntidade.setId(1);
		
		Optional<ProfessorEntity> optional = Optional.of(professorEntidade);
		
		when(professorRepository.findById(1)).thenReturn(optional);
		
		// execução
		ProfessorDTO obj = professorService.getOne(1);
		
		// validação
		assertThat(obj.getCep()).isEqualTo(professorEntidade.getCep());
		assertThat(obj.getNome()).isEqualTo(professorEntidade.getNome());
		assertThat(obj.getRua()).isEqualTo(professorEntidade.getRua());
		assertThat(obj.getNumero()).isEqualTo(professorEntidade.getNumero());
		assertThat(obj.getId()).isEqualTo(professorEntidade.getId());
		
	}
	
	// quando o objeto é NÃO encontrado no banco de dados
	@Test
	void getOneWhenNotFoundObjectTests() {
		
		// simulação do caso do registro não ser encontrado no banco de dados
		Optional<ProfessorEntity> optional = Optional.empty();
		when(professorRepository.findById(1)).thenReturn(optional);
		
		//execução
		ProfessorDTO obj = professorService.getOne(1);
		
		// objeto com valores padrão
		ProfessorEntity professorEntidade = new ProfessorEntity();
		
		// validação
		assertThat(obj.getCep()).isEqualTo(professorEntidade.getCep());
		assertThat(obj.getNome()).isEqualTo(professorEntidade.getNome());
		assertThat(obj.getRua()).isEqualTo(professorEntidade.getRua());
		assertThat(obj.getNumero()).isEqualTo(professorEntidade.getNumero());
		assertThat(obj.getId()).isEqualTo(professorEntidade.getId());
		
	}
}
