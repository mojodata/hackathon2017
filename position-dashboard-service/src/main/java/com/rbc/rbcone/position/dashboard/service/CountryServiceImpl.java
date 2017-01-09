package com.rbc.rbcone.position.dashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbc.rbcone.position.dashboard.model.Coordinate;
import com.rbc.rbcone.position.dashboard.model.Region;
import com.rbc.rbcone.position.dashboard.repo.CountryRepository;

@Service
public class CountryServiceImpl implements CountryService {
	
	@Autowired
	private CountryRepository countryRepository;

	@Override
	public List<Region> getCountryRegions(String countryCode) {
		return countryRepository.getRegionsForCountry(countryCode);
	}

	@Override
	public Coordinate getCountryPoints(String countryCode) {
		return countryRepository.getCountryPoints(countryCode);
	}

}