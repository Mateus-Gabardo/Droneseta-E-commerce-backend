package com.api.dronesetaecommerce.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter @Setter
@Table(name = "produto")
public class ProdutoModel {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID produtoId;

	@Column(length = 255)
	private String nome;
	
	@Column(length = 255)
	private String descricao;
	
	@Column(length = 255)
	private String imagem;
	
	@Enumerated(EnumType.STRING)
	private TamanhoCamiseta tamanhoCamiseta;
	
	@Column(precision = 2)
	private double preco;
	
	@Column
	private Integer quantidade;

	@ManyToOne
	@JoinColumn(name = "pedidoId")
	private PedidoModel pedido;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
