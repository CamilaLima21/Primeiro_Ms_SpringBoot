package br.com.nava.dtos;


import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import br.com.nava.entities.VendaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaDTO {

	private int id;
	@NotNull(message = "Preenchimento Obrigatório")
	private Float valorTotal;


	public VendaEntity toEntity() {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(this, VendaEntity.class);
	}
}