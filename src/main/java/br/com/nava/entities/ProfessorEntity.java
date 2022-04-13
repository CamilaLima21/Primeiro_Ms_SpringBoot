package br.com.nava.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.com.nava.dtos.ProfessorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="PROFESSORES")

public class ProfessorEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String cpf;
	private String rua;
	private int numero;
	private String cep;
	
	public ProfessorDTO toDTO() {
		ModelMapper mapper = new ModelMapper();
		
		ProfessorDTO dto = mapper.map(this, ProfessorDTO.class);
		
		return dto;
	}
}
