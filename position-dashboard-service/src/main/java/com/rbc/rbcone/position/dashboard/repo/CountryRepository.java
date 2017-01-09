package com.rbc.rbcone.position.dashboard.repo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.rbc.rbcone.position.dashboard.model.Coordinate;
import com.rbc.rbcone.position.dashboard.model.Region;

import net.minidev.json.JSONArray;

@Repository
public class CountryRepository {
	
	private static final String POINT = "Point";
	private static final String POLYGON = "Polygon";
	private final Map<String, List<Region>> countryCodeToRegionMap = new HashMap<>();
	private final Map<String, Coordinate> countryCodeToPointMap = new HashMap<>();
	
	
	public CountryRepository() {
		DocumentContext documentContext = JsonPath.parse(getClass().getResourceAsStream("/countries_world.json"));
		List<String> countries = loadCountries(documentContext);
		loadRegions(documentContext, countries);
		loadPoints(documentContext, countries);
	}

	private List<String> loadCountries(DocumentContext documentContext) {
		List<String> countryCodes = new ArrayList<>();
		for (Object countryDescriptionObject : documentContext.<JSONArray>read("$..properties.description")) {
			String countryDescription = (String) countryDescriptionObject;
			String countryCode = countryDescription.substring(7, 9);
			countryCodes.add(countryCode);
			countryCodeToRegionMap.put(countryCode, new ArrayList<>());
			countryCodeToPointMap.put(countryCode, new Coordinate());
		}
		return countryCodes;
	}
	
	private void loadRegions(DocumentContext documentContext, List<String> countryCodes) {
		int countryIndex = 0;
		for (Object geometriesObject : documentContext.<JSONArray>read("$..geometries")) {
			for (Object geometryObject : (JSONArray) geometriesObject) {
				List<Region> regions = countryCodeToRegionMap.get(countryCodes.get(countryIndex));
				@SuppressWarnings("unchecked")
				Map<String, JSONArray> geometryMap = (Map<String, JSONArray>) geometryObject;
				if (POLYGON.equals(geometryMap.get("type"))) {
					loadRegion(regions, geometryMap);
				}
			}
			countryIndex++;
		}
	}

	private void loadPoints(DocumentContext documentContext, List<String> countryCodes) {
		int countryIndex = 0;
		for (Object geometriesObject : documentContext.<JSONArray>read("$..geometries")) {
			for (Object geometryObject : (JSONArray) geometriesObject) {
				Coordinate coordinate = countryCodeToPointMap.get(countryCodes.get(countryIndex));
				@SuppressWarnings("unchecked")
				Map<String, JSONArray> geometryMap = (Map<String, JSONArray>) geometryObject;
				if (POINT.equals(geometryMap.get("type"))) {
					loadPoint(coordinate, geometryMap);
				}
			}
			countryIndex++;
		}
	}

	private void loadRegion(List<Region> regions, Map<String, JSONArray> geometryMap) {
		Region region = new Region();
		int downSampleRate = 1;
		for (Object coordinatesObject : geometryMap.get("coordinates")) {
			JSONArray coordinates = (JSONArray) coordinatesObject;
			for (int i = 0; i < coordinates.size(); i+=downSampleRate) {
				JSONArray coordinateArray = (JSONArray) coordinates.get(i);
				Coordinate coordinate = new Coordinate();
				coordinate.setLng(new BigDecimal((coordinateArray.get(0)).toString()));
				coordinate.setLat(new BigDecimal((coordinateArray.get(1)).toString()));
				region.getCoordinates().add(coordinate);
			}
		}
		regions.add(region);
	}

	private void loadPoint(Coordinate coordinate, Map<String, JSONArray> geometryMap) {
		JSONArray coordinates = (JSONArray) geometryMap.get("coordinates");
		coordinate.setLng(new BigDecimal((coordinates.get(0)).toString()));
		coordinate.setLat(new BigDecimal((coordinates.get(1)).toString()));
	}

	public List<Region> getRegionsForCountry(String countryCode) {
		return countryCodeToRegionMap.containsKey(countryCode) ? countryCodeToRegionMap.get(countryCode) : Collections.emptyList();
	}

	public Coordinate getCountryPoints(String countryCode) {
		return countryCodeToPointMap.containsKey(countryCode) ? countryCodeToPointMap.get(countryCode) : new Coordinate();
	}
}