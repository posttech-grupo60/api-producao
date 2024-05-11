package br.fiap.techchallenge.api.producao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fiap.techchallenge.api.producao.service.ProductQuantityService;

@RestController
@RequestMapping("/product-quantity")
public class ProductQuantityController {
	
	@Autowired
	ProductQuantityService productQuantityService;
	
	@GetMapping
	public ResponseEntity<?> getProductsQuantity(){
		System.out.println("FIND ALL PQ");
		
		try{
			return ResponseEntity.ok(productQuantityService.getAll());
		}catch(Exception e) {
			e.printStackTrace();
			
			return ResponseEntity.badRequest().build();
		}
				
	}
	
}
