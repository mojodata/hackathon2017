package com.rbc.rbcone.position.dashboard.service;

import com.rbc.rbcone.position.dashboard.model.Holding;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by wayneyu on 1/4/17.
 */
public class AccountServiceImplTest {

    private static final Holding HOLDING_1 = new Holding();
    private static final Holding HOLDING_2 = new Holding();
    private static final Holding HOLDING_3 = new Holding();

    private AccountServiceImpl fixture;

    @Before
    public void setup(){
        HOLDING_1.setMarketBaseValue(new BigDecimal(10));
        HOLDING_2.setMarketBaseValue(new BigDecimal(20));
        HOLDING_3.setMarketBaseValue(new BigDecimal(40));
        HOLDING_1.setMajorSecurityType("major1");
        HOLDING_2.setMajorSecurityType("major1");
        HOLDING_3.setMajorSecurityType("major2");
        HOLDING_1.setMinorSecurityType("minor1");
        HOLDING_2.setMinorSecurityType("minor1");
        HOLDING_3.setMinorSecurityType("minor2");

        fixture = new AccountServiceImpl();
    }

    @Test
    public void shouldCalculateMarketValueOfHoldingsGroupedBySuppliedGroup() {
        List<Holding> holdings = Arrays.asList(HOLDING_1, HOLDING_2, HOLDING_3);
        Map<String, BigDecimal> expected = new HashMap<>();

        expected.put("major1", BigDecimal.valueOf(30));
        expected.put("major2", BigDecimal.valueOf(40));

        assertEquals(expected, fixture.calculateTotalMarketValueByMajorSecurityType(holdings));
    }

    @Test
    public void shouldCalculateMarketValueOfHoldingsGroupedByMajorTypeAndMinorType() {
        List<Holding> holdings = Arrays.asList(HOLDING_1, HOLDING_2, HOLDING_3);
        Map<String, Map<String, BigDecimal>> expected = new HashMap<>();
        Map<String, BigDecimal> expectedMinor1 = new HashMap<>();
        Map<String, BigDecimal> expectedMinor2 = new HashMap<>();

        expectedMinor1.put("minor1", BigDecimal.valueOf(30));
        expectedMinor2.put("minor2", BigDecimal.valueOf(40));
        expected.put("major1", expectedMinor1);
        expected.put("major2", expectedMinor2);

        assertEquals(expected, fixture.calculateTotalMarketValueByMinorSecurityType(holdings));
    }
}
