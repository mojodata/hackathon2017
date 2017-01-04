package com.rbc.rbcone.position.dashboard.model;

import org.springframework.data.annotation.Id;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by wayneyu on 1/4/17.
 */
@Entity
public class Holding {

    @Id
    @Column(name="holding_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="account_number", nullable=false)
    @ManyToOne
    public final String accountNumber;

    @Column(name="portfolio_currency", nullable=false)
    public final String portfolioCurrency;

    @Column(name="report_date")
    public final Date reportDate;

    @Column(name="country_of_issuer")
    public final String countryOfIssuer;

    @Column(name="major_security_type")
    public final String majorSecurityType;

    @Column(name="minor_security_type")
    public final String minorSecurityType;

    @Column(name="industry")
    public final String industry;

    @Column(name="security_identifier")
    public final String securityId;

    @Column(name="cusip_seqcurity_number")
    public final String cusip;

    @Column(name="security_number_isin")
    public final String isin;

    @Column(name="sedol_security_number")
    public final String sedol;

    @Column(name="long_security_description")
    public final String securityDescription;

    @Column(name="units")
    public final double units;

    @Column(name="price")
    public final double price;

    @Column(name="book_base_value")
    public final double bookBaseValue;

    @Column(name="market_base_value")
    public final double marketBaseValue;

    public Holding(String accountNumber, String portfolioCurrency, Date reportDate, String countryOfIssuer,
                   String majorSecurityType, String minorSecurityType, String industry, String securityId, String cusip,
                   String isin, String sedol, String securityDescription, double units, double bookBaseValue,
                   double marketBaseValue, double price) {
        this.accountNumber = accountNumber;
        this.portfolioCurrency = portfolioCurrency;
        this.reportDate = reportDate;
        this.countryOfIssuer = countryOfIssuer;
        this.majorSecurityType = majorSecurityType;
        this.minorSecurityType = minorSecurityType;
        this.industry = industry;
        this.securityId = securityId;
        this.cusip = cusip;
        this.isin = isin;
        this.sedol = sedol;
        this.securityDescription = securityDescription;
        this.units = units;
        this.price = price;
        this.bookBaseValue = bookBaseValue;
        this.marketBaseValue = marketBaseValue;
    }
}