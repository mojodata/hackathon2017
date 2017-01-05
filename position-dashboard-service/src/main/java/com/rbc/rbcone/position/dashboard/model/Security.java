package com.rbc.rbcone.position.dashboard.model;

import java.math.BigDecimal;

/**
 * Created by wayneyu on 1/4/17.
 */
public class Security {
    private String currency;
    private BigDecimal marketValue;
    private String description;

    public Security(String currency, BigDecimal marketValue, String description) {
        this.currency = currency;
        this.marketValue = marketValue;
        this.description = description;
    }
}
