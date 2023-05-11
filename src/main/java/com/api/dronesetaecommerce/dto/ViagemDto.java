package com.api.dronesetaecommerce.dto;

import java.util.Date;

import javax.validation.constraints.Size;

import com.api.dronesetaecommerce.model.StatusViagem;

public class ViagemDto {
	
	@Size(min = 0, message = "É necessário ao menos uma camiseta para realizar a viagem")
	private int qtdCamisetas;

	private Date dataHora;

	private StatusViagem status;

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
}
