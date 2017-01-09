package com.rbc.rbcone.position.dashboard.service;

import java.util.List;

import com.rbc.rbcone.position.dashboard.model.Coordinate;
import com.rbc.rbcone.position.dashboard.model.Region;

public interface CountryService {
	
	public List<Region> getCountryRegions(String countryCode);

	public Coordinate getCountryPoints(String countryCode);

}