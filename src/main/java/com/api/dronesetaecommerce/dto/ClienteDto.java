package com.api.dronesetaecommerce.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.api.dronesetaecommerce.model.TipoCliente;

public class ClienteDto {	

	@NotBlank
	@Size(max = 255, message = "O nome excede o tamanho máximo permitido de caracteres")
	private String nome;
	
	@Size(min = 11, max = 11, message = "O CPF precisa ter 11 dígitos")
	private String cpf;
	
	@Size(min = 8, max = 50, message = "A senha precisa ter mais de 8 dígitos e menos de 50 dígitos")
	private String senha;
	
	@Size(min = 16, max = 16, message = "O cartão precisa ter 16 dígitos")
	private String cartaoCredito;
	
	private TipoCliente tipoCliente;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(String cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(String cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

}
