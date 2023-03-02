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

import com.manuel.mercadona.model.Destiny;
import com.manuel.mercadona.response.DestinyResponseRest;
import com.manuel.mercadona.services.IDestinyService;

@RestController
@RequestMapping("/api")
public class DestinyRestController {

	@Autowired
	private IDestinyService destinyService;

	public DestinyRestController(IDestinyService destinyService) {
		super();
		this.destinyService = destinyService;
	}

	/**
	 * Buscar todos los destinos
	 */
	@GetMapping("/destinations")
	public ResponseEntity<DestinyResponseRest> search() {

		ResponseEntity<DestinyResponseRest> response = destinyService.search();
		return response;
	}

	/**
	 * Guardar Destino
	 */
	@PostMapping("/destinations")
	public ResponseEntity<DestinyResponseRest> save(@RequestBody Destiny destiny) throws IOException {

		ResponseEntity<DestinyResponseRest> response = destinyService.save(destiny);
		return response;
	}

	/**
	 * Buscar destino por ID
	 */
	@GetMapping("/destinations/{id}")
	public ResponseEntity<DestinyResponseRest> searchById(@PathVariable Long id) {

		ResponseEntity<DestinyResponseRest> response = destinyService.searchById(id);
		return response;
	}

	/**
	 * Eliminar destino por ID
	 */
	@DeleteMapping("/destinations/{id}")
	public ResponseEntity<DestinyResponseRest> deleteById(@PathVariable Long id) {

		ResponseEntity<DestinyResponseRest> response = destinyService.deleteById(id);
		return response;
	}

	/**
	 * Actualizar destino
	 */
	@PutMapping("/destinations/{id}")
	public ResponseEntity<DestinyResponseRest> update(@RequestBody Destiny destiny, @PathVariable Long id)
			throws IOException {

		ResponseEntity<DestinyResponseRest> response = destinyService.update(destiny, id);
		return response;
	}
}
