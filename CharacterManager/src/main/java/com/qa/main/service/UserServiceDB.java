package com.qa.main.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.qa.main.data.Toon;
import com.qa.main.data.User;
import com.qa.main.dto.ToonWithRealmDTO;
import com.qa.main.dto.UserDTO;
import com.qa.main.exception.UserAlreadyExistsException;
import com.qa.main.repo.UserRepo;

@Service
public class UserServiceDB {

	private UserRepo repo;

	public UserServiceDB(UserRepo repo) {
		super();
		this.repo = repo;
	}

	public UserDTO mapToDTO(User user) {
		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setName(user.getName());
		List<ToonWithRealmDTO> toonDTOs = new ArrayList<>();
		for (Toon toon : user.getToons()) {
			ToonWithRealmDTO toonDto = new ToonWithRealmDTO();
			toonDto.setId(toon.getId());
			toonDto.setClazz(toon.getClazz());
			toonDto.setGold(toon.getGold());
			toonDto.setLevel(toon.getLevel());
			toonDto.setName(toon.getName());
			toonDto.setRace(toon.getRace());
			toonDto.setRealm(toon.getRealm());
			toonDTOs.add(toonDto);
		}
		dto.setToons(toonDTOs);
		return dto;
	}

	public UserDTO getUserByName(String name) {
		User got = repo.findByName(name);
		return this.mapToDTO(got);
	}

	public User createUser(User user) {
		if (this.repo.findByName(user.getName()) != null) {
			throw new UserAlreadyExistsException();
		} else
			return repo.save(user);
	}

	public void deleteUserById(Integer id) {
		this.repo.deleteById(id);
	}

	public List<UserDTO> getAllUsers() {
		List<User> saved = repo.findAll();
		List<UserDTO> toSend = new ArrayList<>();
		for (User user : saved) {
			toSend.add(mapToDTO(user));
		}
		return toSend;
	}

}
