package br.fiap.techchallenge.api.producao.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fiap.techchallenge.api.producao.dto.OrderDTO;
import br.fiap.techchallenge.api.producao.model.Order;
import br.fiap.techchallenge.api.producao.model.Order.KITCHEN_ORDER_STATUS;
import br.fiap.techchallenge.api.producao.model.ProductQuantity;
import br.fiap.techchallenge.api.producao.repository.OrderRepository;
import br.fiap.techchallenge.api.producao.util.FiapUtils;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	ProductQuantityService productQuantityService;
	
	private static Logger logger = LogManager.getLogger(OrderService.class);
	
	public List<Order> getAll(){
		return orderRepository.findAll();
	}
	
	public Optional<Order> getById(Long orderId){
		return orderRepository.findById(orderId);
	}
	
	public Order putOrderStatus(Long id, KITCHEN_ORDER_STATUS status) {
		Optional<Order> orderOp = orderRepository.findById(id);
		
		if (orderOp.isEmpty())
			return null;
		
		Order order = orderOp.get();
		order.setKITCHEN_ORDER_STATUS(status);
		order.setLastUpdate(LocalDateTime.now());
		
		orderRepository.save(order);
		
		return order;
	}

	public Order postOrder(OrderDTO orderDTO) {
		logger.info("POST Order " + orderDTO);
		
		List<ProductQuantity> pqEntityList = productQuantityService.getProductQuantityEntityListFromDTO(orderDTO.getProductQuantity());
		Order order = FiapUtils.convertToOrderEntity(orderDTO);
		addOrderAtributes(order,pqEntityList);
		
		return orderRepository.save(order);
	}
	
	public Order putOrder(Long orderId, OrderDTO orderDTO) {
		List<ProductQuantity> pqEntityList = productQuantityService.getProductQuantityEntityListFromDTO(orderDTO.getProductQuantity());
		Order order = FiapUtils.convertToOrderEntity(orderDTO);
		addOrderAtributes(order,pqEntityList);
		
		Order orderById = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found with id "+ order.getId()));
		logger.info("Order Status: "+ orderById.getKITCHEN_ORDER_STATUS());
		if(!orderById.getKITCHEN_ORDER_STATUS().equals(KITCHEN_ORDER_STATUS.RECEIVED) && !orderById.getKITCHEN_ORDER_STATUS().equals(KITCHEN_ORDER_STATUS.PREPARATION))
			throw new RuntimeException("O pedido não pode ser atualizado porque seu status é " +  orderById.getKITCHEN_ORDER_STATUS());
		
		return orderRepository.save(order);
	}

	private void addOrderAtributes(Order order, List<ProductQuantity> pqEntityList) {
		order.setLastUpdate(FiapUtils.getLastDate());
		order.setKITCHEN_ORDER_STATUS(KITCHEN_ORDER_STATUS.RECEIVED);
		pqEntityList.stream().forEach( pq -> {
			pq.setOrder(order);
		});
		order.setProductQuantity(pqEntityList);
		
	}
}
