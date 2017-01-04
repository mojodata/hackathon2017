package com.rbc.rbcone.position.dashboard.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * Created by wayneyu on 1/4/17.
 */
@Entity
public class Holding implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "holding_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="holding_seq_gen")
	@SequenceGenerator(name="holding_seq_gen", sequenceName="seq_holding")
	private Long id;

	@Column(name = "account_number", nullable = false)
	private String accountNumber;

	@Column(name = "portfolio_currency", nullable = false)
	private String portfolioCurrency;

	@Column(name = "report_date")
	private Date reportDate;

	@Column(name = "country_of_issuer")
	private String countryOfIssuer;

	@Column(name = "major_security_type")
	private String majorSecurityType;

	@Column(name = "minor_security_type")
	private String minorSecurityType;

	@Column(name = "industry")
	private String industry;

	@Column(name = "security_identifier")
	private String securityId;

	@Column(name = "cusip_seqcurity_number")
	private String cusip;

	@Column(name = "security_number_isin")
	private String isin;

	@Column(name = "sedol_security_number")
	private String sedol;

	@Column(name = "long_security_description")
	private String securityDescription;

	@Column(name = "units")
	private BigDecimal units;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "book_base_value")
	private BigDecimal bookBaseValue;

	@Column(name = "market_base_value")
	private BigDecimal marketBaseValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
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
	
	

}