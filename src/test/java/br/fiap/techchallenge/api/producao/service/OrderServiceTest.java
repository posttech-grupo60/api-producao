package br.fiap.techchallenge.api.producao.service;

import static br.fiap.techchallenge.api.producao.util.TestUtils.createFakeOrder;
import static br.fiap.techchallenge.api.producao.util.TestUtils.createFakeOrderDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.fiap.techchallenge.api.producao.dto.OrderDTO;
import br.fiap.techchallenge.api.producao.model.Order;
import br.fiap.techchallenge.api.producao.repository.OrderRepository;
import br.fiap.techchallenge.api.producao.repository.ProductQuantityRepository;

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
		OrderDTO fakeOrderDTO = createFakeOrderDTO();
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
		
		//Arrange
		Order fakeOrder = createFakeOrder();
		Order fakeOrder2 = createFakeOrder();
		List<Order> orders = new ArrayList<>();
		orders.add(fakeOrder);
		orders.add(fakeOrder2);
		
		when(orderRepository.findAll()).thenReturn(orders);
		
		//Act
		List<Order> listFounded = orderRepository.findAll();
		
		//Assert
		assertThat(listFounded).isNotNull();
		assertThat(listFounded).hasSize(orders.size());
		
	}
	
	
	@Test
	void mustAllowGetOrderById() {
		Order fakeOrder = createFakeOrder();
		Long id = fakeOrder.getId();
		
		when(orderRepository.findById(any(Long.class))).thenReturn(Optional.of(fakeOrder));
		
		Optional<Order> optional = orderRepository.findById(id);
		
		assertThat(optional).isPresent().contains(fakeOrder);
		optional.ifPresent( order -> {
			assertThat(order.getId()).isEqualTo(id);
		});
		
	}
	
	@Test
	void mustAllowDeleteOrder() {
		
		doNothing().when(orderRepository).deleteById(any(Long.class));
		
		orderRepository.deleteById(new Random().nextLong());
		
		verify(orderRepository, times(1)).deleteById(any(Long.class));
		
	}
	
	@Test
	void mustAllowUpdateOrder() {
		// Arrange
	    OrderDTO orderDTO = createFakeOrderDTO();
	    Order fakeOrder = createFakeOrder();
	    
	    when(orderRepository.findById(any(Long.class))).thenReturn(Optional.of(fakeOrder));
	    when(orderRepository.save(any(Order.class))).then(i -> i.getArgument(0));

	    // Act
	    Order updatedOrder = orderService.putOrder(orderDTO.getId(), orderDTO);

	    // Assert
	    assertThat(updatedOrder).isNotNull();
	    assertThat(updatedOrder.getId()).isEqualTo(orderDTO.getId());
	    assertThat(updatedOrder.getCustomerId()).isEqualTo(orderDTO.getCustomerId());
	    
	    verify(orderRepository, times(1)).save(any(Order.class));
	}

}
