package com.rbc.rbcone.position.dashboard.service;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbc.rbcone.position.dashboard.model.Account;
import com.rbc.rbcone.position.dashboard.model.Holding;
import com.rbc.rbcone.position.dashboard.repo.AccountRepository;
import com.rbc.rbcone.position.dashboard.repo.HoldingRepository;
import com.rbc.rbcone.position.dashboard.rest.AccountDTO;
import com.rbc.rbcone.position.dashboard.rest.AccountHoldingDTO;
import com.rbc.rbcone.position.dashboard.rest.HoldingDTO;

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
		dto.setMajorSecurityTypeTotalMarketValue(calculateTotalMarketValueByMajorSecurityType(holdings));
		dto.setHoldings(toDTOs(holdings));
		
		return dto;
	}

	private List<HoldingDTO> toDTOs(List<Holding> holdings) {
		List<HoldingDTO> holdingDTOs = new ArrayList<>();
		for (Holding holding : holdings) {
			holdingDTOs.add(toDTO(holding));
		}
		
		return holdingDTOs;
	}

	private HoldingDTO toDTO(Holding holding) {
		HoldingDTO dto = new HoldingDTO();

		dto.setAccountNumber(holding.getAccountNumber());
		dto.setBookBaseValue(holding.getBookBaseValue());
		dto.setCountryOfIssuer(holding.getCountryOfIssuer());
		dto.setCusip(holding.getCusip());
		dto.setIndustry(holding.getIndustry());
		dto.setIsin(holding.getIsin());
		dto.setMajorSecurityType(holding.getMajorSecurityType());
		dto.setMarketBaseValue(holding.getMarketBaseValue());
		dto.setMinorSecurityType(holding.getMinorSecurityType());
		dto.setPortfolioCurrency(holding.getPortfolioCurrency());
		dto.setPrice(holding.getPrice());
		dto.setSecurityDescription(holding.getSecurityDescription());
		dto.setSedol(holding.getSedol());
		dto.setUnits(holding.getUnits());
		
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

	Map<String, Map<String, BigDecimal>> calculateTotalMarketValueByMinorSecurityType(List<Holding> holdings) {
        return holdings.stream()
                .collect(Collectors.groupingBy(Holding::getMajorSecurityType))
                .entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> calculateTotalMarketValue(e.getValue(), Holding::getMinorSecurityType)));
	}

	Map<String, BigDecimal> calculateTotalMarketValueByMajorSecurityType(List<Holding> holdings) {
        return calculateTotalMarketValue(holdings, Holding::getMajorSecurityType);
	}

	private <P> Map<P, BigDecimal> calculateTotalMarketValue(List<Holding> holdings, Function<? super Holding, ? extends P> groupByFunc) {
        return holdings.stream()
                .collect(Collectors.groupingBy(groupByFunc,
                        Collectors.reducing(BigDecimal.ZERO, Holding::getMarketBaseValue, BigDecimal::add)));
	}

}