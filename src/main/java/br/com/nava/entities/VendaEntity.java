package br.com.nava.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;



import br.com.nava.dtos.VendaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "VENDAS")
public class VendaEntity {
		
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "VALOR_TOTAL")
	private Float valorTotal;
	
	@ManyToOne
	@JoinColumn(name= "USUARIO_ID")
	private UsuarioEntity usuario;
	
	
	@ManyToMany(mappedBy = "vendas")
	private List<ProdutoEntity> produtos;
	
	public VendaDTO toDTO() {
		ModelMapper mapper = new ModelMapper();
		VendaDTO dto = mapper.map(this, VendaDTO.class);
		return dto;
	}
}