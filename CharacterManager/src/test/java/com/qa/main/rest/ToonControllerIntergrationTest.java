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
import com.qa.main.data.Toon;
import com.qa.main.data.User;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"classpath:toon-schema.sql",
"classpath:toon-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class ToonControllerIntergrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;
	
	@Test
	 void testGetToonById() throws Exception {
		final Toon savedToon = new Toon(1,new User(1,"Jack",null),"Gonzalo",60,"Night Elf","Warrior",100,new Realm(1,"Frostmane","EU"));
		String savedAsJson = this.mapper.writeValueAsString(savedToon);
		 
		RequestBuilder request = get("/getToonById/" + savedToon.getId());
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedAsJson);
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}
	@Test
	void testGetAllToons() throws Exception {
		String savedToonAsJSON = this.mapper
				.writeValueAsString(List.of(new Toon(1,new User(1,"Jack",null),"Gonzalo",60,"Night Elf","Warrior",100,new Realm(1,"Frostmane","EU"))));
		
		RequestBuilder request = get("/getAllToons");
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedToonAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}
	
	@Test
	void testCreate() throws Exception {
		final Toon testToon = new Toon(2,new User(1,"Jack",null),"Gonzalo",60,"Night Elf","Warrior",100,new Realm(1,"Frostmane","EU"));
		String testToonAsJSON = this.mapper.writeValueAsString(testToon);

		final Toon savedToon = new Toon(2,new User(1,"Jack",null),"Gonzalo",60,"Night Elf","Warrior",100,new Realm(1,"Frostmane","EU"));
		String savedToonAsJSON = this.mapper.writeValueAsString(savedToon);

		RequestBuilder request = post("/createToon").contentType(MediaType.APPLICATION_JSON)
				.content(testToonAsJSON);

		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkContent = content().json(savedToonAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}
	
	@Test
	void testUpdate() throws Exception {
		final Toon testToon = new Toon(1,new User(1,"Jack",null),"Shadrock",60,"Night Elf","Hunter",100,new Realm(1,"Frostmane","EU"));
		final String testToonAsJSON = this.mapper.writeValueAsString(testToon);

		RequestBuilder request = put("/updateToon/1").contentType(MediaType.APPLICATION_JSON)
				.content(testToonAsJSON);

		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkContent = content().json(testToonAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}
	
	@Test
	void testDelete() throws Exception {
		this.mvc.perform(delete("/deleteToon/1")).andExpect(status().isNoContent());
	}
	
}
