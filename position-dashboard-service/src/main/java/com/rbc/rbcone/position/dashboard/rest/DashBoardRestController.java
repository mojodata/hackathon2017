package com.rbc.rbcone.position.dashboard.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rbc.rbcone.position.dashboard.model.Coordinate;
import com.rbc.rbcone.position.dashboard.model.Region;
import com.rbc.rbcone.position.dashboard.service.AccountService;
import com.rbc.rbcone.position.dashboard.service.CountryService;

@RestController()
@RequestMapping("/api")
//FIXME MZ remove this when we are integrated 
@CrossOrigin(origins = "*")
public class DashBoardRestController {
	
	@Autowired
	private AccountService accountService;

	@Autowired
	private CountryService countryService;
	
	@GetMapping(path="accounts", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<AccountDTO> getAccounts() {
		return accountService.getUserAccounts();
	}

	@GetMapping(path="accounts/{accountNumber}/holdings", produces=MediaType.APPLICATION_JSON_VALUE)
	public AccountHoldingDTO getHoldings(@PathVariable(name="accountNumber", required=true) String accountNumber) {
		return accountService.getHoldings(accountNumber);
	}

	@GetMapping(path="accounts/{accountNumber}/{country}/holdings", produces=MediaType.APPLICATION_JSON_VALUE)
	public AccountHoldingDTO getHoldings(@PathVariable(name="accountNumber", required=true) String accountNumber,
                                         @PathVariable(name="country", required=true) String country) {
		return accountService.getHoldings(accountNumber, country);
	}

	@GetMapping(path="coordinates/{countryCode}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<List<Coordinate>> getCountryRegions(@PathVariable(name="countryCode") String countryCode) {
		List<List<Coordinate>> regions = new ArrayList<>();
		for (Region region : countryService.getCountryRegions(countryCode)) {
			regions.add(region.getCoordinates());
		}
		return regions;
	}

}