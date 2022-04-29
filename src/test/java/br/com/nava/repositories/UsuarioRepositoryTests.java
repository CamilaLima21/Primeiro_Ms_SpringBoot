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

import br.com.nava.entities.UsuarioEntity;


@DataJpaTest
@ExtendWith(SpringExtension.class)
class UsuarioRepositoryTests {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	void findByIdWhenFoundTest() {

		UsuarioEntity usuarioEntidade = createValidUsuario();

		testEntityManager.persist(usuarioEntidade);
	
		Optional<UsuarioEntity> usuario = usuarioRepository.findById(usuarioEntidade.getId());
	
		assertThat(usuario).isNotNull();
	}

	@Test
	void findByIdWhenNotFoundTest() {
	
		Optional<UsuarioEntity> usuario = usuarioRepository.findById(1);
	
		assertThat(usuario).isNotPresent();
	}

	@Test
	void findAllTest() {

		UsuarioEntity usuarioEntidade = createValidUsuario();
		
		testEntityManager.persist(usuarioEntidade);
		
		List<UsuarioEntity> usuarios = this.usuarioRepository.findAll();
	
		assertThat(usuarios.size()).isEqualTo(1);
	}

	@Test
	void saveTest() {

		UsuarioEntity usuarioEntidade = createValidUsuario();
		
		testEntityManager.persist(usuarioEntidade);

		UsuarioEntity usuarioSalvo = usuarioRepository.save(usuarioEntidade);

		assertThat(usuarioSalvo.getId()).isEqualTo(usuarioEntidade.getId());;
		assertThat(usuarioSalvo.getNome()).isEqualTo(usuarioEntidade.getNome());
		assertThat(usuarioSalvo.getEmail()).isEqualTo(usuarioEntidade.getEmail());
	}

	@Test
	void deletById() {

		UsuarioEntity usuarioEntidade = createValidUsuario();
		
		UsuarioEntity usuarioTemporario = testEntityManager.persist(usuarioEntidade);
		
		usuarioRepository.deleteById(usuarioTemporario.getId());
		
		Optional<UsuarioEntity> deletado = usuarioRepository.findById(usuarioTemporario.getId());
		assertThat(deletado).isNotPresent();
	}

	private UsuarioEntity createValidUsuario() {

		UsuarioEntity usuarioEntidade = new UsuarioEntity();

		
		usuarioEntidade.setNome("Camila Marques");
		usuarioEntidade.setEmail("cml@gmail.com");

		return usuarioEntidade;
	}
}
