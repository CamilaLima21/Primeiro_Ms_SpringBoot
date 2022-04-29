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

import br.com.nava.dtos.ProdutoDTO;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class ProdutoControllerTests {

	@Autowired

	private MockMvc mockMvc;
	
	@Test
	void getAllTest() throws Exception {

	ResultActions response = mockMvc.perform(get("/produtos").contentType("application/json"));

	MvcResult result = response.andReturn();

	String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
	System.out.println(responseStr);
	
	ObjectMapper mapper = new ObjectMapper();

	ProdutoDTO[] lista = mapper.readValue(responseStr, ProdutoDTO[].class);

	assertThat(lista).isNotEmpty();
	}
	
	@Test
	void getOneTest() throws Exception {

	ResultActions response = mockMvc.perform(get("/produtos/1").contentType("application/json"));

	MvcResult result = response.andReturn();
	
	String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
	System.out.println(responseStr);
	ObjectMapper mapper = new ObjectMapper();

	ProdutoDTO produto = mapper.readValue(responseStr, ProdutoDTO.class);

	assertThat(produto.getId()).isEqualTo(1);
	assertThat(result.getResponse().getStatus()).isEqualTo(200);	
	}
	
	@Test
	void saveTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		ProdutoDTO produto = new ProdutoDTO();
		produto.setNome("Estante para Livros");
		produto.setDescricao("Estante com 6 prateleiras");
		produto.setPreco(499);

		ResultActions response = mockMvc.perform(
				post("/produtos").content(mapper.writeValueAsString(produto)).contentType("application/json"));
		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		System.out.println(responseStr);

		ProdutoDTO produtoSalvo = mapper.readValue(responseStr, ProdutoDTO.class);

		assertThat(produtoSalvo.getId()).isPositive();
		assertThat(produtoSalvo.getNome()).isEqualTo(produto.getNome());
		assertThat(produtoSalvo.getDescricao()).isEqualTo(produto.getDescricao());
		assertThat(produtoSalvo.getPreco()).isEqualTo(produto.getPreco());
		
		assertThat(result.getResponse().getStatus()).isEqualTo(200);		
	}
	
	@Test
	void updateTest() throws Exception {

		ObjectMapper mapper = new ObjectMapper();


		ProdutoDTO produto2 = new ProdutoDTO();
		produto2.setNome("MousePad Gamer");
		produto2.setDescricao("Mouse Pad, dimensões 100X60");
		produto2.setPreco(120);

		ResultActions response = mockMvc.perform(
				patch("/produtos/1").content(mapper.writeValueAsString(produto2)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		System.out.println(responseStr);


		ProdutoDTO produtoSalvo = mapper.readValue(responseStr, ProdutoDTO.class);

		assertThat(produtoSalvo.getId()).isPositive();
		assertThat(produtoSalvo.getNome()).isEqualTo(produto2.getNome());
		assertThat(produtoSalvo.getDescricao()).isEqualTo(produto2.getDescricao());
		assertThat(produtoSalvo.getPreco()).isEqualTo(produto2.getPreco());
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}
	
	@Test
	void deleteTest() throws Exception {


		ResultActions response = mockMvc.perform(delete("/produtos/10").contentType("application/json"));

		MvcResult result = response.andReturn();
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}
	
}
