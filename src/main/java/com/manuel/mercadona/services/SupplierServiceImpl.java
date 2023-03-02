package com.manuel.mercadona.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manuel.mercadona.dao.ISupplierDao;
import com.manuel.mercadona.model.Supplier;
import com.manuel.mercadona.response.SupplierResponseRest;

@Service
public class SupplierServiceImpl implements ISupplierService{

	@Autowired
	private ISupplierDao supplierDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<SupplierResponseRest> search() {

		SupplierResponseRest response = new SupplierResponseRest();

		try {
			List<Supplier> supplier = (List<Supplier>) supplierDao.findAll();
			response.getSupplierResponse().setSupplier(supplier);
		} catch (Exception e) {
			e.getStackTrace();
			return new ResponseEntity<SupplierResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SupplierResponseRest>(response, HttpStatus.OK);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<SupplierResponseRest> searchById(Long id) {
		
		SupplierResponseRest response = new SupplierResponseRest();
		List<Supplier> list = new ArrayList<>();
		
		try {
			Optional<Supplier> supplier = supplierDao.findById(id);
			
			if(supplier.isPresent()) {
				list.add(supplier.get());
				response.getSupplierResponse().setSupplier(list);
			} else {
				return new ResponseEntity<SupplierResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.getStackTrace();
			return new ResponseEntity<SupplierResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SupplierResponseRest>(response, HttpStatus.OK);
	}
	
	
	@Override
	@Transactional
	public ResponseEntity<SupplierResponseRest> save(Supplier supplier) {
		
		SupplierResponseRest response = new SupplierResponseRest();
		List<Supplier> list = new ArrayList<>();
		
		try {
			Supplier supplierSaved = supplierDao.save(supplier);
			
			if(supplierSaved != null) {
				list.add(supplierSaved);
				response.getSupplierResponse().setSupplier(list);
			} else {
				return new ResponseEntity<SupplierResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.getStackTrace();
			return new ResponseEntity<SupplierResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SupplierResponseRest>(response, HttpStatus.OK);
	}
	
	
	@Override
	@Transactional
	public ResponseEntity<SupplierResponseRest> update(Supplier supplier, Long id) {
		
		SupplierResponseRest response = new SupplierResponseRest();
		List<Supplier> list = new ArrayList<>();
		
		try {
			Optional<Supplier> supplierSearch = supplierDao.findById(id);
			
			if(supplierSearch.isPresent()) {
				// Se actualiza el proveedor
				supplierSearch.get().setSupplier_code(supplier.getSupplier_code());
				supplierSearch.get().setSupplier_name(supplier.getSupplier_name());
				
				Supplier supplierToUpdate = supplierDao.save(supplierSearch.get());
				
				if(supplierToUpdate != null) {
					list.add(supplierToUpdate);
					response.getSupplierResponse().setSupplier(list);
				} else {
					return new ResponseEntity<SupplierResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<SupplierResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.getStackTrace();
			return new ResponseEntity<SupplierResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SupplierResponseRest>(response, HttpStatus.OK);
	}
	
	
	@Override
	@Transactional
	public ResponseEntity<SupplierResponseRest> deleteById(Long id) {
		
		SupplierResponseRest response = new SupplierResponseRest();
		
		try {
			supplierDao.deleteById(id);
		} catch (Exception e) {
			e.getStackTrace();
			return new ResponseEntity<SupplierResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SupplierResponseRest>(response, HttpStatus.OK);
	}
	
}
