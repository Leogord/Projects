package com.qa.task.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.task.data.Garage;

@Repository
public interface GarageRepo extends JpaRepository<Garage, Integer> {

}
