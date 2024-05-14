package br.fiap.techchallenge.api.producao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fiap.techchallenge.api.producao.dto.ProductQuantityDTO;
import br.fiap.techchallenge.api.producao.model.ProductQuantity;
import br.fiap.techchallenge.api.producao.repository.ProductQuantityRepository;
import br.fiap.techchallenge.api.producao.util.FiapUtils;

@Service
public class ProductQuantityService {
	
	@Autowired
	ProductQuantityRepository productQuantityRepository;
	
	public ProductQuantityService() {};
	
	public ProductQuantityService(ProductQuantityRepository productQuantityRepository) {
		this.productQuantityRepository = productQuantityRepository;
	};
	
	public ProductQuantity getProductQuantityByProductId(Long long1) {
		System.out.println("GET PQ by ID:" + long1);
		return productQuantityRepository.getByProductId(long1);
	}
	
	public List<ProductQuantity> getAll(){
		return productQuantityRepository.findAll();
	}
	
	public ProductQuantity post(ProductQuantity pqEntity) {
		return productQuantityRepository.save(pqEntity);
	}

	public List<ProductQuantity> post(List<ProductQuantity> productQuantityEntityList) {
		List<ProductQuantity> pqList = new ArrayList<>();
		productQuantityEntityList.stream().forEach(pq -> {
			this.post(productQuantityEntityList);
		});
		
		return pqList;
	}

	public List<ProductQuantity> getProductQuantityEntityListFromDTO(List<ProductQuantityDTO> productQuantity) {
		List<ProductQuantity> pqList = new ArrayList<>(); 
		productQuantity.stream().forEach(pq -> {
			ProductQuantity productQuantityEntity = FiapUtils.convertToProductQuantityEntity(pq);
			pqList.add(productQuantityEntity);
		});
		
		return pqList;
		
	}
}
