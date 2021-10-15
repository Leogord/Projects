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
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Toon> toons;

	public User() {
		super();
	}

	public User(Integer id, String name, List<Toon> toons) {
		super();
		this.id = id;
		this.name = name;
		this.toons = toons;
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

	public List<Toon> getToons() {
		return toons;
	}

	public void setToons(List<Toon> toons) {
		this.toons = toons;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", toons=" + toons + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, toons);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(toons, other.toons);
	}

}
