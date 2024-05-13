package br.fiap.techchallenge.api.producao.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.fiap.techchallenge.api.producao.model.Order;
import br.fiap.techchallenge.api.producao.util.TestUtils;

public class OrderRepositoryTest {
		
	@Mock
	private OrderRepository orderRepository;
	
	AutoCloseable openMocks;
	
	private static Logger logger = LogManager.getLogger(OrderRepositoryTest.class);
	
	@BeforeEach
	private void setup() {
		openMocks = MockitoAnnotations.openMocks(this);
	}
	
	@AfterEach
	private void teardDown() throws Exception {
		openMocks.close();
	}
	
	@Test
	void mustAllowInserOrder() {
		
		//Arrange
		Order fakeOrder = TestUtils.createFakeOrder();
		
		logger.info(fakeOrder);
		
		when(orderRepository.save(any(Order.class))).thenReturn(fakeOrder);
		
		//Act / Assert				
		assertThat(orderRepository.save(fakeOrder))
		.isNotNull()
		.isEqualTo(fakeOrder);
		
		verify(orderRepository , times(1)).save(any(Order.class));
		
	}
	
	@Test
	void mustAllowGetAllOrders() {
		
		//Arrange
		ArrayList<Order> oList = new ArrayList<>();
		Order fakeOrder = TestUtils.createFakeOrder();
		Order fakeOrder2 = TestUtils.createFakeOrder();
		oList.add(fakeOrder);
		oList.add(fakeOrder2);
		
		when(orderRepository.findAll()).thenReturn(oList);
		
		assertThat(orderRepository.findAll())
		.hasSize(2)
		.containsExactlyInAnyOrder(fakeOrder,fakeOrder2);
		
		verify(orderRepository , times(1)).findAll();
		
	}
	
	
	@Test
	void mustAllowGetOrderById() {
		
		//Arrange
		Order fakeOrder = TestUtils.createFakeOrder();
		when(orderRepository.findById(any(Long.class))).thenReturn(Optional.of(fakeOrder));

		//Act
		Optional<Order> optionalOrder = orderRepository.findById(fakeOrder.getId());
		
		//Assert
		optionalOrder.ifPresent( orderReceived -> {
			assertThat(orderReceived.getId()).isEqualTo(fakeOrder.getId());
		});
		
		verify(orderRepository , times(1)).findById(any(Long.class));
		
	}
	
	@Test
	void mustAllowDeleteOrder() {
		
		//Arrange
		long id = new Random().nextLong();
		doNothing().when(orderRepository).deleteById(any(Long.class));
		
		//Act
		orderRepository.deleteById(id);
		
		verify(orderRepository , times(1)).deleteById(any(Long.class));
		
	}

}
