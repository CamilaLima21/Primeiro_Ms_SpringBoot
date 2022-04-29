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

import br.com.nava.dtos.VendaDTO;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class VendaControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getAllTest() throws Exception {

		ResultActions response = mockMvc.perform(get("/vendas").contentType("application/json"));

		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		System.out.println(responseStr);

		ObjectMapper mapper = new ObjectMapper();

		VendaDTO[] lista = mapper.readValue(responseStr, VendaDTO[].class);
		assertThat(lista).isNotEmpty();
	}
	
	@Test
	void getOneTest() throws Exception {

		ResultActions response = mockMvc.perform(get("/vendas/1").contentType("application/json"));

		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		System.out.println(responseStr);
		
		ObjectMapper mapper = new ObjectMapper();

		VendaDTO venda = mapper.readValue(responseStr, VendaDTO.class);

		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}
	
	@Test
	void saveTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();


		VendaDTO venda = new VendaDTO();
		venda.setValorTotal(Float.valueOf(1200));
		System.out.println(mapper.writeValueAsString(venda));

		ResultActions response = mockMvc
				.perform(post("/vendas").content(mapper.writeValueAsString(venda)).contentType("application/json"));
	
		MvcResult result = response.andReturn();
		
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		System.out.println(responseStr);

		
		VendaDTO vendaSalva = mapper.readValue(responseStr, VendaDTO.class);
		
		assertThat(vendaSalva.getId()).isPositive();
		assertThat(vendaSalva.getValorTotal()).isEqualTo(vendaSalva.getValorTotal());
		
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}
	
	@Test
	void updateTest() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		
		VendaDTO venda2 = new VendaDTO();
		venda2.setValorTotal(Float.valueOf(1000));
		

		
		ResultActions response = mockMvc.perform(
				patch("/vendas/1").content(mapper.writeValueAsString(venda2)).contentType("application/json"));
		
		MvcResult result = response.andReturn();
		
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		System.out.println(responseStr);

		
		VendaDTO vendaSalva = mapper.readValue(responseStr, VendaDTO.class);
		
		//assertThat(vendaSalva.getId()).isPositive();
		//assertThat(vendaSalva.getValorTotal()).isEqualTo(venda2.getValorTotal());
		
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}
	
	@Test
	void deleteTest() throws Exception {


		ResultActions response = mockMvc.perform(
				delete("/vendas/1")
				.contentType("application/json"));

		MvcResult result = response.andReturn();		
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}
}
