package com.manuel.mercadona.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id", unique = true , nullable = false)
    private Long product_id;
	
	@Column(name = "product_name", nullable = false)
    private String product_name;
	
	@Column(name = "product_code", nullable = false)
    private Integer product_code;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false, referencedColumnName = "supplierId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Supplier supplier;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destiny_id", nullable = false, referencedColumnName = "destinyId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Destiny destiny;
	
}
