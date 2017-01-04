package com.rbc.rbcone.position.dashboard.model;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

/**
 * Created by wayneyu on 1/4/17.
 */
@Entity
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="account_number", nullable=false)
    public final String accountNumber;

    @Column(name="account_name")
    public final String accountName;

    public Account(String accountNumber, String accountName) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
    }
}
