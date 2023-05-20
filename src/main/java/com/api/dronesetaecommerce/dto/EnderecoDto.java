package com.api.dronesetaecommerce.dto;

import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EnderecoDto {	
	
	@NotBlank
	@Size(max = 255, message = "O logradouro excede o tamanho máximo permitido de caracteres")
	private String logradouro;
	
	@Min(value = 0, message = "É obrigatório informar um número positivo")
	private Integer numero;
	
	@Size(max = 100, message = "O complemento excede o tamanho máximo permitido de caracteres")
	private String complemento;
	
	@NotBlank
	@Size(max = 100, message = "O bairro excede o tamanho máximo permitido de caracteres")
	private String bairro;
	
	@NotBlank
	@Size(max = 100, message = "A cidade excede o tamanho máximo permitido de caracteres")
	private String cidade;
	
	@NotBlank
	@Size(min = 2, max = 2, message = "O estado precisa ter 2 caracteres")
	private String estado;
	
	@NotBlank
	@Size(min = 8, max = 8, message = "O CEP precisa ter 8 caracteres")
	private String cep;
	
	private UUID clienteId;
	
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public UUID getClienteId() {
		return clienteId;
	}

	public void setClienteId(UUID clienteId) {
		this.clienteId = clienteId;
	}

}
