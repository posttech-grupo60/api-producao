package br.fiap.techchallenge.api.producao.dto;

import br.fiap.techchallenge.api.producao.model.Product;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ProductQuantityDTO {
	
	private Long id;
	
	@NonNull
	private Product product;
	
	@Min(value = 1, message = "Quantity must be greater than zero!")
	private int quantity;
	
	
}
