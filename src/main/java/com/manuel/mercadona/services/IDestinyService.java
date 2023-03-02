package com.manuel.mercadona.services;

import org.springframework.http.ResponseEntity;

import com.manuel.mercadona.model.Destiny;
import com.manuel.mercadona.response.DestinyResponseRest;

public interface IDestinyService {

	public ResponseEntity<DestinyResponseRest> search();
	public ResponseEntity<DestinyResponseRest> save(Destiny destiny);
	public ResponseEntity<DestinyResponseRest> searchById(Long Id);
	public ResponseEntity<DestinyResponseRest> deleteById(Long Id);
	public ResponseEntity<DestinyResponseRest> update(Destiny destiny, Long Id);
	
}
