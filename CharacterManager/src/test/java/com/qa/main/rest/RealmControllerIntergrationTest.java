package com.qa.main.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.main.data.Realm;
import com.qa.main.dto.RealmDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"classpath:realm-schema.sql",
"classpath:realm-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class RealmControllerIntergrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;
	
	@Test
	 void testGetRealmById() throws Exception {
		final Realm savedRealm = new Realm(1, "Frostmane", "EU");
		String savedAsJson = this.mapper.writeValueAsString(savedRealm);
		 
		RequestBuilder request = get("/getRealmById/" + savedRealm.getId());
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedAsJson);
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}
	
	@Test
	 void testGetRealmByName() throws Exception {
		final Realm savedRealm = new Realm(1, "Frostmane", "EU");
		String savedAsJson = this.mapper.writeValueAsString(savedRealm);
		 
		RequestBuilder request = get("/getRealmByName/" + savedRealm.getName());
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedAsJson);
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}
	
	@Test
	 void testGetRealmByRegion() throws Exception {
		final List<Realm> savedRealm =List.of(new Realm(1, "Frostmane", "EU"));
		String savedAsJson = this.mapper.writeValueAsString(savedRealm);
		 
		RequestBuilder request = get("/getRealmByRegion/" + savedRealm.get(0).getRegion());
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedAsJson);
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}
	@Test
	void testGetAllRealms() throws Exception {
		String savedRealmAsJSON = this.mapper
				.writeValueAsString(List.of(new Realm(1, "Frostmane", "EU")));
		
		RequestBuilder request = get("/getAllRealms");
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedRealmAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}
	
	@Test
	void testCreate() throws Exception {
		final Realm testRealm = new Realm(2, "Azjol", "EU");
		String testRealmAsJSON = this.mapper.writeValueAsString(testRealm);

		final Realm savedRealm = new Realm(2, "Azjol", "EU");
		String savedRealmAsJSON = this.mapper.writeValueAsString(savedRealm);

		RequestBuilder request = post("/createRealm").contentType(MediaType.APPLICATION_JSON)
				.content(testRealmAsJSON);

		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkContent = content().json(savedRealmAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}
	
	@Test
	void testUpdate() throws Exception {
		final Realm testRealm = new Realm(1, "Azjol", "China");
		final String testRealmAsJSON = this.mapper.writeValueAsString(testRealm);

		RequestBuilder request = put("/updateRealm/1").contentType(MediaType.APPLICATION_JSON)
				.content(testRealmAsJSON);

		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkContent = content().json(testRealmAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}
	
	@Test
	void testDelete() throws Exception {
		this.mvc.perform(delete("/deleteRealm/1")).andExpect(status().isNoContent());
	}
	
}
