package com.api.dronesetaecommerce.dto;

import java.util.List;
import java.util.UUID;

import com.api.dronesetaecommerce.model.StatusPedido;

public class PedidoDto {
	
	private String cpf;
	private UUID enderecoId;
	private StatusPedido status;
	private List<UUID> produtoId;
	
	public UUID getEnderecoId() {
		return enderecoId;
	}
	public void setEnderecoId(UUID enderecoId) {
		this.enderecoId = enderecoId;
	}
	public StatusPedido getStatus() {
		return status;
	}
	public void setStatus(StatusPedido status) {
		this.status = status;
	}
	public List<UUID> getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(List<UUID> produtos) {
		this.produtoId = produtos;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
