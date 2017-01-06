package com.rbc.rbcone.position.dashboard.rest;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryMarketValueDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal totalMarketValue;
	private int rank;

	public CountryMarketValueDTO(BigDecimal totalMarketValue, int rank) {
		super();
		this.totalMarketValue = totalMarketValue;
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
}
