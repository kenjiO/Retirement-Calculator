package edu.westga.retirement.viewmodel;

import java.util.List;

import edu.westga.retirement.model.RetirementScenario;
import edu.westga.retirement.model.RetirementYear;
import edu.westga.retirement.model.SavingsYear;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
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
    private final IntegerProperty socialSecurity = new SimpleIntegerProperty();
    private final IntegerProperty retirementSpending = new SimpleIntegerProperty();

    private final ReadOnlyListWrapper<SavingsYear> savingsYearsList = new ReadOnlyListWrapper<SavingsYear>();
    private final ReadOnlyListWrapper<RetirementYear> retirementYearsList = new ReadOnlyListWrapper<RetirementYear>();
    private final ReadOnlyStringWrapper resultMessage = new ReadOnlyStringWrapper();
    private RetirementScenario scenario;

    /**
     * Create a new View Model
     */
    public RetirementViewModel() {
        this.savingsYearsList.set(FXCollections.observableArrayList());
        this.retirementYearsList.set(FXCollections.observableArrayList());
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
     * Get the socialSecurity property. This is the expected annual social security income
     * @return the socialSecurity property
     */
    public IntegerProperty socialSecurityProperty() {
        return this.socialSecurity;
    }

    /**
     * Get the retirementSpending property. This the planned annual spending in retirement
     * @return the retirementSpending property
     */
    public IntegerProperty retirementSpendingProperty() {
        return this.retirementSpending;
    }

    /**
     * Get the resultMessage property.
     * @return the resultMessage property
     */
    public ReadOnlyStringProperty getResultMessageProperty() {
        return this.resultMessage.getReadOnlyProperty();
    }

    /**
     * Get a list of the SavingsYears for the scenario
     * @return A list of the SavingsYears objects for the scenario
    */
    public ReadOnlyListProperty<SavingsYear> getSavingsYearsList() {
        return this.savingsYearsList.getReadOnlyProperty();
    }

    /**
     * Get a list of the RetirementYears for the scenario
     * @return A list of the RetirementYears objects for the scenario
    */
    public ReadOnlyListProperty<RetirementYear> getRetirementYearsList() {
        return this.retirementYearsList.getReadOnlyProperty();
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
        int socialSecurity = this.socialSecurity.intValue();
        int retirementSpending = this.retirementSpending.intValue();
        this.scenario = new RetirementScenario(age, retireAge, initialBalance, contribution,
                appreciationRate, socialSecurity, retirementSpending);

        List<SavingsYear> savingYearsData = this.scenario.getSavingsYears();
        this.savingsYearsList.set(FXCollections.observableArrayList(savingYearsData));
        List<RetirementYear> retirementYearsData = this.scenario.getRetirementYears();
        this.retirementYearsList.set(FXCollections.observableArrayList(retirementYearsData));
        this.setResultMessage();
    }

    private void setResultMessage() {
        int age = this.scenario.getAgeRetirementLastsTo();
        String message;
        if (age == -1) {
            message = "Congragulations. You will still have savings at age " + RetirementScenario.SCENARIO_STOP_AGE;
        } else {
            message = "You will have enough retirement savings to last to age " + age;
        }
        this.resultMessage.set(message);
    }

}
