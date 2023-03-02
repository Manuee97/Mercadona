package com.manuel.mercadona.services;

import java.math.BigInteger;

import org.springframework.http.ResponseEntity;

import com.manuel.mercadona.model.Product;
import com.manuel.mercadona.response.ProductResponseRest;

public interface IProductService {

	public ResponseEntity<ProductResponseRest> search();
	public ResponseEntity<ProductResponseRest> save(Product product, Long supplierId, Long destinyId);
	public ResponseEntity<ProductResponseRest> searchById(Long Id);
	public ResponseEntity<ProductResponseRest> deleteById(Long Id);
	public ResponseEntity<ProductResponseRest> update(Product product, Long supplierId, Long destinyId, Long Id);
	public ResponseEntity<ProductResponseRest> searchByEan(BigInteger eanCode);
	
}
