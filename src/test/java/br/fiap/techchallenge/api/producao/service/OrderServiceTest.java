package br.fiap.techchallenge.api.producao.service;

import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.fiap.techchallenge.api.producao.dto.OrderDTO;
import br.fiap.techchallenge.api.producao.model.Order;
import br.fiap.techchallenge.api.producao.repository.OrderRepository;
import br.fiap.techchallenge.api.producao.repository.ProductQuantityRepository;
import br.fiap.techchallenge.api.producao.util.TestUtils;

public class OrderServiceTest {
	
	@Mock
	private OrderRepository orderRepository;
	
	@Mock
	private ProductQuantityRepository productQuantityRepository;
	
	private OrderService orderService;
	
	AutoCloseable openMocks;
	
	@BeforeEach
	void setup() {
		openMocks = MockitoAnnotations.openMocks(this);
		orderService = new OrderService(orderRepository , new ProductQuantityService(productQuantityRepository));
	}
	
	@AfterEach
	void tearsDown() throws Exception {
		openMocks.close();
	}
	
	
	@Test
	void mustAllowInserOrder() {
		
		//Arrange
		OrderDTO fakeOrderDTO = TestUtils.createFakeOrderDTO();
		when(orderRepository.save(any(Order.class)))
		.then(i -> i.getArgument(0));
		
		//Act
		Order postOrder = orderService.postOrder(fakeOrderDTO);
		
		//Assert
		assertThat(postOrder)
		.isInstanceOf(Order.class)
		.isNotNull();
		
		assertThat(postOrder.getId()).isEqualTo(fakeOrderDTO.getId());
		assertThat(postOrder.getCustomerId()).isEqualTo(fakeOrderDTO.getCustomerId());
		
	}
	
	@Test
	void mustAllowGetAllOrders() {
		fail("Teste nao implementado");
		
	}
	
	
	@Test
	void mustAllowGetOrderById() {
		
		fail("Teste nao implementado");
	}
	
	@Test
	void mustAllowDeleteOrder() {
		fail("Teste nao implementado");
		
	}

}
