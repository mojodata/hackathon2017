package com.rbc.rbcone.position.dashboard.rest;

import java.io.Serializable;
import java.math.BigDecimal;

public class CountryMarketValueDTO implements Serializable, Comparable<CountryMarketValueDTO> {
	private static final long serialVersionUID = 1L;

	private BigDecimal totalMarketValue;
	private String countryCode;
	private int rank;

	public CountryMarketValueDTO(BigDecimal totalMarketValue, int rank, String countryCode) {
		super();
		this.totalMarketValue = totalMarketValue;
		this.countryCode = countryCode;
		this.rank = rank;
	}

	public BigDecimal getTotalMarketValue() {
		return totalMarketValue;
	}

	public void setTotalMarketValue(BigDecimal totalMarketValue) {
		this.totalMarketValue = totalMarketValue;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
    public int compareTo(CountryMarketValueDTO another) {
		if (this.getTotalMarketValue() == null || another.getTotalMarketValue() == null) {
			return 0;
		}
		return another.getTotalMarketValue().compareTo(this.getTotalMarketValue());
    }

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
}
