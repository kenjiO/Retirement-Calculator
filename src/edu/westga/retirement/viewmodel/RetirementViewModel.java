package edu.westga.retirement.viewmodel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * Max number of presets that can be saved.
     */
    public static final int MAX_NUMBER_PRESETS = 10;
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
    private List<Map<String, Number>> presets;

    /**
     * Create a new View Model
     */
    public RetirementViewModel() {
        this.savingsYearsList.set(FXCollections.observableArrayList());
        this.retirementYearsList.set(FXCollections.observableArrayList());

        // Create list of presets and initializes with null values for each preset
        this.presets = new ArrayList<Map<String, Number>>();
        for (int index = 0; index < MAX_NUMBER_PRESETS; index++) {
            this.presets.add(null);
        }
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

    /**
     * Save the results of the current scenario to a file in csv form
     * @param outFile The file to save the csv data to
     * Precondition: outFile != null
     */
    public void saveToFile(File outFile) {
        if (outFile == null) {
            throw new IllegalArgumentException("outFile cannot be null");
        }
        FileWriter writer;
        try {
            writer = new FileWriter(outFile);
            writer.write(this.scenario.toString());
            writer.close();
        } catch (IOException exceptio) {
            this.resultMessage.set("Error writing data to the selected file");
            return;
        }
        this.resultMessage.set("Scenario saved to " + outFile.getName());
    }

    /**
     * Save the current form fields to a preset that can be recalled later
     * @param presetNumber The preset number to save to
     * Precondition: preset number is between 1 and {@value #MAX_NUMBER_PRESETS}
     */
    public void setPreset(int presetNumber) {
        if (presetNumber > RetirementViewModel.MAX_NUMBER_PRESETS
                || presetNumber < 1) {
            throw new IllegalArgumentException("Preset number must be between 1 and " + RetirementViewModel.MAX_NUMBER_PRESETS);
        }
        Map<String, Number> preset = new HashMap<String, Number>();
        preset.put("currentAge", this.currentAge.getValue());
        preset.put("retireAge", this.retireAge.getValue());
        preset.put("initialBalance", this.initialBalance.getValue());
        preset.put("annualContribution", this.annualContribution.getValue());
        preset.put("appreciationRate", this.appreciationRate.getValue());
        preset.put("socialSecurity", this.socialSecurity.getValue());
        preset.put("retirementSpending", this.retirementSpending.getValue());
        this.presets.set(presetNumber - 1, preset);
    }

    /**
     * Set the scenario parameters to those of a saved preset
     * @param presetNumber The preset number to get the parameters from.
     * @return false when the preset at the given number has been set, otherwise true
     * Precondition: preset number is between 1 and {@value #MAX_NUMBER_PRESETS}
     */
    public boolean recallPreset(int presetNumber) {
        if (presetNumber > RetirementViewModel.MAX_NUMBER_PRESETS
                || presetNumber < 1) {
            throw new IllegalArgumentException("Preset number must be between 1 and " + RetirementViewModel.MAX_NUMBER_PRESETS);
        }
        Map<String, Number> preset = this.presets.get(presetNumber - 1);
        if (preset == null) {
            return false;
        }
        this.currentAge.setValue(preset.get("currentAge"));
        this.retireAge.setValue(preset.get("retireAge"));
        this.initialBalance.setValue(preset.get("initialBalance"));
        this.annualContribution.setValue(preset.get("annualContribution"));
        this.appreciationRate.setValue(preset.get("appreciationRate"));
        this.socialSecurity.setValue(preset.get("socialSecurity"));
        this.retirementSpending.setValue(preset.get("retirementSpending"));
        this.runScenario();
        return true;
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
