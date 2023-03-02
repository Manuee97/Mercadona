package com.manuel.mercadona.services;

import org.springframework.http.ResponseEntity;
import com.manuel.mercadona.model.Supplier;
import com.manuel.mercadona.response.SupplierResponseRest;

public interface ISupplierService {

	public ResponseEntity<SupplierResponseRest> search();
	public ResponseEntity<SupplierResponseRest> save(Supplier supplier);
	public ResponseEntity<SupplierResponseRest> searchById(Long Id);
	public ResponseEntity<SupplierResponseRest> deleteById(Long Id);
	public ResponseEntity<SupplierResponseRest> update(Supplier supplier, Long Id);
	
}
