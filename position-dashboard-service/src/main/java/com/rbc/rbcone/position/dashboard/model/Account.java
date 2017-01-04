package com.rbc.rbcone.position.dashboard.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Created by wayneyu on 1/4/17.
 */
@Entity
@Table(name = "account")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="account_seq_gen")
	@SequenceGenerator(name="account_seq_gen", sequenceName="seq_account")
	private Long id;

    @Column(name="account_number", nullable=false)
    private String accountNumber;

    @Column(name="account_name")
    private String accountName;

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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
}
