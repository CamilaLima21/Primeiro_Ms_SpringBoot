package br.com.nava.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CalculadoraUtilTests {

	private CalculadoraUtil calculadoraUtil = new CalculadoraUtil();
	
	@Test
	void somarTest() {
		// primeira idéia: chamar o método que queremos testar e verificar se a resposta do método é a esperada
		
		int atual = calculadoraUtil.somar(5, 9);
		
		assertEquals(14, atual);
	}
}
