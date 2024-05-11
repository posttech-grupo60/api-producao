package br.fiap.techchallenge.api.producao.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.fiap.techchallenge.api.producao.model.ProductQuantity;

@Repository
public interface ProductQuantityRepository extends JpaRepository<ProductQuantity, Long>{
	
	@Query(value = "SELECT p.id, p.order_id, p.product_id, p.quantity FROM product_quantity p "
			+ "WHERE p.product_id = ?1" , nativeQuery = true)
	public ProductQuantity getByProductId(Long productId);

}
