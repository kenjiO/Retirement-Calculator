package edu.westga.retirement.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.westga.retirement.model.SavingsYear;

/**
 * Test the SavingsYear class getNextYear method
 * @author Kenji Okamoto
 * @version 20151120
 *
 */
public class SavingsYearGetNextYearTest {

    /**
     * Test getNextYear creates a new year using this year's end balance
     */
    @Test
    public void testGetNextYearHasBeginBalanceEqualToCurrentEndBalance() {
        SavingsYear previousYear = new SavingsYear(5000, 2000, .06);
        SavingsYear testYear = previousYear.getNextYear();
        assertEquals(previousYear.getEndBalance(), testYear.getBeginBalance());
    }

    /**
     * Test getNextYear will use the same appreciation rate
     */
    @Test
    public void testGetNextYearUsesSameAppreciationRate() {
        SavingsYear previousYear = new SavingsYear(1000, 0, .1);
        SavingsYear testYear = previousYear.getNextYear();
        assertEquals(110, testYear.getAppreciation());
    }

    /**
     * Test getNextYear uses the same contribution amount
     */
    @Test
    public void testGetNextYearUsesSameContribution() {
        SavingsYear previousYear = new SavingsYear(1000, 97, 0);
        SavingsYear testYear = previousYear.getNextYear();
        assertEquals(97, testYear.getContribution());
    }

    /**
     * Test getNextYear uses the same contribution amount when calculating end balance
     */
    @Test
    public void testGetNextYearUsesSameContributionInCalculations() {
        SavingsYear previousYear = new SavingsYear(1000, 100, 0);
        SavingsYear testYear = previousYear.getNextYear();
        assertEquals(1200, testYear.getEndBalance());
    }

    /**
     * Test getNextYear will use the same appreciationRate and contribution
     */
    @Test
    public void testGetNextYearUsesSameAppreciationRateAndContribution() {
        SavingsYear previousYear = new SavingsYear(1839, 237, .07);
        SavingsYear testYear = previousYear.getNextYear();
        assertEquals(2595, testYear.getEndBalance());
    }


}
