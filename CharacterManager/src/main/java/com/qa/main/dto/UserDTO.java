package com.qa.main.dto;

import java.util.List;
import java.util.Objects;

public class UserDTO {

	private Integer id;

	private String name;

	private List<ToonWithRealmDTO> toons;

	public UserDTO() {
		super();
	}

	public UserDTO(Integer id, List<ToonWithRealmDTO> toons) {
		super();
		this.id = id;
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

	public List<ToonWithRealmDTO> getToons() {
		return toons;
	}

	public void setToons(List<ToonWithRealmDTO> toonDTOs) {
		this.toons = toonDTOs;
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
		UserDTO other = (UserDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(toons, other.toons);
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", toons=" + toons + "]";
	}

}
