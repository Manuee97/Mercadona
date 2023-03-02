package com.manuel.mercadona.services;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manuel.mercadona.dao.IDestinyDao;
import com.manuel.mercadona.dao.IProductDao;
import com.manuel.mercadona.dao.ISupplierDao;
import com.manuel.mercadona.model.Destiny;
import com.manuel.mercadona.model.Product;
import com.manuel.mercadona.model.Supplier;
import com.manuel.mercadona.response.ProductResponseRest;

@Service
public class ProductServiceImpl implements IProductService{

	@Autowired
	private ISupplierDao supplierDao;
	
	@Autowired
	private IDestinyDao destinyDao;
	
	@Autowired
	private IProductDao productDao;
	
	public ProductServiceImpl(ISupplierDao supplierDao, IDestinyDao destinyDao,IProductDao productDao) {
		super();
		this.supplierDao = supplierDao;
		this.destinyDao = destinyDao;
		this.productDao = productDao;
	}

	@Override
	@Transactional
	public ResponseEntity<ProductResponseRest> save(Product product, Long supplierId, Long destinyId) {
		
		ProductResponseRest response = new ProductResponseRest();
		List<Product> list = new ArrayList<>();
		
		try {
			
			// Buscar prveedor
			Optional<Supplier> supplier = supplierDao.findById(supplierId);
			
			if (supplier.isPresent()) {
				product.setSupplier(supplier.get());
			} else {
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			// Buscar destino
			Optional<Destiny> destiny = destinyDao.findById(destinyId);
			
			if (destiny.isPresent()) {
				product.setDestiny(destiny.get());
			} else {
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			// Guardar el producto
			Product productSaved = productDao.save(product);
			
			if (productSaved != null) {
				list.add(productSaved);
				response.getProductResponse().setProducts(list);
			} else {
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			e.getStackTrace();
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}


	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ProductResponseRest> searchById(Long Id) {
		
		ProductResponseRest response = new ProductResponseRest();
		List<Product> list = new ArrayList<>();
		
		try {
			
			// Buscar producto por ID
			Optional<Product> product = productDao.findById(Id);
			
			if (product.isPresent()) {
				list.add(product.get());
				response.getProductResponse().setProducts(list);
			} else {
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			e.getStackTrace();
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}
	
	
	@Override
	@Transactional
	public ResponseEntity<ProductResponseRest> deleteById(Long Id) {
		
		ProductResponseRest response = new ProductResponseRest();

		try {
			// Eliminar producto por ID
			productDao.deleteById(Id);
		} catch (Exception e) {
			e.getStackTrace();
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ProductResponseRest> search() {
		
		ProductResponseRest response = new ProductResponseRest();
		List<Product> list = new ArrayList<>();
		List<Product> listAux = new ArrayList<>();
		
		try {
			
			// Buscar productos
			listAux = (List<Product>) productDao.findAll();
			
			if (listAux.size() > 0) {
				listAux.stream().forEach((p) -> {
					list.add(p);
				});

				response.getProductResponse().setProducts(list);
			} else {
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			e.getStackTrace();
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}
	
	
	@Override
	@Transactional
	public ResponseEntity<ProductResponseRest> update(Product product, Long supplierId, Long destinyId, Long Id) {
		
		ProductResponseRest response = new ProductResponseRest();
		List<Product> list = new ArrayList<>();
		
		try {
			// Buscar prveedor
			Optional<Supplier> supplier = supplierDao.findById(supplierId);
			
			if (supplier.isPresent()) {
				product.setSupplier(supplier.get());
			} else {
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			// Buscar destino
			Optional<Destiny> destiny = destinyDao.findById(destinyId);
			
			if (destiny.isPresent()) {
				product.setDestiny(destiny.get());
			} else {
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}

			// Buscar el producto a actualizar
			Optional <Product> productSearch = productDao.findById(Id);
			
			if (productSearch.isPresent()) {
				
				// Se actualiza el producto
				productSearch.get().setProduct_name(product.getProduct_name());
				productSearch.get().setProduct_code(product.getProduct_code());
				productSearch.get().setDestiny(product.getDestiny());
				productSearch.get().setSupplier(product.getSupplier());
				
				// Se guarda en Base de Datos
				Product productToUpdate = productDao.save(productSearch.get());
				
				if (productToUpdate != null) {
					list.add(productToUpdate);
					response.getProductResponse().setProducts(list);
				} else {
					return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
				}

			} else {
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			e.getStackTrace();
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}

	@Transactional
	public ResponseEntity<ProductResponseRest> searchByEan(BigInteger eanCode) {
		
		ProductResponseRest response = new ProductResponseRest();
		List<Product> list = new ArrayList<>();
		List<Product> listAux = new ArrayList<>();
		
		String number = String.valueOf(eanCode);
		
		String supplierCode = number.substring(0, 7);
		String productCode = number.substring(7, 12);
		String destinyCode = number.substring(12);
	
		try {
			
			// Buscar producto por Ean
			listAux = productDao.findByEan(Integer.parseInt(supplierCode), Integer.parseInt(productCode), Integer.parseInt(destinyCode));
			
			if (!listAux.isEmpty()) {
				list.addAll(listAux);
				response.getProductResponse().setProducts(list);
			} else {
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			e.getStackTrace();
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}
}
