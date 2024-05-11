package br.fiap.techchallenge.api.producao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fiap.techchallenge.api.producao.model.Product;
import br.fiap.techchallenge.api.producao.model.ProductQuantity;
import br.fiap.techchallenge.api.producao.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/{orderId}")
	public ResponseEntity<?> getProductsByOrderId(@PathVariable(value= "orderId") Long orderId){
		try {
			List<ProductQuantity> products = productService.getProductsByOrderId(orderId);
			
			if(products == null)
				return ResponseEntity.notFound().build();
			
			return ResponseEntity.ok(products); 
		}catch(Exception e) {
			e.printStackTrace();
			
			return ResponseEntity.badRequest().build();
		}
	}
	
	
}
