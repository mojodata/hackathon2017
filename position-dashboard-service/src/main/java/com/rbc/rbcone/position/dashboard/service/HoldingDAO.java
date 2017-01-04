package com.rbc.rbcone.position.dashboard.service;

import com.rbc.rbcone.position.dashboard.model.Holding;

import java.util.List;

/**
 * Created by wayneyu on 1/4/17.
 */
public interface HoldingDAO {

    List<Holding> getHoldingsByAccountNumber(String accountNumber);

    List<Holding> getHoldingsByAccountNumberAndCountry(String accountNumber, String country);



}
