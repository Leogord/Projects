package com.qa.main.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.main.data.Realm;
import com.qa.main.data.Toon;
import com.qa.main.data.User;
import com.qa.main.dto.RealmDTO;
import com.qa.main.dto.ToonDTO;
import com.qa.main.repo.RealmRepo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class RealmServiceTest {

	@Autowired
	private RealmServiceDB service;

	@MockBean
	private RealmRepo repo;

	@Test
	void testMapToDTO() {
		final List<ToonDTO> listToonDto = List.of(new ToonDTO(1,"Jack", 16, "Night Elf", "Warrior", 123));
		final List<Toon> listToon = List.of(new Toon(1,new User(1,"Jack",null), "Jack", 16, "Night Elf", "Warrior", 123));
		final RealmDTO realmDTO = new RealmDTO(1, "FrostMane", "EU", listToonDto);
		final Realm realm = new Realm(1, listToon, "FrostMane", "EU");

		assertThat(this.service.mapToDTO(realm)).isEqualTo(realmDTO);

	}

	@Test
	void testGetAllRealms() {
		final List<Toon> listToon = List.of(new Toon(2,new User(1,"Jack",null),"Gonzalo",60,"Night Elf","Warrior",100,new Realm(1,"Frostmane","EU")));
		final List<Realm> realms = List.of(new Realm(1, listToon, "FrostMane", "EU"),
				new Realm(2, listToon, "FrostMane", "EU"));
		List<RealmDTO> tocheck = new ArrayList<>();
		for (Realm realm : realms) {
			tocheck.add(this.service.mapToDTO(realm));
		}

		Mockito.when(this.repo.findAll()).thenReturn(realms);

		assertThat(this.service.getAllRealms()).isEqualTo(tocheck);

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	void testCreate() {
		Realm newRealm = new Realm(1, null, "FrostMane", "EU");
		Realm savedRealm = new Realm(1, null, "FrostMane", "EU");

		Mockito.when(this.repo.save(newRealm)).thenReturn(savedRealm);

		assertThat(this.service.createRealm(newRealm)).isEqualTo(savedRealm);

		Mockito.verify(this.repo, Mockito.times(1)).save(newRealm);
	}

	@Test
	void testUpdate() {
		Realm realm = new Realm(1, null, "Azjol", "EU");
		Optional<Realm> optionalRealm = Optional.of(realm);

		Realm newRealm = new Realm(1, null, "FrostMane", "EU");

		Mockito.when(this.repo.findById(1)).thenReturn(optionalRealm);
		Mockito.when(this.repo.save(newRealm)).thenReturn(newRealm);

		assertThat(this.service.updateRealm(newRealm, realm.getId())).isEqualTo(newRealm);

		Mockito.verify(this.repo, Mockito.times(1)).findById(1);
		Mockito.verify(this.repo, Mockito.times(1)).save(newRealm);
	}

	@Test
	void testGetById() {
		final Integer Id = 1;
		final List<Toon> listToon = List.of(new Toon(2,new User(1,"Jack",null),"Gonzalo",60,"Night Elf","Warrior",100,new Realm(1,"Frostmane","EU")));
		final Realm realm = new Realm(1, listToon, "FrostMane", "EU");
		RealmDTO toCheck = this.service.mapToDTO(realm);

		Mockito.when(this.repo.findById(Id)).thenReturn(Optional.of(realm));

		assertThat(this.service.getRealmById(Id)).isEqualTo(toCheck);
 
		Mockito.verify(this.repo, Mockito.times(1)).findById(Id);
	}

}
