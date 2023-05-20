package com.api.dronesetaecommerce.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class PedidoModel {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID pedidoId;
	
	@ManyToOne
	@JoinColumn(name = "clienteId")
	private ClienteModel cliente;

	@Enumerated(EnumType.STRING)
	private StatusPedido status;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "enderecoId")
	private EnderecoModel enderecoModel;

	@ManyToMany
	@JoinTable(
			  name = "pedido_produto", 
			  joinColumns = @JoinColumn(name = "pedidoId"), 
			  inverseJoinColumns = @JoinColumn(name = "produtoId"))
	private List<ProdutoModel> produtos;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public PedidoModel() {
	}

	public UUID getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(UUID pedidoId) {
		this.pedidoId = pedidoId;
	}

	public ClienteModel getCliente() {
		return cliente;
	}

	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public EnderecoModel getEnderecoModel() {
		return enderecoModel;
	}

	public void setEnderecoModel(EnderecoModel enderecoModel) {
		this.enderecoModel = enderecoModel;
	}

	public List<ProdutoModel> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoModel> produtos) {
		this.produtos = produtos;
	}	
	
}
