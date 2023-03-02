package com.manuel.mercadona.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="supplier")
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supplierId", unique = true , nullable = false)
	private Long supplierId;
	
	@Column(name = "supplier_name", nullable = false)
	private String supplier_name;
	
	@Column(name = "supplier_code", nullable = false)
	private Integer supplier_code;
	
}
