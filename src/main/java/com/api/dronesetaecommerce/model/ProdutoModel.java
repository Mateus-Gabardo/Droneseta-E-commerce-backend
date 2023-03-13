package com.api.dronesetaecommerce.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "produto")
public class ProdutoModel {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID produtoId;
	
	@Column(length = 255)
	private String descricao;
	
	@Column(length = 255)
	private String urlImagem;
	
	@Enumerated(EnumType.STRING)
	private TamanhoCamiseta tamanhoCamiseta;
	
	@Column(precision = 2)
	private double preco;
	
	@Column
	private Integer qtd_por_tamanho;
	
}
