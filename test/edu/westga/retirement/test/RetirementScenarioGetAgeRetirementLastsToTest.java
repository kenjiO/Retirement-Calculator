package edu.westga.retirement.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.westga.retirement.model.RetirementScenario;

/**
 * Test RetirementScenario getAgeRetirementLastsTo method
 * @author Kenji Okamoto
 * @version 20151201
 *
 */
public class RetirementScenarioGetAgeRetirementLastsToTest {

    /**
     * If the retirement does not run out getAgeRetirementLasts to should be -1
     */
    @Test
    public void testWhenRetirementDoesNotRunOutItIsMinus1() {
        RetirementScenario testScenario = new RetirementScenario(50, 61, 100000, 500, .06, 0, 0);
        assertEquals(-1, testScenario.getAgeRetirementLastsTo());
    }

    /**
     * Test when the retirement runs out in one year
     */
    @Test
    public void testWhenRetirementRunsOutInOneYear() {
        RetirementScenario testScenario = new RetirementScenario(70, 70, 1000, 0, 0.0, 0, 900);
        assertEquals(71, testScenario.getAgeRetirementLastsTo());
    }

    /**
     * Test when the retirement runs out in exactly one year
     */
    @Test
    public void testWhenRetirementRunsOutInExactlyOneYear() {
        RetirementScenario testScenario = new RetirementScenario(70, 70, 1000, 0, 0.0, 0, 1000);
        assertEquals(71, testScenario.getAgeRetirementLastsTo());
    }

    /**
     * Test when the retirement runs out in two years
     */
    @Test
    public void testWhenRetirementRunsOutIn2Years() {
        RetirementScenario testScenario = new RetirementScenario(70, 70, 2200, 0, 0.0, 0, 1000);
        assertEquals(72, testScenario.getAgeRetirementLastsTo());
    }

    /**
     * Test when the retirement runs out in two years after making contributions
     */
    @Test
    public void testWhenRetirementRunsOutIn2YearsAfterMakingContributions() {
        RetirementScenario testScenario = new RetirementScenario(60, 62, 1800, 500, 0.0, 0, 1000);
        assertEquals(64, testScenario.getAgeRetirementLastsTo());
    }

}
