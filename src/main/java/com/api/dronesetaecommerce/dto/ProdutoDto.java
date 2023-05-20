package com.api.dronesetaecommerce.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;

import com.api.dronesetaecommerce.model.TamanhoCamiseta;


@Data
public class ProdutoDto {
		
	@JsonIgnore
	private UUID produtoId;

	@NotBlank
	@Size(max = 150, message = "O nome inserido excede o valor permitido para o campo.")
	private String nome;

	@NotBlank
	@Size(max = 150, message = "A descrição inserida excede o valor permitido para o campo.")
	private String descricao;

	@Size(max = 150, message = "A url informada excede o valor permitido para o campo.")
	private String imagem;

	@NotNull(message = "Informar o tamanho da camiseta é obrigatório")
	private TamanhoCamiseta tamanhoCamiseta;

	@Min(value = 0, message = "Valores negativos não são suportados para o campo")
	private double preco;

	@Digits(integer = 10, fraction = 0, message = "O valor deve ser um número inteiro com no máximo 10 dígitos")
	private Integer quantidade;

  public UUID getProdutoId() {
    return produtoId;
  }
  
  public void setProdutoId(UUID produtoId) {
    this.produtoId = produtoId;
  }
  
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public TamanhoCamiseta getTamanhoCamiseta() {
		return tamanhoCamiseta;
	}

	public void setTamanhoCamiseta(TamanhoCamiseta tamanhoCamiseta) {
		this.tamanhoCamiseta = tamanhoCamiseta;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
