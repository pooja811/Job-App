package com.app.secure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.entity.User;
import com.app.entity.UserPrincipal;
import com.app.secure.repo.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("inside MyUserDetailsService.loadUserByUsername:"+username);
		User user = repo.findByUsername(username);

		if (user == null) {
			//System.out.println("User 404");
			log.error("username not found{}",username);
			throw new UsernameNotFoundException("User 404");
		}
		return new UserPrincipal(user);
	}

}