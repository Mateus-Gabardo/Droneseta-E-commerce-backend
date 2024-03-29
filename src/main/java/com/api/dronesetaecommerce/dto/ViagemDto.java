package com.api.dronesetaecommerce.dto;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;

import com.api.dronesetaecommerce.model.StatusViagem;

public class ViagemDto {
	
	@Max(value = 10, message = "O número máximo de camiseta é 10")
	private int qtdCamisetas;

	private Date dataHora;

	private StatusViagem status;
	
	private UUID droneId;
	
	private UUID enderecoId;
	
	private UUID pedidoId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date statusUpdate;

	public int getQtdCamisetas() {
		return qtdCamisetas;
	}

	public void setQtdCamisetas(int qtdCamisetas) {
		this.qtdCamisetas = qtdCamisetas;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public StatusViagem getStatus() {
		return status;
	}

	public void setStatus(StatusViagem status) {
		this.status = status;
	}

	public UUID getDroneId() {
		return droneId;
	}

	public void setDroneId(UUID droneId) {
		this.droneId = droneId;
	}

	public UUID getEnderecoId() {
		return enderecoId;
	}

	public void setEnderecoId(UUID enderecoId) {
		this.enderecoId = enderecoId;
	}

	public Date getStatusUpdate() {
		return statusUpdate;
	}

	public void setStatusUpdate(Date statusUpdate) {
		this.statusUpdate = statusUpdate;
	}

	public UUID getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(UUID viagemId) {
		this.pedidoId = viagemId;
	}
}
