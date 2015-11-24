package edu.westga.retirement.viewmodel;

import java.util.List;

import edu.westga.retirement.model.RetirementScenario;
import edu.westga.retirement.model.SavingsYear;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;

/**
 * View-Model class for the retirement applicaiont
 * @author Kenji Okamoto
 * @version 20151123
 *
 */
public class RetirementViewModel {

    private final IntegerProperty currentAge = new SimpleIntegerProperty();
    private final IntegerProperty retireAge = new SimpleIntegerProperty();
    private final IntegerProperty initialBalance = new SimpleIntegerProperty();
    private final IntegerProperty annualContribution = new SimpleIntegerProperty();
    private final DoubleProperty appreciationRate = new SimpleDoubleProperty();

    private final ReadOnlyListWrapper<SavingsYear> savingsYearsList = new ReadOnlyListWrapper<SavingsYear>();
    private RetirementScenario scenario;

    /**
     * Create a new View Model
     */
    public RetirementViewModel() {
        this.savingsYearsList.set(FXCollections.observableArrayList());
    }

    /**
     * Get the current age property
     * @return The current age property
     */
    public IntegerProperty currentAgeProperty() {
        return this.currentAge;
    }

    /**
     * Get the retire age property
     * @return The retire age property
     */
    public IntegerProperty retireAgeProperty() {
        return this.retireAge;
    }

    /**
     * Get the initialBalance property. This is the value of the present retirement savings
     * @return The initialBalance property
     */
    public IntegerProperty startBalanceProperty() {
        return this.initialBalance;
    }

    /**
     * Get the annualContribution property.  This is how much is contributed to retirement annualy.
     * @return The annualContribution property
     */
    public IntegerProperty annualContributionProperty() {
        return this.annualContribution;
    }

    /**
     * Get the appreciationRate property.  This is the annual percent growth of retirement assets
     * @return The appreciationRate property
     */
    public DoubleProperty returnRateProperty() {
        return this.appreciationRate;
    }

    /**
     * Get a list of the SavingsYears for the scenario
     * @return A list of the SavingsYears objects for the scenario
    */
    public ReadOnlyListProperty<SavingsYear> getSavingsYearsList() {
        return this.savingsYearsList.getReadOnlyProperty();
    }

   /**
    * Run a retirement scenario using the current field values
    */
    public void runScenario() {
        int age = this.currentAge.intValue();
        int retireAge = this.retireAge.intValue();
        int initialBalance = this.initialBalance.intValue();
        int contribution = this.annualContribution.intValue();
        double appreciationRate = this.appreciationRate.doubleValue();

        this.scenario = new RetirementScenario(age, retireAge, initialBalance, contribution, appreciationRate);

        List<SavingsYear> savingYearsData = this.scenario.getSavingsYears();
        this.savingsYearsList.set(FXCollections.observableArrayList(savingYearsData));
    }

}