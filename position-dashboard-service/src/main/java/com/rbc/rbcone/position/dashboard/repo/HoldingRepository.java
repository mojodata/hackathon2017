package com.rbc.rbcone.position.dashboard.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rbc.rbcone.position.dashboard.model.Holding;

@Repository
public interface HoldingRepository extends JpaRepository<Holding, Long> {
	
    List<Holding> findByAccountNumber(String accountNumber);

    List<Holding> findByAccountNumberAndCountryOfIssuer(String accountNumber, String countryOfIssuer);

}
