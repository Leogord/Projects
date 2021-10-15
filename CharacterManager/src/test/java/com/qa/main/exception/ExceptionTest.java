package com.qa.main.exception;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
@Sql(scripts = { "classpath:toon-schema.sql",
		"classpath:toon-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

public class ExceptionTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	
	@Test
	 void testToonNotFound() throws Exception {

		RequestBuilder request = get("/getToonById/4"); 
		
		ResultMatcher checkStatus = status().isNotFound();

		this.mvc.perform(request).andExpect(checkStatus);
	}
	
	@Test
	 void testLevelOutOfRange() throws Exception {

		final Toon testToon = new Toon(2,new User(1,"Jack",null),"Gonzalo",65,"Night Elf","Warrior",100,new Realm(1,"Frostmane","EU"));
		String testToonAsJSON = this.mapper.writeValueAsString(testToon);

		RequestBuilder request = post("/createToon").contentType(MediaType.APPLICATION_JSON)
				.content(testToonAsJSON);

		ResultMatcher checkStatus = status().isPreconditionFailed();

		this.mvc.perform(request).andExpect(checkStatus);
	}
	
	@Test
	 void testRealmNotFound() throws Exception {
	
		RequestBuilder request = get("/getRealmById/5");
		
		ResultMatcher checkStatus = status().isNotFound();

		this.mvc.perform(request).andExpect(checkStatus);
	}
	
	@Test
	void testRealmAlreadyExists() throws Exception {
		final Realm testRealm = new Realm(1, "Frostmane", "EU");
		
		String testRealmAsJSON = this.mapper.writeValueAsString(testRealm);

		RequestBuilder request = post("/createRealm").contentType(MediaType.APPLICATION_JSON)
				.content(testRealmAsJSON);

		ResultMatcher checkStatus = status().isConflict();

		this.mvc.perform(request).andExpect(checkStatus);
	}
}
