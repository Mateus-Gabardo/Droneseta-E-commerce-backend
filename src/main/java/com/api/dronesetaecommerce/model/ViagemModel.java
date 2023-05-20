package com.api.dronesetaecommerce.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "viagem")
public class ViagemModel {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID viagemId;

	@Column(nullable = false)
	private int qtdCamisetas;

	@Column
	private Date dataHora;

	@Enumerated(EnumType.STRING)
	private StatusViagem status;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "droneId")
	private DroneModel drone;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "enderecoId")
	private EnderecoModel endereco;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pedidoId")
	private PedidoModel pedido;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date statusUpdate;

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

	public EnderecoModel getEndereco() {
		return endereco;
	}

	public void setEnderecoModel(EnderecoModel enderecoModel) {
		this.endereco = enderecoModel;
	}

	public Date getStatusUpdate() {
		return statusUpdate;
	}

	public void setStatusUpdate(Date statusUpdate) {
		this.statusUpdate = statusUpdate;
	}

	public PedidoModel getPedido() {
		return pedido;
	}

	public void setPedido(PedidoModel pedido) {
		this.pedido = pedido;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
