package com.qa.task.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.task.data.Vehicle;
import com.qa.task.repo.VehicleRepo;

@Service
public class VehicleServiceDB {

	private VehicleRepo repo;

	public VehicleServiceDB(VehicleRepo repo) {
		super();
		this.repo = repo;
	}

	public Vehicle getVehicleByIndex(Integer id) {
		return this.repo.findById(id).get();
	}

	public List<Vehicle> getAllVehicles() {
		return this.repo.findAll();
	}

	public Vehicle createVehicle(Vehicle vehicle) {

		return this.repo.save(vehicle);
	}

	public Vehicle updateVehicleById(Vehicle vehicle, Integer id) {
		Vehicle toUpdate = this.repo.findById(id).get();
		toUpdate.setNumOfWheels(vehicle.getNumOfWheels());
		toUpdate.setType(vehicle.getType());
		toUpdate.setWeight(vehicle.getWeight());
		toUpdate.setGarage(vehicle.getGarage());
		return repo.save(toUpdate);
	}

	public void deleteVehicleById(Integer id) {
		this.repo.deleteById(id);
	}
}
