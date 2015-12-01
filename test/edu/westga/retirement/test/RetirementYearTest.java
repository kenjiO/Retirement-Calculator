package edu.westga.retirement.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.westga.retirement.model.RetirementYear;

/**
 * Test class for the RetirementYear class
 * @author Kenji Okamoto
 * @version 20151201
 *
 */
public class RetirementYearTest {

    /**
     * Test getBeginBalance is the same as the paramater passed in the constructor
     */
    @Test
    public void testInitialYearStartBalanceEqualsParamater() {
        RetirementYear testYear = new RetirementYear(80, 95724, 40500, 10000, 7.5);
        assertEquals(95724, testYear.getBeginBalance());
    }

    /**
     * Test getWithdrawal() is the same as the paramater passed in the constructor
     */
    @Test
    public void testgetWithdrawalIsSameAsConstructorParamter() {
        RetirementYear testYear = new RetirementYear(80, 95724, 40500, 10000, 7.5);
        assertEquals(40500, testYear.getWithdrawal());
    }


    /**
     * Test that the withdrawal is factored into the end balance
     */
    @Test
    public void testWithdrawalIsFactoredIntoTheEndBalance() {
        RetirementYear testYear = new RetirementYear(80, 5000, 1000, 0, 0.0);
        assertEquals(4000, testYear.getEndBalance());

    }

    /**
     * Test that a 0 withdrawal, 0 social security and 0 appreciation does not affect the balance
     */
    @Test
    public void test0Withdrawal0SocialSecurity0AppreciationDoesNotIncreaseBalance() {
        RetirementYear testYear = new RetirementYear(80, 87365, 0, 0, 0.0);
        assertEquals(87365, testYear.getEndBalance());

    }

    /**
     * Test getAppreciation returns begin balance * appreciationRate
     */
    @Test
    public void testGetAppreciationIsBeginBalnceTimesAppreciationRate() {
        RetirementYear testYear = new RetirementYear(80, 5000, 0, 0, .06);
        assertEquals(5000 * .06, testYear.getAppreciation(), 00001);
    }

    /**
     * Test getAppreciation is 0 when the appreciationRate is 0
     */
    @Test
    public void testGetAppreciationIs0WhenAppreciationRateIs0() {
        RetirementYear testYear = new RetirementYear(80, 5000, 0, 0, 0.0);
        assertEquals(0, testYear.getAppreciation());
    }

    /**
     * Test getAppreciation is 0 when begin balance is 0
     */
    @Test
    public void testGetAppreciationWhenBeginBalanceIs0Is0() {
        RetirementYear testYear = new RetirementYear(80, 0, 0, 0, .06);
        assertEquals(0, testYear.getAppreciation());
    }

    /**
     * Test appreciation is factored into the end Balance
     */
    @Test
    public void testAppreciationIsFactoredIntoTheEndBalance() {
        RetirementYear testYear = new RetirementYear(80, 5000, 0, 0, .06);
        assertEquals(testYear.getBeginBalance() + testYear.getAppreciation(), testYear.getEndBalance());
    }

    /**
     * Test social security amount is 0 when the age is less than social security start age
     */
    @Test
    public void testSocialSecurityAmountIs0WhenAgeIsLessThanSocialSecurityStartAge() {
        for (int age = 1; age < RetirementYear.SOCIAL_SECURITY_START_AGE; age++) {
            RetirementYear testYear = new RetirementYear(age, 5000, 0, 1000, 0.0);
            assertEquals(0, testYear.getSocialSecurity());
        }
    }

    /**
     * Test social security amount is expected social security when at or above social security start age
     */
    @Test
    public void testSocialSecurityAmountIsSameAsExpectedWhenAgeIsAtOrAboveSocialSecurityStartAge() {
        for (int age = RetirementYear.SOCIAL_SECURITY_START_AGE; age < 120; age++) {
            RetirementYear testYear = new RetirementYear(age, 5000, 0, 1000, 0.0);
            assertEquals(1000, testYear.getSocialSecurity());
        }
    }
    
    /**
     * Test the end balance can be negative if there is not enough savings and income
     */
    @Test
    public void testEndBalanceCanBeNegativeWhenNotEnoughSavingsAndIncome() {
        RetirementYear testYear = new RetirementYear(80, 5000, 30000, 10000, .1);
        assertEquals(-14500, testYear.getEndBalance());
    }
    
    /**
     * Test that when beginBalance is negative the endBalance can still decrease
     */

    @Test
    public void testWhenBeginBalanceIsNegativeEndBalanceCanDecreaseMore() {
        RetirementYear testYear = new RetirementYear(80, -5000, 30000, 12000, .1);
        assertEquals(-23000, testYear.getEndBalance());
    }
    
    /**
     * Test when begin balance is negative there is no appreciation
     */
    @Test
    public void testWhenBeginBalanceIsNegativeThereIsNoAppreciation() {
        RetirementYear testYear = new RetirementYear(80, -5000, 30000, 10000, .1);
        assertEquals(0, testYear.getAppreciation());
    }
}
