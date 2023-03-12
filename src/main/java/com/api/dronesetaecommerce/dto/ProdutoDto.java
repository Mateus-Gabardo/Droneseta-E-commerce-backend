package com.api.dronesetaecommerce.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.api.dronesetaecommerce.model.TamanhoCamiseta;

import lombok.Data;

@Data
public class ProdutoDto {
	@NotBlank
	@Size(max = 150, message = "A descrição inserida excede o valor permitido para o campo.")
	private String descricao;
	
	@Size(max = 150, message = "A url informada excede o valor permitido para o campo.")
	private String urlImagem;
	
	@NotNull(message = "Informar o tamanho da camiseta é obrigadtório")
	private TamanhoCamiseta tamanhoCamiseta;
	
	@Min(value = 0, message = "Valores negativos não são suportados para o campo")
	private double preco;
	
	@Digits(integer = 2, fraction = 0, message = "O valor deve ser um número inteiro com no máximo 2 dígitos")
	private Integer qtd_por_tamanho;
	
}
