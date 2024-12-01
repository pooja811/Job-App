package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.User;
import com.app.secure.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService service;
	
	@PostMapping("/register")
	public User register(@RequestBody User user) {

		return service.saveUser(user);
	}
}
