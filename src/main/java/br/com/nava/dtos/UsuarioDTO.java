package br.com.nava.dtos;



import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;



import br.com.nava.entities.UsuarioEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

	private int id;
	@NotEmpty(message = "Preenchimento Obrigatório")
	@NotNull(message = "Preenchimento Obrigatório")
	@Length(min = 3, max= 80, message= "O número de caracteres deve ser entre 3 e 80")
	@Pattern( regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]*$", message = "É válido apenas caracteres")
	private String nome;
	private String email;
	//private EnderecoEntity endereco;
	//private List<VendaEntity> vendas;
	
	public UsuarioEntity toEntity() {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(this,  UsuarioEntity.class);
	}
	
}
