package com.qa.main.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import com.qa.main.data.User;
import com.qa.main.dto.ToonDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserDToTest {

	@Test
	void testToonDTO() {
		UserDTO dto = new UserDTO();
		assertTrue(dto instanceof UserDTO);
	}

	@Test
	void testFullDTO() {
		List<ToonWithRealmDTO> listRealm = List.of(new ToonWithRealmDTO());
		UserDTO dto = new UserDTO(1, listRealm);
		assertTrue(dto instanceof UserDTO);
	}

	@Test
	void testGandS() {
		List<ToonWithRealmDTO> listRealm = List.of(new ToonWithRealmDTO());
		UserDTO dto = new UserDTO(1, listRealm);

		dto.setId(1);
		dto.setName("Jack");
		dto.setToons(listRealm);

		assertEquals("Jack", dto.getName());
		assertEquals(1, dto.getId());
		assertEquals(listRealm, dto.getToons());

	}

	@Test
	void testtoString() {
		List<ToonWithRealmDTO> listRealm = List.of(new ToonWithRealmDTO());
		UserDTO dto = new UserDTO(1, listRealm);
		String expected = "UserDTO [id=" + dto.getId() + ", name=" + dto.getName() + ", toons=" + dto.getToons() + "]";
		assertEquals(expected, dto.toString());
	}

	@Test
	void TestEquals() {
		List<ToonWithRealmDTO> listRealm = List.of(new ToonWithRealmDTO());
		UserDTO dto1 = new UserDTO(1, listRealm);
		UserDTO dto2 = new UserDTO(1, listRealm);
		assertTrue(dto1.equals(dto2));
		assertFalse(dto1.equals(new User()));
		assertFalse(dto1.equals(null));
		assertTrue(dto1.equals(dto1));
	}

	@Test
	void TestHashCode() {
		List<ToonWithRealmDTO> listRealm = List.of(new ToonWithRealmDTO());
		UserDTO dto1 = new UserDTO(1, listRealm);
		UserDTO dto2 = new UserDTO(1, listRealm);
		assertTrue(dto1.hashCode() == dto2.hashCode());
		
	}

}
