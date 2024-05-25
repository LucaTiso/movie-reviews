package com.luca.moviereviews.core.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.luca.moviereviews.core.service.ConfigService;
import com.luca.moviereviews.jpa.entities.Country;
import com.luca.moviereviews.jpa.repository.CountryRepository;
import com.luca.moviereviews.responses.CountryResponse;
import com.luca.moviereviews.responses.dto.CountryDto;

@Service
public class ConfigServiceImpl implements ConfigService{
	
	private final CountryRepository countryRepository;
	
	public ConfigServiceImpl(CountryRepository countryRepository) {
		this.countryRepository=countryRepository;
	}

	@Override
	public CountryResponse searchCountries() {
		
		List<Country> countries=countryRepository.findAllByOrderByNameAsc();
		
		List<CountryDto> countryList=countries.stream().map(c->
			 new CountryDto(c.getId(),c.getName())
			
		).toList();
		
		CountryResponse countryResponse=new CountryResponse();
		countryResponse.setCountryList(countryList);
		
		return countryResponse;
		
	}

}
