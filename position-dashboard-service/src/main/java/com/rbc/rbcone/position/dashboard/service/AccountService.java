package com.rbc.rbcone.position.dashboard.service;

import java.util.List;

import com.rbc.rbcone.position.dashboard.rest.AccountDTO;
import com.rbc.rbcone.position.dashboard.rest.AccountHoldingDTO;

public interface AccountService {
	List<AccountDTO> getUserAccounts();

	AccountHoldingDTO getHoldings(String accountNumber);

    AccountHoldingDTO getHoldings(String accountNumber, String country);

}