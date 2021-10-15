package com.qa.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.main.data.Realm;
import com.qa.main.data.Toon;
import com.qa.main.dto.RealmDTO;
import com.qa.main.dto.ToonDTO;
import com.qa.main.exception.RealmAlreadyExistsException;
import com.qa.main.exception.RealmNotFoundException;
import com.qa.main.repo.RealmRepo;

@Service
public class RealmServiceDB {
	private RealmRepo repo;

	public RealmServiceDB(RealmRepo repo) {
		super();
		this.repo = repo;
	}

	public RealmDTO mapToDTO(Realm realm) {
		RealmDTO dto = new RealmDTO();
		dto.setId(realm.getId());
		dto.setName(realm.getName());
		dto.setRegion(realm.getRegion());
		List<ToonDTO> toonDTOs = new ArrayList<>();
		for (Toon toon : realm.getToon()) {
			ToonDTO toonDto = new ToonDTO();
			toonDto.setClazz(toon.getClazz());
			toonDto.setGold(toon.getGold());
			toonDto.setId(toon.getId());
			toonDto.setLevel(toon.getLevel());
			toonDto.setName(toon.getName());
			toonDto.setRace(toon.getRace());
			toonDTOs.add(toonDto);
		}
		dto.setToon(toonDTOs);
		return dto;
	}

	public RealmDTO getRealmById(Integer id) {
		try {
		Realm saved = repo.findById(id).get();
		return this.mapToDTO(saved);
		} catch (Exception e){
			throw new RealmNotFoundException();
			
		}
	}

	public RealmDTO getRealmByName(String name) {
		Realm saved = repo.findByName(name);
		return this.mapToDTO(saved);
	}

	public List<RealmDTO> getRealmByRegion(String region) {

		List<Realm> got = repo.findByregion(region);
		List<RealmDTO> toSend = new ArrayList<>();
		for (Realm realm : got) {
			toSend.add(this.mapToDTO(realm));
		}
		return toSend;
	}

	public List<RealmDTO> getAllRealms() {
		List<Realm> saved = repo.findAll();
		List<RealmDTO> toSend = new ArrayList<>();
		for (Realm realm : saved) {
			toSend.add(mapToDTO(realm));
		}
		return toSend;
	}

	public Realm createRealm(Realm realm) {
		if (this.repo.findByName(realm.getName()) != null) {
			throw new RealmAlreadyExistsException();
		} else return repo.save(realm);
		
	}

	public Realm updateRealm(Realm realm, Integer id) {
		Realm toUpdate = repo.findById(id).get();
		toUpdate.setName(realm.getName());
		toUpdate.setRegion(realm.getRegion());
		return repo.save(toUpdate);
	}

	public void deleteRealmById(Integer id) {
		this.repo.deleteById(id);
	}

}
