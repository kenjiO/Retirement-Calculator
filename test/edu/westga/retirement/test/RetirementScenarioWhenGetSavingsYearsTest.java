package edu.westga.retirement.test;


import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;

import edu.westga.retirement.model.RetirementScenario;
import edu.westga.retirement.model.SavingsYear;

/**
 * Test the RetirementScenario.getSavingsYears() method
 * @author Kenji Okamoto
 * @version 20151124
 *
 */
public class RetirementScenarioWhenGetSavingsYearsTest {


    /**
     * Test getSavingsYears returns an empty list when already retired
     */
    @Test
    public void testgetSavingsYearsReturnsEmptyListWhenAlreadyRetired() {
        RetirementScenario testScenario = new RetirementScenario(65, 65, 10000, 500, .06);
        List<SavingsYear> result = testScenario.getSavingsYears();
        assertEquals(0, result.size());
    }

    /**
     * Test getSavingsYears gets 1 year of Data 1 year before retirement
     */
    @Test
    public void testgetSavingsYearsWhen1YearBeforeRetirementReturns1Year() {
        RetirementScenario testScenario = new RetirementScenario(64, 65, 10000, 500, .06);
        List<SavingsYear> result = testScenario.getSavingsYears();
        assertEquals(1, result.size());
    }

    /**
     * Test getSavingsYears gets accurate data for 1 year before retirement
     */
    @Test
    public void testgetSavingsYearsWhenOneYearBeforeRetirementHasAccurateData() {
        RetirementScenario testScenario = new RetirementScenario(64, 65, 10000, 297, .06);
        SavingsYear year = testScenario.getSavingsYears().get(0);
        int expectedAppreciation = (int) (10000 * .06);
        int expectedEndBalance = 10000 + 297 + expectedAppreciation;
        assertEquals(64, year.getAge());
        assertEquals(10000, year.getBeginBalance());
        assertEquals(297, year.getContribution());
        assertEquals(expectedAppreciation, year.getAppreciation());
        assertEquals(expectedEndBalance, year.getEndBalance());
    }

    /**
     * Test getSavingsYears gets 3 years of Data 3 years before retirement
     */
    @Test
    public void testgetSavingsYearsWhen3YearBeforeRetirementReturns3Years() {
        RetirementScenario testScenario = new RetirementScenario(62, 65, 10000, 500, .06);
        List<SavingsYear> result = testScenario.getSavingsYears();
        assertEquals(3, result.size());
    }

    /**
     * Test getSavingsYears gets accurate data for the third year of savings
     */
    @Test
    public void testgetSavingsYearsGetsAccurateDataFor3rdYear() {
        RetirementScenario testScenario = new RetirementScenario(62, 65, 10000, 297, .06);
        SavingsYear firstYear = new SavingsYear(62, 10000, 297, .06);
        SavingsYear expected = firstYear.getNextYear().getNextYear();
        SavingsYear actual = testScenario.getSavingsYears().get(2);
        assertEquals(expected.getAge(), actual.getAge());
        assertEquals(expected.getBeginBalance(), actual.getBeginBalance());
        assertEquals(expected.getContribution(), actual.getContribution());
        assertEquals(expected.getAppreciation(), actual.getAppreciation());
        assertEquals(expected.getEndBalance(), actual.getEndBalance());
    }

    /**
     * Test list returned by getSavingsYears can't modify the RetirementScenario
     */
    @Test
    public void testgetSavingsYearsReturnsListThatCannotModifyTheOriginal() {
        RetirementScenario testScenario = new RetirementScenario(62, 65, 10000, 500, .06);
        assertEquals(true, testScenario.getSavingsYears().size() > 0);
        testScenario.getSavingsYears().clear();
        assertEquals(true, testScenario.getSavingsYears().size() > 0);
    }
}
