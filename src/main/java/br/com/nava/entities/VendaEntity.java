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
	private float valorTotal;
	
	@ManyToOne
	@JoinColumn(name= "USUARIO_ID")
	private UsuarioEntity usuario;
	
	@ManyToMany(mappedBy = "vendas")
	private List<ProdutoEntity> produtos;
}