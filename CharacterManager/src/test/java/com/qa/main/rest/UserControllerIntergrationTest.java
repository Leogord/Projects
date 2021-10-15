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
import com.qa.main.data.User;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"classpath:toon-schema.sql",
"classpath:toon-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class UserControllerIntergrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;
	
	@Test
	 void testGetUserByName() throws Exception {
		final User savedUser = new User(1,"Jack",null);
		String savedAsJson = this.mapper.writeValueAsString(savedUser);
		 
		RequestBuilder request = get("/getUserByName/" + savedUser.getName());
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedAsJson);
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}


	@Test
	void testGetAllUsers() throws Exception {
		String savedUserAsJSON = this.mapper
				.writeValueAsString(List.of(new User(1,"Jack",null)));
		
		RequestBuilder request = get("/getAllUsers");
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedUserAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}
	
	@Test
	void testCreate() throws Exception {
		final User testUser = new User(2,"Dave",null);
		String testUserAsJSON = this.mapper.writeValueAsString(testUser);

		final User savedUser = new User(2,"Dave",null);
		String savedUserAsJSON = this.mapper.writeValueAsString(savedUser);

		RequestBuilder request = post("/createUser").contentType(MediaType.APPLICATION_JSON)
				.content(testUserAsJSON);

		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkContent = content().json(savedUserAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testDelete() throws Exception {
		this.mvc.perform(delete("/deleteToon/1"));
		this.mvc.perform(delete("/deleteRealm/1"));
		this.mvc.perform(delete("/deleteUser/1")).andExpect(status().isNoContent());
	}
	
}
