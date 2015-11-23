package edu.westga.retirement.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
     * Get a list of data for all the savings years.
     * @return An immutable list of savings years data as unmodifiable maps
     */
    public List<Map<String, Integer>> getSavingsYearData() {
        ArrayList<Map<String, Integer>> data = new ArrayList<Map<String, Integer>>();
        for (SavingsYear year : this.savingsYearlyData) {
            data.add(year.getMappedValues());
        }
        return  Collections.unmodifiableList(data);
    }

}
