package br.com.nava.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;


import br.com.nava.dtos.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;


@Data

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="USUARIOS")


public class UsuarioEntity {
	@Id	
	// dizer como será gerado a chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// nome_usuario
	@Column (name = "nome")
	private String nome;
	
	private String email;
	
	
	// SELECT * FROM USUARIOS U INNER JOIN ENDERECOS E ON U.ID = E.ID
	// JOIN COLUMN  é a entidade mais "Forte" (a que possui o campo com a chave estrangeira)
	
	
	@OneToOne
	@JoinColumn(name = "endereco_id")
	//@ToString.Exclude
	private EnderecoEntity endereco;

	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<VendaEntity> vendas;
	
	public UsuarioDTO toDTO() {
		ModelMapper mapper = new ModelMapper();
		
		UsuarioDTO dto = mapper.map(this, UsuarioDTO.class);
		
		return dto;
	}
	
}