package com.api.dronesetaecommerce.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "cliente")
public class ClienteModel {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID clienteId;
	
	@Column(nullable = false, length = 255)
	private String nome;
	
	@Column(nullable = false, length = 11, unique = true)
	private String cpf;
	
	@Column(nullable = false)
	private String senha;
	
	@Column(length = 16)
	private String cartaoCredito;
	
	@Column
	@Enumerated(EnumType.STRING)
	private TipoCliente tipoCliente;

	public ClienteModel() {
	}

	public UUID getId() {
		return clienteId;
	}

	public void setId(UUID id) {
		this.clienteId = id;
	}

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
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		this.senha = passwordEncoder.encode(senha);
	}
	
	public boolean checkSenha(String senha) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(senha, this.senha);
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
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public UUID getClienteId() {
		return clienteId;
	}

	public void setClienteId(UUID clienteId) {
		this.clienteId = clienteId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}