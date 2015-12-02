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
    /**
     * The age the scenario goes up to
     */
    public static final int SCENARIO_STOP_AGE = 99;
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
        while (age <= SCENARIO_STOP_AGE) {
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

    /**
     * Gets the last age the retirement savings are still positive
     * @return The last age the retirement savings are still postive or -1
     * if there are still savings at the end of the scenario
     */
    public int getAgeRetirementLastsTo() {
        for (RetirementYear year : this.retirementYearlyData) {
            if (year.getEndBalance() < 0) {
                return year.getAge();
            }
        }
        return -1;
    }

}
