package edu.westga.retirement.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import edu.westga.retirement.model.RetirementScenario;
import edu.westga.retirement.model.SavingsYear;

/**
 * Test the RetirementScenario.getSavingsYearsData() method
 * @author Kenji Okamoto
 * @version 20151123
 *
 */
public class RetirementScenarioGetSavingsYearDataTest {


    /**
     * Test getSavingsYearsData returns an empty list when already retired
     */
    @Test
    public void testGetSavingsYearDataReturnsEmptyListWhenAlreadyRetired() {
        RetirementScenario testScenario = new RetirementScenario(65, 65, 10000, 500, .06);
        List<Map<String, Integer>> result = testScenario.getSavingsYearData();
        assertEquals(0, result.size());
    }
    
    /**
     * Test getSavingsYearsData gets 1 year of Data 1 year before retirement
     */
    @Test
    public void testGetSavingsYearDataWhen1YearBeforeRetirementReturns1Year() {
        RetirementScenario testScenario = new RetirementScenario(64, 65, 10000, 500, .06);
        List<Map<String, Integer>> result = testScenario.getSavingsYearData();
        assertEquals(1, result.size());
    }

    /**
     * Test getSavingsYearsData gets accurate data for 1 year before retirement
     */
    @Test
    public void testGetSavingsYearDataWhenOneYearBeforeRetirementHasAccurateData() {
        RetirementScenario testScenario = new RetirementScenario(64, 65, 10000, 297, .06);
        Map<String, Integer> year = testScenario.getSavingsYearData().get(0);
        int expectedAppreciation = (int) (10000 * .06);
        int expectedEndBalance = 10000 + 297 + expectedAppreciation;
        assertEquals(64, year.get("age").intValue());
        assertEquals(10000, year.get("beginBalance").intValue());
        assertEquals(297, year.get("contribution").intValue());
        assertEquals(expectedAppreciation, year.get("appreciation").intValue());
        assertEquals(expectedEndBalance, year.get("endBalance").intValue());
    }

    /**
     * Test getSavingsYearsData gets 3 years of Data 3 years before retirement
     */
    @Test
    public void testGetSavingsYearDataWhen3YearBeforeRetirementReturns3Years() {
        RetirementScenario testScenario = new RetirementScenario(62, 65, 10000, 500, .06);
        List<Map<String, Integer>> result = testScenario.getSavingsYearData();
        assertEquals(3, result.size());
    }

    /**
     * Test getSavingsYearsData gets accurate data for the third year of savings
     */
    @Test
    public void testGetSavingsYearDataGetsAccurateDataFor3rdYear() {
        RetirementScenario testScenario = new RetirementScenario(62, 65, 10000, 297, .06);
        SavingsYear firstYear = new SavingsYear(62, 10000, 297, .06);
        SavingsYear expected = firstYear.getNextYear().getNextYear();
        Map<String, Integer> actual = testScenario.getSavingsYearData().get(2);
        assertEquals(expected.getAge(), actual.get("age").intValue());
        assertEquals(expected.getBeginBalance(), actual.get("beginBalance").intValue());
        assertEquals(expected.getContribution(), actual.get("contribution").intValue());
        assertEquals(expected.getAppreciation(), actual.get("appreciation").intValue());
        assertEquals(expected.getEndBalance(), actual.get("endBalance").intValue());
    }

    /**
     * Test getSavingsYearsData returns an unmodifiable list
     */
    @Test
    public void testGetSavingsYearDataReturnsAnUnmodifiableList() {
        RetirementScenario testScenario = new RetirementScenario(62, 65, 10000, 500, .06);
        List<Map<String, Integer>> savingsData = testScenario.getSavingsYearData();
        try {
            savingsData.clear();
            fail("UnsupportedOperationException not thrown");
        } catch (UnsupportedOperationException exception) {
        }
    }
}
