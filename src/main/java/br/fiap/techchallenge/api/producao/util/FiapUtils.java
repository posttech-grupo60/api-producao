package br.fiap.techchallenge.api.producao.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.modelmapper.ModelMapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

	public static OrderDTO converToOrderDTO(String message) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
		Gson gson = gsonBuilder.setPrettyPrinting().create();
		return gson.fromJson(message, OrderDTO.class);
	}
}
