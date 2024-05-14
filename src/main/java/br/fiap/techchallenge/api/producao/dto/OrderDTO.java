package br.fiap.techchallenge.api.producao.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class OrderDTO {
	@NonNull
	private Long id;

	@NonNull
	private List<ProductQuantityDTO> productQuantity;
	
	@NonNull
	private Long customerId;
	
}
