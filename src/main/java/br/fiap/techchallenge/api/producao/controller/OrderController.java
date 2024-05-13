package br.fiap.techchallenge.api.producao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fiap.techchallenge.api.producao.dto.OrderDTO;
import br.fiap.techchallenge.api.producao.exception.ResourceNotFoundException;
import br.fiap.techchallenge.api.producao.model.Order;
import br.fiap.techchallenge.api.producao.model.Order.KITCHEN_ORDER_STATUS;
import br.fiap.techchallenge.api.producao.service.OrderService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping
	public ResponseEntity<?> getAllOrders(){
		
		try {
			return ResponseEntity.ok(orderService.getAll());
		}catch(Exception e) {
			e.printStackTrace();
			
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping
	private ResponseEntity<Order> postOrder(@RequestBody @Valid OrderDTO orderDTO){
		Order orderCreated = null;
		try {
			orderCreated = orderService.postOrder(orderDTO);
		}catch(Exception e) {
			e.printStackTrace();
			
			return ResponseEntity.badRequest().build();
		}
		
		return new ResponseEntity<>(orderCreated, HttpStatus.CREATED);
	}
	
	@PutMapping("/{orderId}")
	private ResponseEntity<?> putOrder(@PathVariable(value = "orderId") Long orderId , @RequestBody @Valid OrderDTO orderDTO){
		Order orderCreated = null;
		try {
			orderCreated = orderService.putOrder(orderId, orderDTO);
		}catch(Exception e) {
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
		}
		
		return new ResponseEntity<>(orderCreated, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-order-status/{orderId}/{status}")
	private ResponseEntity<?> putOrderStatus(@PathVariable(value = "orderId") Long orderId , @PathVariable(value = "status") KITCHEN_ORDER_STATUS status){
		System.out.println("Update Status: " + orderId + "/" + status);
		Order orderUpdated = null;
		try {
			orderUpdated = orderService.putOrderStatus(orderId, status);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(orderUpdated);
	}
	
	@DeleteMapping("/{orderId}")
	public ResponseEntity<?> deleteOrder(@PathVariable(value = "orderId") Long orderId){
		
		try {
			orderService.deleteOrder(orderId);
		}catch (ResourceNotFoundException e) {
			return  new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.noContent().build();
	}
	
}
