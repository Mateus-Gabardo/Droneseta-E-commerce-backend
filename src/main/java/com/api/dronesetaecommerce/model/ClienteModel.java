package com.api.dronesetaecommerce.model;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	private TipoCliente tipoCliente = TipoCliente.NORMAL;
	
	@OneToMany(mappedBy = "cliente")
	private Set<EnderecoModel> enderecos;

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

	public void setCartaoCredito(String cartao_credito) {
		this.cartaoCredito = cartao_credito;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipo_cliente(TipoCliente tipo_cliente) {
		this.tipoCliente = tipo_cliente;
	}

	public Set<EnderecoModel> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<EnderecoModel> enderecos) {
		this.enderecos = enderecos;
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}