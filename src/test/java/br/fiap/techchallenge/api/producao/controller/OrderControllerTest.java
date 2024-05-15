package br.fiap.techchallenge.api.producao.controller;

import static br.fiap.techchallenge.api.producao.util.TestUtils.createFakeOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.fiap.techchallenge.api.handler.GlobalExceptionHandler;
import br.fiap.techchallenge.api.producao.model.Order;
import br.fiap.techchallenge.api.producao.service.OrderService;

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

      mockMvc.perform(get("/order/{id}", id)
              .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id").value(order.getId().toString()));
          
      verify(orderService, times(1)).getById(any(Long.class));
    }
}
