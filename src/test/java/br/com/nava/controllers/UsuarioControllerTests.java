package br.com.nava.controllers;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.nava.dtos.UsuarioDTO;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class UsuarioControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getAllTest() throws Exception {

		ResultActions response = mockMvc.perform(get("/usuarios").contentType("application/json"));

		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		System.out.println(responseStr);

		ObjectMapper mapper = new ObjectMapper();

		UsuarioDTO[] lista = mapper.readValue(responseStr, UsuarioDTO[].class);

		assertThat(lista).isNotEmpty();
	}

	@Test
	void getOneTest() throws Exception {

		ResultActions response = mockMvc.perform(get("/usuarios/1").contentType("application/json"));

		MvcResult result = response.andReturn();
	
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		System.out.println(responseStr);
		ObjectMapper mapper = new ObjectMapper();

		UsuarioDTO usuario = mapper.readValue(responseStr, UsuarioDTO.class);
	
		assertThat(usuario.getId()).isEqualTo(1);
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}

	@Test
	void saveTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();


		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setNome("José Lima");
		usuario.setEmail("jose.lima@gmail.com");

		ResultActions response = mockMvc
				.perform(post("/usuarios").content(mapper.writeValueAsString(usuario)).contentType("application/json"));
	
		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		System.out.println(responseStr);


		UsuarioDTO usuarioSalvo = mapper.readValue(responseStr, UsuarioDTO.class);

		assertThat(usuarioSalvo.getId()).isPositive();
		assertThat(usuarioSalvo.getNome()).isEqualTo(usuarioSalvo.getNome());
		assertThat(usuarioSalvo.getEmail()).isEqualTo(usuarioSalvo.getEmail());

		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}

	@Test
	void updateTest() throws Exception {

		ObjectMapper mapper = new ObjectMapper();


		UsuarioDTO usuario2 = new UsuarioDTO();
		usuario2.setNome("Roberto Silva");
		usuario2.setEmail("roberto.silva@gmail.com");


		ResultActions response = mockMvc.perform(
				patch("/usuarios/1").content(mapper.writeValueAsString(usuario2)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		System.out.println(responseStr);


		UsuarioDTO usuarioSalvo = mapper.readValue(responseStr, UsuarioDTO.class);
		assertThat(usuarioSalvo.getId()).isPositive();
		assertThat(usuarioSalvo.getNome()).isEqualTo(usuario2.getNome());
		assertThat(usuarioSalvo.getEmail()).isEqualTo(usuario2.getEmail());

		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}
	
	@Test
	void deleteTest() throws Exception {


		ResultActions response = mockMvc.perform(
				delete("/usuarios/2")
				.contentType("application/json"));

		MvcResult result = response.andReturn();		
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}
}
