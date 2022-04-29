package br.com.nava.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import br.com.nava.dtos.EnderecoDTO;
import br.com.nava.entities.EnderecoEntity;
import br.com.nava.repositories.EnderecoRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class EnderecoServiceTests {

	@Autowired
	private EnderecoService enderecoService;

	@MockBean
	private EnderecoRepository enderecoRepository;

	@Test
	void getAllTest() {

		List<EnderecoEntity> listaMockada = new ArrayList<EnderecoEntity>();

		EnderecoEntity enderecoEntidade = createValidEndereco();
		
		listaMockada.add(enderecoEntidade);

		when(enderecoRepository.findAll()).thenReturn(listaMockada);
		
		List<EnderecoDTO> retorno = enderecoService.getAll();
		
		isEnderecoValid(retorno.get(0), listaMockada.get(0));
	}
	
	@Test
	void getOneWhenNotFoundObjectTest() {
		
		
		Optional<EnderecoEntity> optional = Optional.empty();
		when(enderecoRepository.findById(1)).thenReturn(optional);
		
		EnderecoDTO obj = enderecoService.getOne(1);
		
		EnderecoEntity enderecoEntidade = new EnderecoEntity();
		
		isEnderecoValid(obj, enderecoEntidade);
	}
	
	@Test
	void savetest() {
	
		EnderecoEntity enderecoEntidade = createValidEndereco();
		when(enderecoRepository.save(enderecoEntidade)).thenReturn(enderecoEntidade);
		EnderecoDTO enderecoSalvo = enderecoService.save(enderecoEntidade);
		
		isEnderecoValid(enderecoSalvo, enderecoEntidade);
	}
	
	@Test
	void updateWhenFoundObj() {
	
		EnderecoEntity enderecoEntidade = createValidEndereco();
		Optional<EnderecoEntity> optional = Optional.of(enderecoEntidade);
		
		when(enderecoRepository.findById(enderecoEntidade.getId())).thenReturn(optional);
		when(enderecoRepository.save(enderecoEntidade)).thenReturn(enderecoEntidade);
		
		EnderecoDTO enderecoAlterado = enderecoService.update(enderecoEntidade.getId(), enderecoEntidade);
		
		isEnderecoValid(enderecoAlterado, enderecoEntidade);		
	}
	
	@Test
	void updateWhenNotFoundObj() {
		
		
		Optional<EnderecoEntity> optional = Optional.empty();
		EnderecoEntity enderecoEntidade = createValidEndereco();
		
		when(enderecoRepository.findById(1)).thenReturn(optional);
		
		EnderecoDTO enderecoAlterado = enderecoService.update(1, enderecoEntidade);
		
		isEnderecoValid(enderecoAlterado, new EnderecoEntity());	
	}
	
	@Test
	void deleteTest() {
		
		
		assertDoesNotThrow(()-> enderecoService.delete(1));
		
		verify(enderecoRepository, times(1)).deleteById(1);
	}	
	
	private void isEnderecoValid(EnderecoDTO obj, EnderecoEntity enderecoEntidade) {
		
		assertThat(obj.getId()).isEqualTo(enderecoEntidade.getId());
		assertThat(obj.getRua()).isEqualTo(enderecoEntidade.getRua());
		assertThat(obj.getNumero()).isEqualTo(enderecoEntidade.getNumero());
		assertThat(obj.getCep()).isEqualTo(enderecoEntidade.getCep());
		assertThat(obj.getCidade()).isEqualTo(enderecoEntidade.getCidade());
		assertThat(obj.getEstado()).isEqualTo(enderecoEntidade.getEstado());		
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
