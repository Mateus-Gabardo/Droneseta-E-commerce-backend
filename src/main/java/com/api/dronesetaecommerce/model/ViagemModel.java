package com.api.dronesetaecommerce.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "viagem")
public class ViagemModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID viagemId;

	@Column(nullable = false)
	private int qtdCamisetas;

	@Column
	private Date dataHora;

	@Column
	private StatusViagem status;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "droneId")
	private DroneModel drone;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "enderecoId")
	private EnderecoModel enderecoModel;

	public UUID getViagemId() {
		return viagemId;
	}

	public void setViagemId(UUID viagemId) {
		this.viagemId = viagemId;
	}

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

	public DroneModel getDrone() {
		return drone;
	}

	public void setDrone(DroneModel drone) {
		this.drone = drone;
	}

	public EnderecoModel getEnderecoModel() {
		return enderecoModel;
	}

	public void setEnderecoModel(EnderecoModel enderecoModel) {
		this.enderecoModel = enderecoModel;
	}

}
