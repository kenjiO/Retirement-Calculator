package edu.westga.retirement.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to model a possible retirement scenario
 * @author Kenji Okamoto
 * @version 20151123
 *
 */
public final class RetirementScenario {
    private final List<SavingsYear> savingsYearlyData;

    /**
     * Create a new retirementScenario
     * @param currentAge the currentAge
     * @param retirementAge the planned retirement age
     * @param initialSavings the initial retirement savings
     * @param annualContribution the annual contribution
     * @param appreciationRate the appreciation rate of savings
     * Precondition currentAge > 0 and retirementAge > 0
     */
    public RetirementScenario(int currentAge, int retirementAge, int initialSavings,
                              int annualContribution, double appreciationRate) {
        if (currentAge < 1 || retirementAge < 1) {
            throw new IllegalArgumentException("Cannot have ages below 1");
        }
        this.savingsYearlyData = new ArrayList<SavingsYear>();

        if (currentAge < retirementAge) {
            SavingsYear currentYear = new SavingsYear(currentAge, initialSavings, annualContribution, appreciationRate);
            for (int index = currentAge; index < retirementAge; index++) {
                this.savingsYearlyData.add(currentYear);
                currentYear = currentYear.getNextYear();
            }
        }
    }

    /**
     * Get a list of the SavingsYears objects in this scenario
     * @return A list of the SavingsYears object for each saving year in the scenario
     */
    public List<SavingsYear> getSavingsYears() {
        return new ArrayList<SavingsYear>(this.savingsYearlyData);
    }

}
