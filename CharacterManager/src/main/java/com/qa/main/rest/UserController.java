package com.qa.main.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.main.data.Toon;
import com.qa.main.data.User;
import com.qa.main.dto.ToonDTO;
import com.qa.main.dto.ToonWithRealmDTO;
import com.qa.main.dto.UserDTO;
import com.qa.main.service.ToonServiceDB;
import com.qa.main.service.UserServiceDB;

@RestController
public class UserController {

	private UserServiceDB service;

	public UserController(UserServiceDB service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/getUserByName/{name}")
	public UserDTO getUser(@PathVariable String name) {
		return service.getUserByName(name);
	}
	
	@GetMapping("/getAllUsers")
	public List<UserDTO> getAllUsers(){
		return service.getAllUsers();
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<User> createUser(@RequestBody User User){
		User responseBody = service.createUser(User);
		return new ResponseEntity<User>(responseBody, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Integer id){
		service.deleteUserById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
