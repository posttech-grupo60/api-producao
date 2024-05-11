package br.fiap.techchallenge.api.producao.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name = "kitchen_order")
public class Order {
	
	public Order() {}
	
	@Id
	@NonNull
	private Long id;

	@JsonManagedReference
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	@NonNull
	private List<ProductQuantity> productQuantity;
	
	@NonNull
	@Column(nullable = false)
	private Long customerId;
	
	@Column(name = "kitchen_order_status" , nullable = false)
	private KITCHEN_ORDER_STATUS KITCHEN_ORDER_STATUS;
	
	public enum KITCHEN_ORDER_STATUS {
		
		RECEIVED, 
		PREPARATION, 
		READY, 
		FINISHED,
		CANCELED;
		
	}
	
	@Column(nullable = false)
	private LocalDateTime lastUpdate = LocalDateTime.now(); 
	
}
