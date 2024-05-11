package br.fiap.techchallenge.api.producao.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.modelmapper.ModelMapper;

import br.fiap.techchallenge.api.producao.dto.OrderDTO;
import br.fiap.techchallenge.api.producao.dto.ProductQuantityDTO;
import br.fiap.techchallenge.api.producao.model.Order;
import br.fiap.techchallenge.api.producao.model.ProductQuantity;

public abstract class FiapUtils {
	
	private static ModelMapper mapper = new ModelMapper();
	
	public static LocalDateTime getLastDate() {
		return LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
	}
	
	public static Order convertToOrderEntity(OrderDTO ordertDTO) {
		
		return mapper.map(ordertDTO, Order.class);
		
	}
	
	public static ProductQuantity convertToProductQuantityEntity(ProductQuantityDTO pqDto) {
		
		return mapper.map(pqDto, ProductQuantity.class);
		
	}
}
