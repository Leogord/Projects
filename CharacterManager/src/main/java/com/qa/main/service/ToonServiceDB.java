package com.qa.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.main.exception.LevelOutOfRangeException;
import com.qa.main.exception.RealmNotFoundException;
import com.qa.main.exception.ToonNotFoundExcepton;
import com.qa.main.data.Toon;
import com.qa.main.dto.ToonWithRealmDTO;
import com.qa.main.repo.ToonRepo;



@Service
public class ToonServiceDB {
	private ToonRepo repo;
	
	private ModelMapper mapper;
	
	public ToonServiceDB(ToonRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	
	public ToonWithRealmDTO getToonById(Integer id) {
			try {
				return this.mapper.map(repo.findById(id).get(), ToonWithRealmDTO.class);
			} catch (Exception e) {
				throw new ToonNotFoundExcepton();
			}
			
	}
	
	public List<ToonWithRealmDTO> getAllToons(){
		List<Toon> saved = repo.findAll();
		List<ToonWithRealmDTO> toSend = new ArrayList<>();
		for(Toon toon: saved) {
			toSend.add(mapper.map(toon, ToonWithRealmDTO.class));
		}
		return toSend;
	}
	
	public Toon createToon (Toon toon) {
		
			if(toon.getLevel() >60 && toon.getLevel()>0) {
				throw new LevelOutOfRangeException();
			}
			try {
			return repo.save(toon);
		} catch (Exception e) {
			throw new RealmNotFoundException();
		}
		
		
	}
	
	public Toon updateToon(Toon toon, Integer id) {
		Toon toUpdate = repo.findById(id).get();
		toUpdate.setName(toon.getName());
		toUpdate.setLevel(toon.getLevel());
		toUpdate.setRace(toon.getRace());
		toUpdate.setClazz(toon.getClazz());
		toUpdate.setGold(toon.getGold());
		toUpdate.setRealm(toon.getRealm());
		return repo.save(toUpdate);
	}
	
	public void deleteToonById(Integer id) {
		this.repo.deleteById(id);
	}
	

}
