package com.api.dronesetaecommerce.model;

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
	
	@ManyToOne
	@JoinColumn(name = "pedidoId")
	private PedidoModel pedido;

	public UUID getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(UUID produtoId) {
		this.produtoId = produtoId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	public TamanhoCamiseta getTamanhoCamiseta() {
		return tamanhoCamiseta;
	}

	public void setTamanhoCamiseta(TamanhoCamiseta tamanhoCamiseta) {
		this.tamanhoCamiseta = tamanhoCamiseta;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Integer getQtd_por_tamanho() {
		return qtd_por_tamanho;
	}

	public void setQtd_por_tamanho(Integer qtd_por_tamanho) {
		this.qtd_por_tamanho = qtd_por_tamanho;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PedidoModel getPedido() {
		return pedido;
	}

	public void setPedido(PedidoModel pedido) {
		this.pedido = pedido;
	}	
	
}
