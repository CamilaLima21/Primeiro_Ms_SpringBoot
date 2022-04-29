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

import br.com.nava.dtos.EnderecoDTO;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class EnderecoControllerTests {

	@Autowired

	private MockMvc mockMvc;

	@Test
	void getAllTest() throws Exception {

		ResultActions response = mockMvc.perform(get("/enderecos").contentType("application/json"));

		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		System.out.println(responseStr);

		ObjectMapper mapper = new ObjectMapper();

		EnderecoDTO[] lista = mapper.readValue(responseStr, EnderecoDTO[].class);

		assertThat(lista).isNotEmpty();
	}
	
	@Test
	void getOneTest() throws Exception {
		

	ResultActions response = mockMvc.perform(get("/enderecos/1").contentType("application/json"));

	MvcResult result = response.andReturn();

	String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
	System.out.println(responseStr);
	
	ObjectMapper mapper = new ObjectMapper();

	EnderecoDTO endereco = mapper.readValue(responseStr, EnderecoDTO.class);

	assertThat(endereco.getId()).isEqualTo(1);
	assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}

	@Test
	void saveTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();


		EnderecoDTO endereco = new EnderecoDTO();
		endereco.setRua("Rua Cinco");
		endereco.setNumero(555);
		endereco.setCep("55555-555");
		endereco.setCidade("Salvador");
		endereco.setEstado("BA");


		ResultActions response = mockMvc.perform(
				post("/enderecos").content(mapper.writeValueAsString(endereco)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		System.out.println(responseStr);

		EnderecoDTO enderecoSalvo = mapper.readValue(responseStr, EnderecoDTO.class);

		assertThat(enderecoSalvo.getId()).isPositive();
		assertThat(enderecoSalvo.getRua()).isEqualTo(endereco.getRua());
		assertThat(enderecoSalvo.getNumero()).isEqualTo(endereco.getNumero());
		assertThat(enderecoSalvo.getCep()).isEqualTo(endereco.getCep());
		assertThat(enderecoSalvo.getCidade()).isEqualTo(endereco.getCidade());
		assertThat(enderecoSalvo.getEstado()).isEqualTo(endereco.getEstado());

		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}

	@Test
	void updateTest() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		EnderecoDTO endereco2 = new EnderecoDTO();
		endereco2.setRua("Rua Quatro");
		endereco2.setNumero(444);
		endereco2.setCep("44444-444");
		endereco2.setCidade("Salvador");
		endereco2.setEstado("BA");


		ResultActions response = mockMvc.perform(
				patch("/enderecos/1").content(mapper.writeValueAsString(endereco2)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		System.out.println(responseStr);


		EnderecoDTO enderecoSalvo = mapper.readValue(responseStr, EnderecoDTO.class);

		assertThat(enderecoSalvo.getId()).isPositive();
		assertThat(enderecoSalvo.getRua()).isEqualTo(endereco2.getRua());
		assertThat(enderecoSalvo.getNumero()).isEqualTo(endereco2.getNumero());
		assertThat(enderecoSalvo.getCep()).isEqualTo(endereco2.getCep());
		assertThat(enderecoSalvo.getCidade()).isEqualTo(endereco2.getCidade());
		assertThat(enderecoSalvo.getEstado()).isEqualTo(endereco2.getEstado());

		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}

	@Test
	void deleteTest() throws Exception {


		ResultActions response = mockMvc.perform(delete("/enderecos/3").contentType("application/json"));

		MvcResult result = response.andReturn();
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}

}
