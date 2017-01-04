package com.rbc.rbcone.position.dashboard.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbc.rbcone.position.dashboard.model.Account;
import com.rbc.rbcone.position.dashboard.model.Holding;
import com.rbc.rbcone.position.dashboard.repo.AccountRepository;
import com.rbc.rbcone.position.dashboard.repo.HoldingRepository;
import com.rbc.rbcone.position.dashboard.rest.AccountDTO;
import com.rbc.rbcone.position.dashboard.rest.AccountHoldingDTO;

@Service
public class AccountServiceImpl implements AccountService {

	private static final String ZERO = "0.0";

	private static final String ALL_ACCOUNTS = "ALL";

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	HoldingRepository holdingRepository;
	
	@Override
	public List<AccountDTO> getUserAccounts() {
		List<Account> accounts = accountRepository.findAll();
		
		List<AccountDTO> accountDTOs = new ArrayList<>();

		for (Account account : accounts) {
			accountDTOs.add(new AccountDTO(account.getAccountNumber(), account.getAccountName()));
		}
		
		return accountDTOs;
	}

	@Override
	public AccountHoldingDTO getHoldings(String accountNumber) {
		AccountHoldingDTO dto = new AccountHoldingDTO();
		List<Holding> holdings;
		
		if (ALL_ACCOUNTS.equals(accountNumber)) {
			holdings = holdingRepository.findAll();
		} else {
			holdings = holdingRepository.findByAccountNumber(accountNumber);
		}
		
		dto.setTotalMarketValue(calculateTotalMarketValue(holdings));
		dto.setCountryTotalMarketValue(calculateTotalMarketValueByCountry(holdings));
		
		return dto;
	}

	private Map<String, BigDecimal> calculateTotalMarketValueByCountry(List<Holding> holdings) {
		Map<String, BigDecimal> map = new HashMap<>();
		
		for (Holding holding : holdings) {
			String countryOfIssuer = holding.getCountryOfIssuer();
			BigDecimal marketBaseValue = holding.getMarketBaseValue() != null ? holding.getMarketBaseValue() : new BigDecimal(ZERO);
			
			if (map.containsKey(countryOfIssuer)) {
				map.put(countryOfIssuer, map.get(countryOfIssuer).add(marketBaseValue));
			} else {
				map.put(countryOfIssuer, marketBaseValue);
			}
		}
		
		return map;
	}

	private BigDecimal calculateTotalMarketValue(List<Holding> holdings) {
		BigDecimal totalMarketValue = new BigDecimal(ZERO);
		
		for (Holding holding : holdings) {
			BigDecimal marketBaseValue = holding.getMarketBaseValue() != null ? holding.getMarketBaseValue() : new BigDecimal(ZERO);
			
			totalMarketValue = totalMarketValue.add(marketBaseValue);
		}
		
		return totalMarketValue;
	}
	
}