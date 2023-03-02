package com.manuel.mercadona.response;

import java.util.List;

import com.manuel.mercadona.model.Product;

import lombok.Data;

@Data
public class ProductResponse {

	List<Product> products;
	
}
