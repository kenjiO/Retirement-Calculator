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
    private final List<RetirementYear> retirementYearlyData;

    /**
     * Create a new retirementScenario
     * @param currentAge the currentAge
     * @param retirementAge the planned retirement age
     * @param initialSavings the initial retirement savings
     * @param annualContribution the annual contribution
     * @param appreciationRate the appreciation rate of savings
     * @param socialSecurity the expected social security income
     * @param retirementSpending Annual spending for retirement
     *
     * Precondition currentAge and retirementAge must be between 1-100
     */
    public RetirementScenario(int currentAge, int retirementAge, int initialSavings,
                              int annualContribution, double appreciationRate,
                              int socialSecurity, int retirementSpending) {
        if (currentAge < 1 || retirementAge < 1
            || currentAge > 100 || retirementAge > 100) {
            throw new IllegalArgumentException("Ages must be 1-100");
        }
        this.savingsYearlyData = new ArrayList<SavingsYear>();
        this.retirementYearlyData = new ArrayList<RetirementYear>();

        int age = currentAge;
        if (age < retirementAge) {
            SavingsYear currentSavingsYear = new SavingsYear(age, initialSavings, annualContribution, appreciationRate);
            while (age < retirementAge) {
                this.savingsYearlyData.add(currentSavingsYear);
                currentSavingsYear = currentSavingsYear.getNextYear();
                age++;
            }
        }

        RetirementYear currentRetirementYear;
        if (this.savingsYearlyData.isEmpty()) {
            currentRetirementYear = new RetirementYear(age, initialSavings, retirementSpending, socialSecurity, appreciationRate);
        } else {
            SavingsYear lastYear = this.savingsYearlyData.get(this.savingsYearlyData.size() - 1);
            currentRetirementYear = new RetirementYear(lastYear.getAge() + 1,
                    lastYear.getEndBalance(), retirementSpending, socialSecurity, appreciationRate);
        }
        while (age < 100) {
            this.retirementYearlyData.add(currentRetirementYear);
            currentRetirementYear = currentRetirementYear.getNextYear();
            age++;
        }

    }

    /**
     * Get a list of the SavingsYears objects in this scenario
     * @return A list of the SavingsYears object for each saving year in the scenario
     */
    public List<SavingsYear> getSavingsYears() {
        return new ArrayList<SavingsYear>(this.savingsYearlyData);
    }

    /**
     * Get a list of the RetirementYears objects in this scenario
     * @return A list of the RetirementYear objects for each retirement year in the scenario
     */
    public List<RetirementYear> getRetirementYears() {
        return new ArrayList<RetirementYear>(this.retirementYearlyData);
    }

}
