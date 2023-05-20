package com.api.dronesetaecommerce.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.api.dronesetaecommerce.model.TamanhoCamiseta;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter @Setter
public class ProdutoDto {

	@JsonIgnore
	private UUID productId;

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

	@Digits(integer = 2, fraction = 0, message = "O valor deve ser um número inteiro com no máximo 2 dígitos")
	private Integer quantidade;

}
