package com.rbc.rbcone.position.dashboard.rest;

import java.io.Serializable;

public class AccountDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private String accountNumber;
    private String accountName;

	public AccountDTO(String accountNumber, String accountName) {
		super();
		this.accountNumber = accountNumber;
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
}
