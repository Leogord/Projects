package com.qa.task.data;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String type;

	private Integer numOfWheels;

	private Integer weight;

	@ManyToOne
	private Garage garage;

	public Vehicle() {
		super();
	}

	public Vehicle(Integer id, String type, Integer numOfWheels, Integer weight, Garage garage) {
		super();
		this.id = id;
		this.type = type;
		this.numOfWheels = numOfWheels;
		this.weight = weight;
		this.garage = garage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getNumOfWheels() {
		return numOfWheels;
	}

	public void setNumOfWheels(Integer numOfWheels) {
		this.numOfWheels = numOfWheels;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Garage getGarage() {
		return garage;
	}

	public void setGarage(Garage garage) {
		this.garage = garage;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", type=" + type + ", numOfWheels=" + numOfWheels + ", weight=" + weight
				+ ", garage=" + garage + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(garage, id, numOfWheels, type, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		return Objects.equals(garage, other.garage) && Objects.equals(id, other.id)
				&& Objects.equals(numOfWheels, other.numOfWheels) && Objects.equals(type, other.type)
				&& Objects.equals(weight, other.weight);
	}

}
