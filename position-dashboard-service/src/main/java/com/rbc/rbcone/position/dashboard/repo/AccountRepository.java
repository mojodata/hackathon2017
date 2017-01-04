package com.rbc.rbcone.position.dashboard.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rbc.rbcone.position.dashboard.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
