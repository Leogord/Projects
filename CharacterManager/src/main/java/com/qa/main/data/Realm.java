package com.qa.main.data;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class Realm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnore
	@OneToMany(mappedBy = "realm")
	private List<Toon> toon;
	
	private String name;
	
	private String region;

	public Realm() {
		super();
	}

	
	
	public Realm(Integer id, String name, String region) {
		super();
		this.id = id;
		this.name = name;
		this.region = region;
	}



	public Realm(Integer id, List<Toon> toon, String name, String region) {
		super();
		this.id = id;
		this.toon = toon;
		this.name = name;
		this.region = region;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	public List<Toon> getToon() {
		return toon;
	}

	public void setToon(List<Toon> toon) {
		this.toon = toon;
	}



	@Override
	public String toString() {
		return "Realm [id=" + id + ", toon=" + toon + ", name=" + name + ", region=" + region + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, name, region, toon);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Realm other = (Realm) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(region, other.region)
				&& Objects.equals(toon, other.toon);
	}


	
	
	
}
