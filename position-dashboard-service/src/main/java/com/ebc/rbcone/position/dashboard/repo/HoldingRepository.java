package com.ebc.rbcone.position.dashboard.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rbc.rbcone.position.dashboard.model.Holding;

@Repository
public interface HoldingRepository extends JpaRepository<Holding, Long> {
	
    List<Holding> getHoldingsByAccountNumber(String accountNumber);
    
    List<Holding> getHoldingsByAccountNumberAndCountryOfIssuer(String accountNumber, String countryOfIssuer);
    
}
