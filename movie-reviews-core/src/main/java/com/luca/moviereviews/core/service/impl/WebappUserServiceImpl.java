package com.luca.moviereviews.core.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luca.moviereviews.core.requests.WebappUserLogin;
import com.luca.moviereviews.core.requests.WebappUserRegistration;
import com.luca.moviereviews.core.requests.WebappUserRequest;
import com.luca.moviereviews.core.service.WebappUserService;
import com.luca.moviereviews.core.utils.EntityUtils;
import com.luca.moviereviews.jpa.entities.WebappUser;
import com.luca.moviereviews.jpa.repository.WebappUserRepository;
import com.luca.moviereviews.responses.UserResponse;

@Service
public class WebappUserServiceImpl implements WebappUserService {

	@Autowired
	private WebappUserRepository webappUserRepository;

	@Override
	@Transactional
	public void registration(WebappUserRegistration registration) {

		WebappUser user = EntityUtils.dtoToEntity(registration);
		webappUserRepository.save(user);

	}

	@Override
	@Transactional
	public UserResponse login(WebappUserLogin login) {
		UserResponse response = new UserResponse();

		response = webappUserRepository.findByUsername(login.getUsername())
				.filter(u -> u.getPassword().contentEquals(login.getPassword()))
				.map(EntityUtils::entityToDto).orElse(null);
		
			
		return response;
	}

	@Override
	@Transactional
	public UserResponse editProfile(WebappUserRequest webappUserReqest) {
		
		UserResponse userResponse=null;
		
		WebappUser user=webappUserRepository.findByUsername(webappUserReqest.getUsername()).orElse(null);
		if(user!=null) {
			if(webappUserReqest.getCountry()!=null) {
				user.setCountry(webappUserReqest.getCountry());
			}
			if(webappUserReqest.getEmail()!=null) {
				user.setEmail(webappUserReqest.getEmail());
			}	
			if(webappUserReqest.getPassword()!=null) {
				user.setPassword(webappUserReqest.getPassword());
			}
			user.setLastUpdateTime(LocalDateTime.now());
			userResponse=EntityUtils.entityToDto(user);
		}
				
		return userResponse;

	}

}
