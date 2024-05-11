package br.fiap.techchallenge.api.producao.dto;

import java.math.BigDecimal;
import java.util.UUID;

import br.fiap.techchallenge.api.producao.model.ProductQuantity;
import br.fiap.techchallenge.api.producao.model.Product.CATEGORY;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.NonNull;

public class ProductDTO {
	@Column(name="uuid", length=36)
	private UUID uuid;
	
	@NotEmpty(message = "Product ID cannot be empty")
	@NonNull
	private UUID productId;
	
	@NotEmpty(message = "Product name cannot be empty")
	@NonNull
	private String name;
	
	@NotEmpty(message = "Product description cannot be empty")
	@NonNull
	private String description;
	
	@NotEmpty(message = "Product price cannot be empty")
	@NonNull
	private BigDecimal price;
	
	@NotEmpty(message = "Product category cannot be empty")
	@NonNull
	private CATEGORY CATEGORY;
	
	ProductQuantity productQuantity;
	
	public enum CATEGORY{
		LANCHE,
		ACOMPANHAMENTO,
		BEBIDA,
		SOBREMESA,
		
	}

}
