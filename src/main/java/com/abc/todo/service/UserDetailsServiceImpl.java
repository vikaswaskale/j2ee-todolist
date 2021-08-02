package com.abc.todo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abc.todo.model.User;
import com.abc.todo.repository.UserRepo;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepo.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found for username" + username);
		}
		return new org.springframework.security.core.userdetails.User(user.get().getUserName(), user.get().getPassword(),user.get().getRoles());
	}

}
