package com.manuel.mercadona.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.manuel.mercadona.model.Product;

@Repository
public interface IProductDao extends JpaRepository<Product, Long>{

	@Transactional
	@Query(value=" SELECT * FROM Product p "
			+ " INNER JOIN supplier s "
			+ " INNER JOIN destiny d "
			+ " WHERE s.supplier_code = :supplierCode and p.product_code = :productCode and d.destinyId = :destinyCode", nativeQuery = true)
	public List<Product> findByEan(@Param("supplierCode") Integer supplierCode, @Param("productCode") Integer productCode, @Param("destinyCode") Integer destinyCode);	

}
