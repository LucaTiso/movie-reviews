package com.luca.moviereviews.core.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.luca.moviereviews.jpa.entities.SecurityUser;
import com.luca.moviereviews.jpa.repository.SecurityUserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final SecurityUserRepository repository;
	
	public UserDetailsServiceImpl(SecurityUserRepository repository) {
		this.repository=repository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SecurityUser securityUser=repository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("user not found"));
		
		UserDetailsImpl userDetails=new UserDetailsImpl();
		
		userDetails.setEmail(securityUser.getEmail());
		userDetails.setPassword(securityUser.getPassword());
		userDetails.setRole(securityUser.getRole());
		userDetails.setUsername(securityUser.getUsername());
		userDetails.setCountry(securityUser.getCountry());
		userDetails.setFirstName(securityUser.getFirstName());
		userDetails.setLastName(securityUser.getLastName());
		userDetails.setEnabled(securityUser.getEnabled());
		
		return userDetails;
	}
	
	

}
