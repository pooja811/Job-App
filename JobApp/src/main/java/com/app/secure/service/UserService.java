package com.app.secure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.entity.User;
import com.app.secure.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo repo;

	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JWTService jwtService;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	public User saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return repo.save(user);

	}

	public String verify(User user) {
		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if (authentication.isAuthenticated())
			return jwtService.generateToken(user.getUsername());
		return "fail";
	}
}