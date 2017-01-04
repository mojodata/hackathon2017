package com.rbc.rbcone.position.dashboard.service;

import com.rbc.rbcone.position.dashboard.model.Holding;
import com.rbc.rbcone.position.dashboard.model.Security;

import java.util.List;

/**
 * Created by wayneyu on 1/4/17.
 */
public interface HoldingDAO {

    List<Holding> getHoldingsByAccountNumber(String accountNumber);

    List<Security> getSecuritiesByAccountNumberAndCountry(String accountNumber, String country);

    List<Security> getSecuritiesByAccountNumberAndMajorAndMinorAndIndustry(String accountNumber, String majorSecurityType,
                                                                           String minorSecurityType, String industry);
}
