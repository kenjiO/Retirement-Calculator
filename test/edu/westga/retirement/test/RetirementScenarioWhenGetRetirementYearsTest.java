package edu.westga.retirement.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import edu.westga.retirement.model.RetirementScenario;
import edu.westga.retirement.model.RetirementYear;
import edu.westga.retirement.model.SavingsYear;

/**
 * Test the RetirementScenario.getRetirementYears() method
 * @author Kenji Okamoto
 * @version 20151201
 *
 */
public class RetirementScenarioWhenGetRetirementYearsTest {

    /**
     * Test retirement years will start at the specified retirement age
     */
    @Test
    public void testRetirementYearsStartsAtTheSpecifiedRetireAge() {
        RetirementScenario testScenario = new RetirementScenario(50, 61, 100000, 500, .06, 12000, 40000);
        assertEquals(61, testScenario.getRetirementYears().get(0).getAge());
    }

    /**
     * Test the first year in retirement years will use end balance from the last saving year
     */
    @Test
    public void testRetirementYearsFirstYearUsesSavingsYearLastYearData() {
        RetirementScenario testScenario = new RetirementScenario(50, 65, 100000, 500, .06, 12000, 40000);
        List<SavingsYear> savingsYears = testScenario.getSavingsYears();
        SavingsYear lastSavingYear = savingsYears.get(savingsYears.size() - 1);
        assertEquals(lastSavingYear.getEndBalance(), testScenario.getRetirementYears().get(0).getBeginBalance());
    }

    /**
     * Test the appreciation rate used is that specified in constructor
     */
    @Test
    public void testRetirementYearsUsesAppreciationRateSpecifiedInConstructor() {
        double appreciationRate = .06;
        RetirementScenario testScenario = new RetirementScenario(67, 68, 100000, 0, appreciationRate, 0, 0);
        RetirementYear year = testScenario.getRetirementYears().get(0);
        int appreciation = (int) (year.getBeginBalance() * appreciationRate);
        assertEquals(appreciation, year.getAppreciation());
    }

    /**
     * Test the social security amount is the amount specified in the constructor
     */
    @Test
    public void testRetirementYearsUsesSocialSecuritySpecifiedInConstructor() {
        int socialSecurity = 13143;
        RetirementScenario testScenario = new RetirementScenario(RetirementYear.SOCIAL_SECURITY_START_AGE, 65, 0, 0, 0.0, socialSecurity, 0);
        for (RetirementYear year :  testScenario.getRetirementYears()) {
            assertEquals(socialSecurity, year.getSocialSecurity());
        }
    }

    /**
     * Test the withdrawal amount is the amount specified in the constructor
     */
    @Test
    public void testRetirementYearsUsesWithdrawalAmountSpecifiedInConstructor() {
        int withdrawal = 23468;
        RetirementScenario testScenario = new RetirementScenario(80, 65, 0, 0, 0.0, 0, withdrawal);
        for (RetirementYear year :  testScenario.getRetirementYears()) {
            assertEquals(withdrawal, year.getWithdrawal());
        }
    }

    /**
     * Test the data generated in the scenario is accurate
     */
    @Test
    public void testDataGeneratedIntheScenarioIsAccurate() {
        RetirementScenario testScenario = new RetirementScenario(65, 67, 300000, 5000, 0.1, 13000, 64000);
        List<SavingsYear> workingYears = testScenario.getSavingsYears();
        List<RetirementYear> retiredYears = testScenario.getRetirementYears();
        assertEquals(335000, workingYears.get(0).getEndBalance());
        assertEquals(373500, workingYears.get(1).getEndBalance());
        assertEquals(346850, retiredYears.get(0).getEndBalance());
        assertEquals(330535, retiredYears.get(1).getEndBalance());
        assertEquals(312588, retiredYears.get(2).getEndBalance());
    }
}
