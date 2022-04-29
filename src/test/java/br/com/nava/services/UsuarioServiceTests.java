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

import br.com.nava.dtos.UsuarioDTO;
import br.com.nava.entities.UsuarioEntity;
import br.com.nava.repositories.UsuarioRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UsuarioServiceTests {

	@Autowired
	private UsuarioService usuarioService;
	@MockBean
	private UsuarioRepository usuarioRepository;
	
	@Test
	void getAllTest() {
		
		List<UsuarioEntity> listaMockada = new ArrayList<UsuarioEntity>();
		
		UsuarioEntity usuarioEntidade = createValidUsuario();
		listaMockada.add(usuarioEntidade);
	
		when(usuarioRepository.findAll()).thenReturn(listaMockada);
		
		List<UsuarioDTO> retorno = usuarioService.getAll();
		
		isUsuarioValid(retorno.get(0), listaMockada.get(0));				
	}

	@Test
	void getOneWhenFoundObjectTest() {
		
		UsuarioEntity usuarioEntidade = new UsuarioEntity();
		usuarioEntidade.setId(1);
		usuarioEntidade.setNome("Camila Marques");
		usuarioEntidade.setEmail("cml@gmail.com");
		
		Optional<UsuarioEntity> optional = Optional.of(usuarioEntidade);
		
		when(usuarioRepository.findById(1)).thenReturn(optional);
	
		UsuarioDTO obj = usuarioService.getOne(1);
	
		isUsuarioValid(obj, usuarioEntidade);
	}
	

	@Test
	void getOneWhenNotFoundObjectTest() {
		
		Optional<UsuarioEntity> optional = Optional.empty();
		when(usuarioRepository.findById(1)).thenReturn(optional);
	
		UsuarioDTO obj = usuarioService.getOne(1);
	
		UsuarioEntity usuarioEntidade = new UsuarioEntity();
	
		isUsuarioValid(obj, usuarioEntidade);
	}
	
	@Test
	void saveTest() {
	
		UsuarioEntity usuarioEntidade = createValidUsuario();
		when(usuarioRepository.save(usuarioEntidade)).thenReturn(usuarioEntidade);
		
		UsuarioDTO usuarioSalvo = usuarioService.save(usuarioEntidade);
	
		isUsuarioValid(usuarioSalvo, usuarioEntidade);		
	}
	
	@Test
	void updateWhenFoundObj() {
	
		UsuarioEntity usuarioEntidade = createValidUsuario();
		Optional<UsuarioEntity> optional = Optional.of(usuarioEntidade);
	
		when(usuarioRepository.findById(usuarioEntidade.getId())).thenReturn(optional);
		when(usuarioRepository.save(usuarioEntidade)).thenReturn(usuarioEntidade);
	
		UsuarioDTO usuarioAlterado = usuarioService.update(usuarioEntidade.getId(), usuarioEntidade);
		
		isUsuarioValid(usuarioAlterado, usuarioEntidade);
	}
	
	@Test
	void updateWhenNotFoundObj() {
		
		Optional<UsuarioEntity> optional = Optional.empty();
		UsuarioEntity usuarioEntidade = createValidUsuario();
	
		when(usuarioRepository.findById(1)).thenReturn(optional);
		
		UsuarioDTO usuarioAlterado = usuarioService.update(1, usuarioEntidade);
		
		isUsuarioValid(usuarioAlterado, new UsuarioEntity());
	}
	
	@Test
	void deleteTest() {
	
		assertDoesNotThrow(()-> usuarioService.delete(1));
		
		verify(usuarioRepository, times(1)).deleteById(1);
	}
	
	private void isUsuarioValid(UsuarioDTO obj, UsuarioEntity usuarioEntidade) {
		
		assertThat(obj.getId()).isEqualTo(usuarioEntidade.getId());
		assertThat(obj.getNome()).isEqualTo(usuarioEntidade.getNome());
		assertThat(obj.getEmail()).isEqualTo(usuarioEntidade.getEmail());
	}
	
	private UsuarioEntity createValidUsuario() {
		
		UsuarioEntity usuarioEntidade = new UsuarioEntity();
		
		usuarioEntidade.setId(1);
		usuarioEntidade.setNome("Camila Marques");
		usuarioEntidade.setEmail("cml@gmail.com");
		
		return usuarioEntidade;
	}
}
