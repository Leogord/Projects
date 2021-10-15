package com.qa.main.data;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity


public class Toon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private Integer level;
	
	private String race;
	
	private String clazz;
	
	private Integer gold;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Realm realm;

	public Toon() {
		super();
	}
	
	
	public Toon(Integer id, User user,  String name, Integer level, String race, String clazz, Integer gold, Realm realm) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		this.race = race;
		this.clazz = clazz;
		this.gold = gold;
		this.realm = realm;
		this.user = user;
	}
	
	public Toon(Integer id, String name, Integer level, String race, String clazz, Integer gold, Realm realm) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		this.race = race;
		this.clazz = clazz;
		this.gold = gold;
		this.realm = realm;
	}


	public Toon(Integer id,User user, String name, Integer level, String race, String clazz, Integer gold) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		this.race = race;
		this.clazz = clazz;
		this.gold = gold;
		this.user = user;
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


	public Integer getLevel() {
		return level;
	}


	public void setLevel(Integer level) {
		this.level = level;
	}


	public String getRace() {
		return race;
	}


	public void setRace(String race) {
		this.race = race;
	}


	public String getClazz() {
		return clazz;
	}


	public void setClazz(String clazz) {
		this.clazz = clazz;
	}


	public Integer getGold() {
		return gold;
	}


	public void setGold(Integer gold) {
		this.gold = gold;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Realm getRealm() {
		return realm;
	}


	public void setRealm(Realm realm) {
		this.realm = realm;
	}


	@Override
	public int hashCode() {
		return Objects.hash(clazz, gold, id, level, name, race, realm, user);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Toon other = (Toon) obj;
		return Objects.equals(clazz, other.clazz) && Objects.equals(gold, other.gold) && Objects.equals(id, other.id)
				&& Objects.equals(level, other.level) && Objects.equals(name, other.name)
				&& Objects.equals(race, other.race) && Objects.equals(realm, other.realm)
				&& Objects.equals(user, other.user);
	}


	@Override
	public String toString() {
		return "Toon [id=" + id + ", name=" + name + ", level=" + level + ", race=" + race + ", clazz=" + clazz
				+ ", gold=" + gold + ", user=" + user + ", realm=" + realm + "]";
	}



	
	
	
	
	
}
