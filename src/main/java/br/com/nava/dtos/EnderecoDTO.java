package br.com.nava.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import br.com.nava.entities.EnderecoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {
	
	private int id;
	@NotEmpty(message = "Preenchimento Obrigatório")
	@NotNull(message = "Preenchimento Obrigatório")
	@Length(min = 3, max= 255, message= "O número de caracteres deve ser entre 3 e 255")
	@Pattern( regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$/", message = "É válido apenas caracteres")
	private String rua;
	private int numero;
	@Pattern( regexp = "^[0-9]+$/", message = "É válido apenas números")
	private String cep;
	private String cidade;
	private String estado;	
	
	public EnderecoEntity toEntity() {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(this,  EnderecoEntity.class);
	}

}
