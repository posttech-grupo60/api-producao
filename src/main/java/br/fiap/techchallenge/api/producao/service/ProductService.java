package br.fiap.techchallenge.api.producao.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fiap.techchallenge.api.producao.model.Order;
import br.fiap.techchallenge.api.producao.model.Product;
import br.fiap.techchallenge.api.producao.model.ProductQuantity;
import br.fiap.techchallenge.api.producao.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductQuantityService productQuantityService;
	
	@Autowired
	OrderService orderService;
	
	public List<Product> getProducts(){
		System.out.println("FIND ALL");
		List<Product> products = productRepository.findAll();
		System.out.println("SIZE " + products.size());

		return products;
				
	}

	public List<ProductQuantity> getProductsByOrderId(Long orderId) {
		Optional<Order> orderOp = orderService.getById(orderId);
		
		if(orderOp.isEmpty())
			return null;
					
		return orderOp.get().getProductQuantity();
	}
}
