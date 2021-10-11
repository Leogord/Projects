package com.qa.task.rest;

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

import com.qa.task.data.Garage;
import com.qa.task.service.GarageServiceDB;

@RestController

public class GarageController {

	private GarageServiceDB service;

	public GarageController(GarageServiceDB service) {
		super();
		this.service = service;
	}

	@GetMapping("/getGarageByID/{id}")
	public Garage getGarageByIndex(@PathVariable Integer id) {
		return service.getGarageByIndex(id);
	}

	@GetMapping("/getAllGarages")
	public List<Garage> getAllGarages() {
		return this.service.getAllGarages();
	}

	@PostMapping("/createGarage")
	public ResponseEntity<Garage> createGarage(@RequestBody Garage garage) {
		Garage responseBody = this.service.createGarage(garage);
		ResponseEntity<Garage> response = new ResponseEntity<Garage>(responseBody, HttpStatus.CREATED);
		return response;
	}

	@PutMapping("/updateGarage/{id}")
	public ResponseEntity<Garage> updateGarage(@RequestBody Garage garage, @PathVariable Integer id) {
		Garage responseBody = this.service.updateGarageById(garage, id);
		return new ResponseEntity<Garage>(responseBody, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteGarage/{id}")
	public ResponseEntity<Garage> deleteGarage(@PathVariable Integer id) {
		service.deleteGarageById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
