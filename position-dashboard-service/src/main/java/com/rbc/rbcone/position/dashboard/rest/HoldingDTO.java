package com.rbc.rbcone.position.dashboard.rest;

import java.io.Serializable;
import java.math.BigDecimal;

import com.rbc.rbcone.position.dashboard.model.Security;

public class HoldingDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String accountNumber;
	private String portfolioCurrency;
	private String countryOfIssuer;
	private String majorSecurityType;
	private String minorSecurityType;
	private String industry;
	private String securityId;
	private String cusip;
	private String isin;
	private String sedol;
	private String securityDescription;
	private BigDecimal units;
	private BigDecimal price;
	private BigDecimal bookBaseValue;
	private BigDecimal marketBaseValue;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getPortfolioCurrency() {
		return portfolioCurrency;
	}

	public void setPortfolioCurrency(String portfolioCurrency) {
		this.portfolioCurrency = portfolioCurrency;
	}

	public String getCountryOfIssuer() {
		return countryOfIssuer;
	}

	public void setCountryOfIssuer(String countryOfIssuer) {
		this.countryOfIssuer = countryOfIssuer;
	}

	public String getMajorSecurityType() {
		return majorSecurityType;
	}

	public void setMajorSecurityType(String majorSecurityType) {
		this.majorSecurityType = majorSecurityType;
	}

	public String getMinorSecurityType() {
		return minorSecurityType;
	}

	public void setMinorSecurityType(String minorSecurityType) {
		this.minorSecurityType = minorSecurityType;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getSecurityId() {
		return securityId;
	}

	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}

	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}

	public String getSedol() {
		return sedol;
	}

	public void setSedol(String sedol) {
		this.sedol = sedol;
	}

	public String getSecurityDescription() {
		return securityDescription;
	}

	public void setSecurityDescription(String securityDescription) {
		this.securityDescription = securityDescription;
	}

	public BigDecimal getUnits() {
		return units;
	}

	public void setUnits(BigDecimal units) {
		this.units = units;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getBookBaseValue() {
		return bookBaseValue;
	}

	public void setBookBaseValue(BigDecimal bookBaseValue) {
		this.bookBaseValue = bookBaseValue;
	}

	public BigDecimal getMarketBaseValue() {
		return marketBaseValue;
	}

	public void setMarketBaseValue(BigDecimal marketBaseValue) {
		this.marketBaseValue = marketBaseValue;
	}

    private Security toSecurity() {
        return new Security(portfolioCurrency, marketBaseValue, securityDescription);
    }
}
