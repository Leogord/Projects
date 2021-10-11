package com.qa.task.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.task.data.Garage;
import com.qa.task.repo.GarageRepo;

@Service
public class GarageServiceDB {

	private GarageRepo repo;

	public GarageServiceDB(GarageRepo repo) {
		super();
		this.repo = repo;
	}

	public Garage getGarageByIndex(Integer id) {
		return this.repo.findById(id).get();
	}

	public List<Garage> getAllGarages() {
		return this.repo.findAll();
	}

	public Garage createGarage(Garage garage) {

		return this.repo.save(garage);
	}

	public Garage updateGarageById(Garage garage, Integer id) {
		Garage toUpdate = this.repo.findById(id).get();
		toUpdate.setAddress(garage.getAddress());
		toUpdate.setName(garage.getName());
		return repo.save(toUpdate);
	}

	public void deleteGarageById(Integer id) {
		this.repo.deleteById(id);
	}
}
