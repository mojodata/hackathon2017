package com.rbc.rbcone.position.dashboard.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rbc.rbcone.position.dashboard.service.AccountService;

@RestController()
@RequestMapping("/api")
public class DashBoardRestController {
	
	@Autowired
	AccountService accountService;
	
	
	@GetMapping(path="accounts", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<AccountDTO> getAccounts() {
		return accountService.getUserAccounts();
	}

	@GetMapping(path="{accountNumber}/holdings", produces=MediaType.APPLICATION_JSON_VALUE)
	public AccountHoldingDTO getHoldings(@RequestParam(name="accountNumber", defaultValue="ALL", required=true) String accountNumber) {
		return accountService.getHoldings(accountNumber);
	}

}