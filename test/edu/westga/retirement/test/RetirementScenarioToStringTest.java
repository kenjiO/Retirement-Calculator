package edu.westga.retirement.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.westga.retirement.model.RetirementScenario;

/**
 * Test RetirementScenario toString method
 * @author Kenji Okamoto
 * @version 20151206
 *
 */
public class RetirementScenarioToStringTest {
    private String expectedResultWhen55 = "Year,Start Balance,Contribution,Withdrawal,Social Security,Appreciation,End Balance" + System.lineSeparator()
        + "50,100000,5000,0,0,7000,112000" + System.lineSeparator()
        + "51,112000,5000,0,0,7840,124840" + System.lineSeparator()
        + "52,124840,5000,0,0,8738,138578" + System.lineSeparator()
        + "53,138578,5000,0,0,9700,153278" + System.lineSeparator()
        + "54,153278,5000,0,0,10729,169007" + System.lineSeparator()
        + "55,169007,5000,0,0,11830,185837" + System.lineSeparator()
        + "56,185837,5000,0,0,13008,203845" + System.lineSeparator()
        + "57,203845,5000,0,0,14269,223114" + System.lineSeparator()
        + "58,223114,5000,0,0,15617,243731" + System.lineSeparator()
        + "59,243731,5000,0,0,17061,265792" + System.lineSeparator()
        + "60,265792,5000,0,0,18605,289397" + System.lineSeparator()
        + "61,289397,5000,0,0,20257,314654" + System.lineSeparator()
        + "62,314654,5000,0,0,22025,341679" + System.lineSeparator()
        + "63,341679,5000,0,0,23917,370596" + System.lineSeparator()
        + "64,370596,5000,0,0,25941,401537" + System.lineSeparator()
        + "65,401537,5000,0,0,28107,434644" + System.lineSeparator()
        + "66,434644,5000,0,0,30425,470069" + System.lineSeparator()
        + "67,470069,5000,0,0,32904,507973" + System.lineSeparator()
        + "68,507973,0,50000,12000,35558,505531" + System.lineSeparator()
        + "69,505531,0,50000,12000,35387,502918" + System.lineSeparator()
        + "70,502918,0,50000,12000,35204,500122" + System.lineSeparator()
        + "71,500122,0,50000,12000,35008,497130" + System.lineSeparator()
        + "72,497130,0,50000,12000,34799,493929" + System.lineSeparator()
        + "73,493929,0,50000,12000,34575,490504" + System.lineSeparator()
        + "74,490504,0,50000,12000,34335,486839" + System.lineSeparator()
        + "75,486839,0,50000,12000,34078,482917" + System.lineSeparator()
        + "76,482917,0,50000,12000,33804,478721" + System.lineSeparator()
        + "77,478721,0,50000,12000,33510,474231" + System.lineSeparator()
        + "78,474231,0,50000,12000,33196,469427" + System.lineSeparator()
        + "79,469427,0,50000,12000,32859,464286" + System.lineSeparator()
        + "80,464286,0,50000,12000,32500,458786" + System.lineSeparator()
        + "81,458786,0,50000,12000,32115,452901" + System.lineSeparator()
        + "82,452901,0,50000,12000,31703,446604" + System.lineSeparator()
        + "83,446604,0,50000,12000,31262,439866" + System.lineSeparator()
        + "84,439866,0,50000,12000,30790,432656" + System.lineSeparator()
        + "85,432656,0,50000,12000,30285,424941" + System.lineSeparator()
        + "86,424941,0,50000,12000,29745,416686" + System.lineSeparator()
        + "87,416686,0,50000,12000,29168,407854" + System.lineSeparator()
        + "88,407854,0,50000,12000,28549,398403" + System.lineSeparator()
        + "89,398403,0,50000,12000,27888,388291" + System.lineSeparator()
        + "90,388291,0,50000,12000,27180,377471" + System.lineSeparator()
        + "91,377471,0,50000,12000,26422,365893" + System.lineSeparator()
        + "92,365893,0,50000,12000,25612,353505" + System.lineSeparator()
        + "93,353505,0,50000,12000,24745,340250" + System.lineSeparator()
        + "94,340250,0,50000,12000,23817,326067" + System.lineSeparator()
        + "95,326067,0,50000,12000,22824,310891" + System.lineSeparator()
        + "96,310891,0,50000,12000,21762,294653" + System.lineSeparator()
        + "97,294653,0,50000,12000,20625,277278" + System.lineSeparator()
        + "98,277278,0,50000,12000,19409,258687" + System.lineSeparator()
        + "99,258687,0,50000,12000,18108,238795" + System.lineSeparator();

    /**
     *  Test with a scenario that starts at age 50
     */
    @Test
    public void testWithScnearioStartingAtAge50() {
        RetirementScenario testScenario = new RetirementScenario(50, 68, 100000, 5000, .07, 12000, 50000);
        assertEquals(this.expectedResultWhen55, testScenario.toString());
    }

    /**
     *  Test with a scenario that starts at age 98
     */
    @Test
    public void testWithScenarioStartingAtAge98() {
        RetirementScenario testScenario = new RetirementScenario(98, 68, 100000, 5000, .07, 12000, 50000);
        String expected = "Year,Start Balance,Contribution,Withdrawal,Social Security,Appreciation,End Balance" + System.lineSeparator()
            + "98,100000,0,50000,12000,7000,69000" + System.lineSeparator()
            + "99,69000,0,50000,12000,4830,35830" + System.lineSeparator();
        assertEquals(expected, testScenario.toString());
    }

    /**
     *  Test with a scenario that starts at age 99
     */
    @Test
    public void testWithScenarioStartingAtAge99() {
        RetirementScenario testScenario = new RetirementScenario(99, 68, 100000, 5000, .07, 12000, 50000);
        String expected = "Year,Start Balance,Contribution,Withdrawal,Social Security,Appreciation,End Balance" + System.lineSeparator()
                + "99,100000,0,50000,12000,7000,69000"  + System.lineSeparator();
        assertEquals(expected, testScenario.toString());
    }

}
