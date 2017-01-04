package com.rbc.rbcone.position.dashboard.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

	@Override
	public List<String> getUserAccounts() {
		return Arrays.asList("account1", "account2");
	}
	
}