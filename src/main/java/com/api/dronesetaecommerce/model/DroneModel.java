package com.api.dronesetaecommerce.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "drone")
public class DroneModel {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID droneId;

	@Column(length = 50, nullable = true)
	private String modelo;

	@Column(nullable = true)
	private int autonomia;

	@Column
	private int capacidade;

	public UUID getDroneId() {
		return droneId;
	}

	public void setDroneId(UUID droneId) {
		this.droneId = droneId;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAutonomia() {
		return autonomia;
	}

	public void setAutonomia(int autonomia) {
		this.autonomia = autonomia;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
