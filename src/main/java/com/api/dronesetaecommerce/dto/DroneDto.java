package com.api.dronesetaecommerce.dto;

import javax.validation.constraints.Digits;

public class DroneDto {
	
	private String modelo;
	
	@Digits(integer = 2, fraction = 0, message = "O valor deve ser um número inteiro com no máximo 2 dígitos que represente o tempo de voo em minutos")
	private int autonomia;
	
	@Digits(integer = 2, fraction = 0, message = "O valor deve ser um número inteiro com no máximo 2 dígitos")
	private int capacidade;

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
	
}
