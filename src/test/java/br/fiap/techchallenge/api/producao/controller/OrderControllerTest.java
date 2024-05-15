package br.fiap.techchallenge.api.producao.controller;

import static br.fiap.techchallenge.api.producao.util.TestUtils.createFakeOrder;
import static br.fiap.techchallenge.api.producao.util.TestUtils.createFakeOrderDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.fiap.techchallenge.api.handler.GlobalExceptionHandler;
import br.fiap.techchallenge.api.producao.dto.OrderDTO;
import br.fiap.techchallenge.api.producao.model.Order;
import br.fiap.techchallenge.api.producao.model.Product.CATEGORY;
import br.fiap.techchallenge.api.producao.service.OrderService;
import br.fiap.techchallenge.api.producao.util.FiapUtils;

public class OrderControllerTest {

	private MockMvc mockMvc;

//  @RegisterExtension
//  static MockMvcListener mockMvcListener = new MockMvcListener();

	@Mock
	private OrderService orderService;

	AutoCloseable openMocks;

	@BeforeEach
	void setUp() {
		openMocks = MockitoAnnotations.openMocks(this);
		OrderController orderController = new OrderController(orderService);
		mockMvc = MockMvcBuilders.standaloneSetup(orderController).setControllerAdvice(new GlobalExceptionHandler())
				.addFilter((request, response, chain) -> {
					response.setCharacterEncoding("UTF-8");
					chain.doFilter(request, response);
				}, "/*").build();
	}

	@AfterEach
	void tearDown() throws Exception {
		openMocks.close();
	}

	@Test
	void mustAllowGetOrder() throws Exception {
		Order order = createFakeOrder();

		Long id = order.getId();
		when(orderService.getById(any(Long.class))).thenReturn(Optional.of(order));

		mockMvc.perform(get("/order/{orderId}", id).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(order.getId().toString()));

		verify(orderService, times(1)).getById(any(Long.class));
	}
	
	@Test
    void mustAllowGetAllOrders() throws Exception {
      Order fakeOrder = createFakeOrder();
      Order fakeOrder2 = createFakeOrder();
      List<Order> list = new ArrayList<>();
      list.add(fakeOrder);
      list.add(fakeOrder2);
      
      when(orderService.getAll()).thenReturn(list);
      
      mockMvc.perform(get("/order")
              .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.[0].id").value(fakeOrder.getId().toString()));

       verify(orderService, times(1)).getAll();
    }

	@Test
	void mustAllowUpdateOrder() throws Exception {
		OrderDTO fakeOrderDTO = createFakeOrderDTO();
		fakeOrderDTO.getProductQuantity().get(0).getProduct().setCATEGORY(CATEGORY.LANCHE);
		Long id = fakeOrderDTO.getId();

		when(orderService.putOrder(any(Long.class), any(OrderDTO.class))).thenAnswer(i -> { 
			OrderDTO orderDTO = i.getArgument(1);
			return FiapUtils.convertToOrderEntity(orderDTO);
		});

		mockMvc.perform(
				put("/order/{id}", id).contentType(MediaType.APPLICATION_JSON).content(asJsonString(fakeOrderDTO)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(String.valueOf(id)));
				
		verify(orderService, times(1)).putOrder(any(Long.class), any(OrderDTO.class));
	}
	
	@Test
    void devePermitirApagarMensagem() throws Exception {
      var id = new Random().nextLong();
      
      doNothing().when(orderService).deleteOrder(any(Long.class));

      mockMvc.perform(delete("/order/{orderId}", id))
      .andExpect(status().isNoContent());
      
      verify(orderService, times(1)).deleteOrder(any(Long.class));
    }

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
