package br.fiap.techchallenge.api.producao.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
public class ProductQuantity {
	
	ProductQuantity(){}
	
	@Id
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id", nullable = false)
	@NonNull
	private Product product;
	
	@Column(nullable = false)
	@Min(value = 1, message = "Quantity must be greater than zero!")
	private int quantity;
	
	@JsonIgnore
	@JsonBackReference
	@ManyToOne()
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;
	
}
