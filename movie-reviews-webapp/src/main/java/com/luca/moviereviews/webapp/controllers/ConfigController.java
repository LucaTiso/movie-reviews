package com.luca.moviereviews.webapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luca.moviereviews.core.service.ConfigService;
import com.luca.moviereviews.responses.CountryResponse;

@RestController
@RequestMapping(path = "/api/config")
public class ConfigController {
	
	private ConfigService configService;
	

	public ConfigController(ConfigService configService) {
		this.configService=configService;
	}
	
	
	@GetMapping(path="/countries")
	public ResponseEntity<?> getCountries() {

		CountryResponse countryResponse=configService.searchCountries();
		
		ResponseEntity<?> response=new ResponseEntity<>(countryResponse,HttpStatus.OK);
		
		return response;
	}
	

}
