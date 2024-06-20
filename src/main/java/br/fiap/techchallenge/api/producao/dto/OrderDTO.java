package br.fiap.techchallenge.api.producao.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDTO {
	@NonNull
	private Long id;

	@NonNull
	private List<ProductQuantityDTO> productQuantity;
	
	@NonNull
	private Long customerId;
	
}
