package com.luca.moviereviews.responses;

import java.util.List;

import com.luca.moviereviews.responses.dto.CountryDto;

public class CountryResponse {
	
	private List<CountryDto> countryList;

	public List<CountryDto> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<CountryDto> countryList) {
		this.countryList = countryList;
	}
	
}
