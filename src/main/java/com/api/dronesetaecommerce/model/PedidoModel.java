package com.api.dronesetaecommerce.model;

import lombok.Getter;
import lombok.Setter;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Getter @Setter
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

	@OneToMany(mappedBy = "pedido", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<ProdutoModel> produtos;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
