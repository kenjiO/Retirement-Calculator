package edu.westga.retirement.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.westga.retirement.model.RetirementYear;

/**
 * Test the RetirementYear class getNextYear method
 * @author Kenji Okamoto
 * @version 20151201
 *
 */
public class RetirementYearGetNextYearTest {

    /**
     * Test getNextYear creates a new year using this year's end balance
     */
    @Test
    public void testGetNextYearHasBeginBalanceEqualToCurrentEndBalance() {
        RetirementYear previousYear = new RetirementYear(80, 200000, 40000, 12000, .06);
        RetirementYear testYear = previousYear.getNextYear();
        assertEquals(previousYear.getEndBalance(), testYear.getBeginBalance());
    }

    /**
     * Test getNextYear will use the same appreciation rate
     */
    @Test
    public void testGetNextYearUsesSameAppreciationRate() {
        RetirementYear previousYear = new RetirementYear(80, 200000, 40000, 12000, .06);
        RetirementYear testYear = previousYear.getNextYear();
        int appreciation = (int) (testYear.getBeginBalance() * .06);
        assertEquals(appreciation, testYear.getAppreciation());
    }

    /**
     * Test getNextYear uses the same withdrawal amount
     */
    @Test
    public void testGetNextYearUsesSameWithdrawal() {
        RetirementYear previousYear = new RetirementYear(80, 200000, 40000, 12000, .06);
        RetirementYear testYear = previousYear.getNextYear();
        assertEquals(40000, testYear.getWithdrawal());
    }

    /**
     * Test getNextYear uses the same withdrawal amount when calculating end balance
     */
    @Test
    public void testGetNextYearUsesSameWithdrawalAmountInCalculations() {
        RetirementYear previousYear = new RetirementYear(80, 200000, 40000, 0, 0.0);
        RetirementYear testYear = previousYear.getNextYear();
        assertEquals(120000, testYear.getEndBalance());
    }

    /**
     * Test getNextYear will return a year with age incremented
     */
    @Test
    public void testGetNextYearReturnsNextYearWithAgeIncremented() {
        RetirementYear previousYear = new RetirementYear(80, 200000, 40000, 12000, .06);
        RetirementYear testYear = previousYear.getNextYear();
        assertEquals(81, testYear.getAge());
    }

    /**
     * Test getNextYear will have the same social security amount when
     * over the social security start age
     */
    @Test
    public void testGetNextYearHasSameSocialSecurity() {
        int age = RetirementYear.SOCIAL_SECURITY_START_AGE;
        RetirementYear previousYear = new RetirementYear(age, 200000, 40000, 12000, .06);
        RetirementYear testYear = previousYear.getNextYear();
        assertEquals(12000, testYear.getSocialSecurity());
    }

}
