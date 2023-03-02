package com.manuel.mercadona.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manuel.mercadona.model.Supplier;
import com.manuel.mercadona.response.SupplierResponseRest;
import com.manuel.mercadona.services.ISupplierService;

@RestController
@RequestMapping("/api")
public class SupplierRestController {

	@Autowired
	private ISupplierService supplierService;

	public SupplierRestController(ISupplierService supplierService) {
		super();
		this.supplierService = supplierService;
	}
	
	/**
	 * Buscar todos los proveedores
	 */
	@GetMapping("/suppliers")
	public ResponseEntity<SupplierResponseRest> search(){
		
		ResponseEntity<SupplierResponseRest> response = supplierService.search();
		return response;
	}
	
	
	/**
	 * Guardar Proveedor
	 */
	@PostMapping("/suppliers")
	public ResponseEntity<SupplierResponseRest> save(@RequestBody Supplier supplier) throws IOException {
				
		ResponseEntity<SupplierResponseRest> response = supplierService.save(supplier);
		return response;
	}
	
	
	/**
	 * Buscar proveedor por ID
	 */
	@GetMapping("/suppliers/{id}")
	public ResponseEntity<SupplierResponseRest> searchById(@PathVariable Long id){
		
		ResponseEntity<SupplierResponseRest> response = supplierService.searchById(id);
		return response;
	}
	
	
	/**
	 * Eliminar proveedor por ID
	 */
	@DeleteMapping("/suppliers/{id}")
	public ResponseEntity<SupplierResponseRest> deleteById(@PathVariable Long id){
		
		ResponseEntity<SupplierResponseRest> response = supplierService.deleteById(id);
		return response;
	}
	
	
	/**
	 * Actualizar proveedor
	 */
	@PutMapping("/suppliers/{id}")
	public ResponseEntity<SupplierResponseRest> update(@RequestBody Supplier supplier, @PathVariable Long id) throws IOException {
				
		ResponseEntity<SupplierResponseRest> response = supplierService.update(supplier, id);
		return response;
	}
	
}

