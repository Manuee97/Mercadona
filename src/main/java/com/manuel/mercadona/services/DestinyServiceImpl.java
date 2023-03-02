package com.manuel.mercadona.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manuel.mercadona.dao.IDestinyDao;
import com.manuel.mercadona.model.Destiny;
import com.manuel.mercadona.response.DestinyResponseRest;

@Service
public class DestinyServiceImpl implements IDestinyService{

	@Autowired
	private IDestinyDao destinyDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<DestinyResponseRest> search() {
		
		DestinyResponseRest response = new DestinyResponseRest();
		
		try {
			List<Destiny> destiny = (List<Destiny>) destinyDao.findAll();
			response.getDestinyResponse().setDestiny(destiny);
		} catch (Exception e) {
			e.getStackTrace();
			return new ResponseEntity<DestinyResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<DestinyResponseRest>(response, HttpStatus.OK);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<DestinyResponseRest> searchById(Long id) {
		
		DestinyResponseRest response = new DestinyResponseRest();
		List<Destiny> list = new ArrayList<>();
		
		try {
			Optional<Destiny> destiny = destinyDao.findById(id);
			
			if(destiny.isPresent()) {
				list.add(destiny.get());
				response.getDestinyResponse().setDestiny(list);
			} else {
				return new ResponseEntity<DestinyResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.getStackTrace();
			return new ResponseEntity<DestinyResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<DestinyResponseRest>(response, HttpStatus.OK);
	}
	
	
	@Override
	@Transactional
	public ResponseEntity<DestinyResponseRest> save(Destiny destiny) {
		
		DestinyResponseRest response = new DestinyResponseRest();
		List<Destiny> list = new ArrayList<>();
		
		try {
			Destiny destinySaved = destinyDao.save(destiny);
			
			if(destinySaved != null) {
				list.add(destinySaved);
				response.getDestinyResponse().setDestiny(list);
			} else {
				return new ResponseEntity<DestinyResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.getStackTrace();
			return new ResponseEntity<DestinyResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<DestinyResponseRest>(response, HttpStatus.OK);
	}
	
	
	@Override
	@Transactional
	public ResponseEntity<DestinyResponseRest> update(Destiny destiny, Long id) {
		
		DestinyResponseRest response = new DestinyResponseRest();
		List<Destiny> list = new ArrayList<>();
		
		try {
			Optional<Destiny> destinySearch = destinyDao.findById(id);
			
			if(destinySearch.isPresent()) {
				// Se actualiza el destino
				destinySearch.get().setDestiny_code(destiny.getDestiny_code());
				destinySearch.get().setDestiny_name(destiny.getDestiny_name());
				
				Destiny destinyToUpdate = destinyDao.save(destinySearch.get());
				
				if(destinyToUpdate != null) {
					list.add(destinyToUpdate);
					response.getDestinyResponse().setDestiny(list);
				} else {
					return new ResponseEntity<DestinyResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<DestinyResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.getStackTrace();
			return new ResponseEntity<DestinyResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<DestinyResponseRest>(response, HttpStatus.OK);
	}
	
	
	@Override
	@Transactional
	public ResponseEntity<DestinyResponseRest> deleteById(Long id) {
		
		DestinyResponseRest response = new DestinyResponseRest();
		
		try {
			destinyDao.deleteById(id);
		} catch (Exception e) {
			e.getStackTrace();
			return new ResponseEntity<DestinyResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<DestinyResponseRest>(response, HttpStatus.OK);
	}
}

