package com.rbc.rbcone.position.dashboard.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rbc.rbcone.position.dashboard.rest.AccountDTO;
import com.rbc.rbcone.position.dashboard.rest.AccountHoldingDTO;

@Service
public class AccountServiceImpl implements AccountService {

	@Override
	public List<AccountDTO> getUserAccounts() {
		//FIXME hook up the services here
		return Arrays.asList(new AccountDTO("1", "first Account"), new AccountDTO("2", "second Account"));
	}

	@Override
	public AccountHoldingDTO getHoldings(String accountNumber) {
		//FIXME hook up the services here
		AccountHoldingDTO dto = new AccountHoldingDTO();
		
		dto.setTotalMarketValue(new BigDecimal("12342.245"));
		Map<String, BigDecimal> countryTotalMarketValue = new HashMap<String, BigDecimal>();
		countryTotalMarketValue.put("CA", new BigDecimal("10000.00"));
		countryTotalMarketValue.put("USA", new BigDecimal("2342.245"));
		
		dto.setCountryTotalMarketValue(countryTotalMarketValue );
		
		return dto;
	}
	
}