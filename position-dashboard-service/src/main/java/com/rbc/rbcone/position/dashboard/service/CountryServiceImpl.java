package com.rbc.rbcone.position.dashboard.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rbc.rbcone.position.dashboard.model.Region;

@Service
public class CountryServiceImpl implements CountryService {

	@Override
	public List<Region> getCountryRegions(String countryCode) {
		return Collections.emptyList();
	}

}