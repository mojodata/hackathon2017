package com.rbc.rbcone.position.dashboard.rest;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class AccountHoldingDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private BigDecimal totalMarketValue;
	private Map<String, BigDecimal> countryTotalMarketValue = new HashMap<>();

	public BigDecimal getTotalMarketValue() {
		return totalMarketValue;
	}

	public void setTotalMarketValue(BigDecimal totalMarketValue) {
		this.totalMarketValue = totalMarketValue;
	}

	public Map<String, BigDecimal> getCountryTotalMarketValue() {
		return countryTotalMarketValue;
	}

	public void setCountryTotalMarketValue(Map<String, BigDecimal> countryTotalMarketValue) {
		this.countryTotalMarketValue = countryTotalMarketValue;
	}
}
