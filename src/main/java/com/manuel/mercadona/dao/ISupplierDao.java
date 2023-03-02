package com.manuel.mercadona.dao;

import org.springframework.data.repository.CrudRepository;

import com.manuel.mercadona.model.Supplier;

public interface ISupplierDao extends CrudRepository<Supplier, Long>{

}
