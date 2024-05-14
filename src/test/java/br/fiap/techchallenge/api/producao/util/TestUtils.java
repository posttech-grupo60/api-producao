package br.fiap.techchallenge.api.producao.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.fiap.techchallenge.api.producao.dto.OrderDTO;
import br.fiap.techchallenge.api.producao.dto.ProductQuantityDTO;
import br.fiap.techchallenge.api.producao.model.Order;
import br.fiap.techchallenge.api.producao.model.Order.KITCHEN_ORDER_STATUS;
import br.fiap.techchallenge.api.producao.model.Product;
import br.fiap.techchallenge.api.producao.model.Product.CATEGORY;
import br.fiap.techchallenge.api.producao.model.ProductQuantity;

public abstract class TestUtils {
	
	public static Order createFakeOrder() {
		long orderId = new Random().nextLong();
		
		Order order = Order.builder()
				.id(orderId )
				.customerId(new Random().nextLong())
				.KITCHEN_ORDER_STATUS(KITCHEN_ORDER_STATUS.RECEIVED)
				.lastUpdate(FiapUtils.getLastDate())
				.build();
		
		ProductQuantity fakeProductQuantity = createFakeProductQuantity(order);
		ArrayList<ProductQuantity> pqList = new ArrayList<>();
		pqList.add(fakeProductQuantity);
		order.setProductQuantity((pqList));
		return order;
				
	}
	
	private static ProductQuantity createFakeProductQuantity(Order order) {
		
		ProductQuantity pq = ProductQuantity.builder()
				.id(new Random().nextLong())
				.quantity(2)
				.build();
		
		Product fakeProduct = createFakeProduct();
		
		pq.setProduct(fakeProduct);
		
		return pq;
	}
	
	private static Product createFakeProduct() {
		
		return Product.builder()
				.uuid(new Random().nextLong())
				.name("Big Mac")
				.description("A receita você conhece: 2 hamburgueres, alface ...")
				.price(new BigDecimal(3.50))
				.CATEGORY(CATEGORY.LANCHE)
				.productId(new Random().nextLong())
				.build();
	}
	
	public static OrderDTO createFakeOrderDTO() {
		ProductQuantityDTO fakeProductQuantityDTO = createFakeProductQuantityDTO();
		System.out.println(fakeProductQuantityDTO);
		List<ProductQuantityDTO> list = new ArrayList<>();
		list.add(fakeProductQuantityDTO);

		OrderDTO orderDTO = OrderDTO.builder()
		.customerId(new Random().nextLong())
		.id(new Random().nextLong())
		.productQuantity(list)
		.build();
		
		return orderDTO;
	}
	
	private static ProductQuantityDTO createFakeProductQuantityDTO() {
		Product fakeProduct = createFakeProduct();

		ProductQuantityDTO productQuantityDTO = ProductQuantityDTO.builder()
		.id(new Random().nextLong())
		.quantity(2)
		.product(fakeProduct)
		.build();
		
		return productQuantityDTO;
		
	}
	
}
