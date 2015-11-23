package edu.westga.retirement.test;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import edu.westga.retirement.model.SavingsYear;

/**
 * Test class for the SavingsYear class
 * @author Kenji Okamoto
 * @version 20151120
 *
 */
public class SavingsYearTest {

    /**
     * Test getBeginBalance is the same as the paramater passed in the constructor
     */
    @Test
    public void testInitialYearStartBalanceEqualsParamater() {
        SavingsYear testYear = new SavingsYear(40, 95724, 1000, 7.5);
        assertEquals(95724, testYear.getBeginBalance());
    }

    /**
     * Test getContribution() is the same as the paramater passed in the constructor
     */
    @Test
    public void testgetContributionIsSameAsConstructorParamter() {
        SavingsYear testYear = new SavingsYear(40, 100000, 8374, 0);
        assertEquals(8374, testYear.getContribution());
    }


    /**
     * Test that the contrubution is factored in to the end balance
     */
    @Test
    public void testContributionIsFactoredIntoTheEndBalance() {
        SavingsYear testYear = new SavingsYear(40, 5000, 1000, 0);
        assertEquals(6000, testYear.getEndBalance());

    }

    /**
     * Test that a 0 contibution does not increase the balance
     */
    @Test
    public void test0ContributionDoesNotIncreaseBalance() {
        SavingsYear testYear = new SavingsYear(40, 5000, 0, 0);
        assertEquals(5000, testYear.getEndBalance());

    }

    /**
     * Test that 0 contrubition and 0 appreciation rate does not change balance
     */
    @Test
    public void test0ContributionAnd0AppreciationRateDoesNotChangeBalance() {
        SavingsYear testYear = new SavingsYear(40, 5000, 0, 0);
        assertEquals(5000, testYear.getEndBalance());

    }

    /**
     * Test getAppreciation returns begin balance * appreciationRate
     */
    @Test
    public void testGetAppreciationIsBeginBalnceTimesAppreciationRate() {
        SavingsYear testYear = new SavingsYear(40, 5000, 1000, .06);
        assertEquals(300, testYear.getAppreciation());
    }

    /**
     * Test getAppreciation is 0 when the appreciationRate is 0
     */
    @Test
    public void testGetAppreciationIs0WhenAppreciationRateIs0() {
        SavingsYear testYear = new SavingsYear(40, 5000, 1000, 0);
        assertEquals(0, testYear.getAppreciation());
    }

    /**
     * Test getAppreciation is 0 when begin balance is 0
     */
    @Test
    public void testGetAppreciationWhenBeginBalanceIs0Is0() {
        SavingsYear testYear = new SavingsYear(40, 0, 1000, .06);
        assertEquals(0, testYear.getAppreciation());
    }

    /**
     * Test appreciation is factored into the end Balance
     */
    @Test
    public void testAppreciationIsFactoredIntoTheEndBalance() {
        SavingsYear testYear = new SavingsYear(40, 5000, 0, .06);
        assertEquals(5300, testYear.getEndBalance());
    }

    /**
     * Test getValuesMap can get the age from the SavingsYear
     */
    @Test
    public void testGetValuesMapGetsAgeFromTheSavingsYear() {
        SavingsYear testYear = new SavingsYear(43, 95724, 398, 7.5);
        Map<String, Integer> map = testYear.getMappedValues();
        assertEquals(43, map.get("age").intValue());
    }

    /**
     * Test getValuesMap can get the beginBalance from the SavingsYear
     */
    @Test
    public void testGetValuesMapGetsBeginBalanceFromTheSavingsYear() {
        SavingsYear testYear = new SavingsYear(43, 95724, 398, 7.5);
        Map<String, Integer> map = testYear.getMappedValues();
        assertEquals(95724, map.get("beginBalance").intValue());
    }
    
    /**
     * Test getValuesMap can get the contribution from the SavingsYear
     */
    @Test
    public void testGetValuesMapGetsContributionFromTheSavingsYear() {
        SavingsYear testYear = new SavingsYear(43, 95724, 398, 7.5);
        Map<String, Integer> map = testYear.getMappedValues();
        assertEquals(398, map.get("contribution").intValue());
    }
    
    /**
     * Test getValuesMap can get the appreciation from the SavingsYear
     */
    @Test
    public void testGetValuesMapGetsAppreciationFromTheSavingsYear() {
        SavingsYear testYear = new SavingsYear(43, 95724, 398, 7.5);
        Map<String, Integer> map = testYear.getMappedValues();
        assertEquals(testYear.getAppreciation(), map.get("appreciation").intValue());
    }
    
    /**
     * Test getValuesMap can get the endBalance from the SavingsYear
     */
    @Test
    public void testGetValuesMapGetsEndBalanceFromTheSavingsYear() {
        SavingsYear testYear = new SavingsYear(43, 95724, 398, 7.5);
        Map<String, Integer> map = testYear.getMappedValues();
        assertEquals(testYear.getEndBalance(), map.get("endBalance").intValue());
    }
    
    /**
     * Test getValuesMap returns an unmodifiable map
     */
    @Test
    public void testGetValuesMapReturnsUnmodifiableMap() {
        SavingsYear testYear = new SavingsYear(43, 95724, 398, 7.5);
        Map<String, Integer> map = testYear.getMappedValues();
        try {
        	map.put("age", 55);
        	fail("UnspportedOperationException not thrown");
        } catch (UnsupportedOperationException exception) {
        }
    }

}
