package com.qa.main.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.main.data.Toon;
import com.qa.main.dto.ToonDTO;
import com.qa.main.dto.ToonWithRealmDTO;
import com.qa.main.service.ToonServiceDB;

@RestController
public class ToonController {

	private ToonServiceDB service;

	public ToonController(ToonServiceDB service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/getToonById/{id}")
	public ToonWithRealmDTO getToon(@PathVariable Integer id) {
		return service.getToonById(id);
	}
	
	@GetMapping("/getAllToons")
	public List<ToonWithRealmDTO> getAllToons(){
		return service.getAllToons();
	}
	
	@PostMapping("/createToon")
	public ResponseEntity<Toon> createToon(@RequestBody Toon toon){
		Toon responseBody = service.createToon(toon);
		return new ResponseEntity<Toon>(responseBody, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateToon/{id}")
	public ResponseEntity<Toon> updateToon(@RequestBody Toon toon, @PathVariable Integer id){
		Toon responseBody = service.updateToon(toon, id);
		return new ResponseEntity<Toon>(responseBody, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteToon/{id}")
	public ResponseEntity<Toon> deleteToon(@PathVariable Integer id){
		service.deleteToonById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
