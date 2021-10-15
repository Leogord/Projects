package com.qa.main.dto;

import java.util.List;
import java.util.Objects;


public class RealmDTO {

	private Integer id;
	
	private String name;
	
	private String region;
	
	private List<ToonDTO> toon;
	


	public RealmDTO() {
		super();
	}

	
	
	public RealmDTO(Integer id, String name, String region, List<ToonDTO> toon) {
		super();
		this.id = id;
		this.name = name;
		this.region = region;
		this.toon = toon;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ToonDTO> getToon() {
		return toon;
	}

	public void setToon(List<ToonDTO> toon) {
		this.toon = toon;
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

	@Override
	public String toString() {
		return "RealmDTO [id=" + id + ", name=" + name + ", region=" + region + ", toon=" + toon + "]";
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
		RealmDTO other = (RealmDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(region, other.region)
				&& Objects.equals(toon, other.toon);
	}
	
	
}
