package com.manuel.mercadona.controller;

import java.io.IOException;
import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manuel.mercadona.model.Product;
import com.manuel.mercadona.response.ProductResponseRest;
import com.manuel.mercadona.services.IProductService;

@RestController
@RequestMapping("/api")
public class ProductRestController {

	@Autowired
	private IProductService productService;
	
	public ProductRestController(IProductService productService) {
		super();
		this.productService = productService;
	}
	
	/**
	 * Buscar todos los productos
	 */
	@GetMapping("/products")
	public ResponseEntity<ProductResponseRest> search(){
		
		ResponseEntity<ProductResponseRest> response = productService.search();
		return response;
	}

	/**
	 * Guardar Producto
	 */
	@PostMapping("/products")
	public ResponseEntity<ProductResponseRest> save(
			@RequestParam("product_name") String name, 
			@RequestParam("product_code") int product_code,
			@RequestParam("supplierId") Long supplierId, 
			@RequestParam("destinyId") Long destinyId) throws IOException {
		
		Product product = new Product();
		product.setProduct_name(name);
		product.setProduct_code(product_code);
		
		ResponseEntity<ProductResponseRest> response = productService.save(product, supplierId, destinyId);
		return response;
	}
	
	/**
	 * Buscar producto por ID
	 */
	@GetMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> searchById(@PathVariable Long id){
		
		ResponseEntity<ProductResponseRest> response = productService.searchById(id);
		return response;
	}
	
	/**
	 * Eliminar producto por ID
	 */
	@DeleteMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> deleteById(@PathVariable Long id){
		
		ResponseEntity<ProductResponseRest> response = productService.deleteById(id);
		return response;
	}
	
	/**
	 * Actualizar producto
	 */
	@PutMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> update(@RequestBody Product product, @RequestParam("supplierId") Long supplierId, @RequestParam("destinyId") Long destinyId, @PathVariable Long id) throws IOException {
				
		ResponseEntity<ProductResponseRest> response = productService.update(product, supplierId, destinyId, id);
		return response;
	}
	
	/**
	 * Buscar producto por EAN
	 */
	@GetMapping("/ean/{eanCode}")
	public ResponseEntity<ProductResponseRest> searchByEan(@PathVariable BigInteger eanCode){
		
		ResponseEntity<ProductResponseRest> response = productService.searchByEan(eanCode);
		return response;
	}
}
