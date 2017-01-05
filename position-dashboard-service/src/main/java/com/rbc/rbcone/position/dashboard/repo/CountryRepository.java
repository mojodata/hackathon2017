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
	
	private final Map<String, List<Region>> countryCodeToRegionMap = new HashMap<>();
	
	
	public CountryRepository() {
		DocumentContext documentContext = JsonPath.parse(getClass().getResourceAsStream("/countries_world.json"));
		loadRegions(documentContext, loadCountries(documentContext));
	}

	private List<String> loadCountries(DocumentContext documentContext) {
		List<String> countryCodes = new ArrayList<>();
		for (Object countryDescriptionObject : documentContext.<JSONArray>read("$..properties.description")) {
			String countryDescription = (String) countryDescriptionObject;
			String countryCode = countryDescription.substring(7, 9);
			countryCodes.add(countryCode);
			countryCodeToRegionMap.put(countryCode, new ArrayList<>());
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
				if ("Polygon".equals(geometryMap.get("type"))) {
					loadRegion(regions, geometryMap);
				}
			}
			countryIndex++;
		}
	}

	private void loadRegion(List<Region> regions, Map<String, JSONArray> geometryMap) {
		Region region = new Region();
		for (Object coordinatesObject : geometryMap.get("coordinates")) {
			for (Object coordinateObject : (JSONArray) coordinatesObject) {
				JSONArray coordinateArray = (JSONArray) coordinateObject;
				Coordinate coordinate = new Coordinate();
				coordinate.setLng(new BigDecimal((coordinateArray.get(0)).toString()));
				coordinate.setLat(new BigDecimal((coordinateArray.get(1)).toString()));
				region.getCoordinates().add(coordinate);
			}
		}
		regions.add(region);
	}

	public List<Region> getRegionsForCountry(String countryCode) {
		return countryCodeToRegionMap.containsKey(countryCode) ? countryCodeToRegionMap.get(countryCode) : Collections.emptyList();
	}
}