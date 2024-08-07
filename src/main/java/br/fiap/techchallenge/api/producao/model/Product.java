package br.fiap.techchallenge.api.producao.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
@Builder
public class Product {
	
	public Product() {}
	
	public Product(Long uuid, Long productId, String name, String description, BigDecimal price, CATEGORY CATEGORY,	ProductQuantity productQuantity) {
		super();
		this.uuid = uuid;
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.CATEGORY = CATEGORY;
		this.productQuantity = productQuantity;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="uuid")
	private Long uuid;
	
	@Column(name = "product_id",nullable = false)
	@NonNull
	private Long productId;
	
	@Column(nullable = false)
	@NotEmpty(message = "Product name cannot be empty")
	@NonNull
	private String name;
	
	@Column(nullable = false)
	@NotEmpty(message = "Product description cannot be empty")
	@NonNull
	private String description;
	
	@Column(nullable = false)
	@NonNull
	private BigDecimal price;
	
	@Column(name = "category", nullable = false)
	@NonNull
	private CATEGORY CATEGORY;
	
	@JsonIgnore
	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
	ProductQuantity productQuantity;
	
	public enum CATEGORY{
		LANCHE,
		ACOMPANHAMENTO,
		BEBIDA,
		SOBREMESA,
		
	}

}
