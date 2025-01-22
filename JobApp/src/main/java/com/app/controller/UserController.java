package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.User;
import com.app.secure.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "User APIs", description = "Register and Login")
public class UserController {

	@Autowired
	UserService service;
	
	@PostMapping("/register")
	public User register(@RequestBody User user) {

		return service.saveUser(user);
	}

	@PostMapping("/login")
	@Operation(summary = "Authenticate user", description = "Provide username and password to obtain JWT token.")
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "Bad request, missing or invalid fields."),
			@ApiResponse(responseCode = "403", description = "Forbidden, invalid username or password.") })
	public ResponseEntity<String> login(@RequestBody User user) {
		String verify = service.verify(user);
		return ResponseEntity.status(HttpStatus.OK).body(verify);

	}
}
