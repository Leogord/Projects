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

import com.qa.task.data.Vehicle;
import com.qa.task.service.VehicleServiceDB;

@RestController

public class VehicleController {

	private VehicleServiceDB service;

	public VehicleController(VehicleServiceDB service) {
		super();
		this.service = service;
	}

	@GetMapping("/getVehicleByID/{id}")
	public Vehicle getVehicleByIndex(@PathVariable Integer id) {
		return service.getVehicleByIndex(id);
	}

	@GetMapping("/getAllVehicles")
	public List<Vehicle> getAllVehicles() {
		return this.service.getAllVehicles();
	}

	@PostMapping("/createVehicle")
	public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
		Vehicle responseBody = this.service.createVehicle(vehicle);
		ResponseEntity<Vehicle> response = new ResponseEntity<Vehicle>(responseBody, HttpStatus.CREATED);
		return response;
	}

	@PutMapping("/updateVehicle/{id}")
	public ResponseEntity<Vehicle> updateVehicle(@RequestBody Vehicle vehicle, @PathVariable Integer id) {
		Vehicle responseBody = this.service.updateVehicleById(vehicle, id);
		return new ResponseEntity<Vehicle>(responseBody, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteVehicle/{id}")
	public ResponseEntity<Vehicle> deleteVehicle(@PathVariable Integer id) {
		service.deleteVehicleById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
