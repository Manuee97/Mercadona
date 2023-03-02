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
@Table(name="destiny")
public class Destiny {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "destinyId", unique = true , nullable = false)
    private Long destinyId;
	
	@Column(name = "destiny_name", nullable = false)
    private String destiny_name;
    
    @Column(name = "destiny_code", nullable = false)
    private Integer destiny_code;
	
}
