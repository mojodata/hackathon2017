package com.rbc.rbcone.position.dashboard.rest;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountHoldingDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private BigDecimal totalMarketValue;
	private Map<String, BigDecimal> countryTotalMarketValue = new HashMap<>();
	private Map<String, BigDecimal> majorSecurityTypeTotalMarketValue = new HashMap<>();
	private Map<String, Map<String, BigDecimal>> minorSecurityTypeTotalMarketValue = new HashMap<>();
	private List<HoldingDTO> holdings = new ArrayList<>();

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

	public Map<String, BigDecimal> getMajorSecurityTypeTotalMarketValue() {
		return majorSecurityTypeTotalMarketValue;
	}

	public void setMajorSecurityTypeTotalMarketValue(Map<String, BigDecimal> majorSecurityTypeTotalMarketValue) {
		this.majorSecurityTypeTotalMarketValue = majorSecurityTypeTotalMarketValue;
	}

	public Map<String, Map<String, BigDecimal>> getMinorSecurityTypeTotalMarketValue() {
		return minorSecurityTypeTotalMarketValue;
	}

	public void setMinorSecurityTypeTotalMarketValue(Map<String, Map<String, BigDecimal>> minorSecurityTypeTotalMarketValue) {
		this.minorSecurityTypeTotalMarketValue = minorSecurityTypeTotalMarketValue;
	}

	public List<HoldingDTO> getHoldings() {
		return holdings;
	}

	public void setHoldings(List<HoldingDTO> holdings) {
		this.holdings = holdings;
	}
}
