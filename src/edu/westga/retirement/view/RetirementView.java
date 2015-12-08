package edu.westga.retirement.view;

import java.io.File;

import edu.westga.retirement.model.RetirementYear;
import edu.westga.retirement.model.SavingsYear;
import edu.westga.retirement.viewmodel.RetirementViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * View class for the retirement application
 * @author Kenji Okamoto
 * @version 20151120
 *
 */
public class RetirementView {

    @FXML
    private Spinner<Integer> age;
    @FXML
    private Spinner<Integer> retireAge;
    @FXML
    private Spinner<Integer> savings;
    @FXML
    private Spinner<Integer> contribution;
    @FXML
    private Spinner<Double> returnRate;
    @FXML
    private Spinner<Integer> socialSecurity;
    @FXML
    private Spinner<Integer> retirementSpending;
    @FXML
    private Label resultMessage;
    @FXML
    private Pane resultsPane;
    @FXML
    private TableView<SavingsYear> savingsYearsTable;
    @FXML
    private TableColumn<SavingsYear, Integer> ageColumn;
    @FXML
    private TableColumn<SavingsYear, Integer> beginBalanceColumn;
    @FXML
    private TableColumn<SavingsYear, Integer> contributionColumn;
    @FXML
    private TableColumn<SavingsYear, Integer> appreciationColumn;
    @FXML
    private TableColumn<SavingsYear, Integer> endBalanceColumn;

    @FXML
    private TableView<RetirementYear> retirementYearsTable;
    @FXML
    private TableColumn<RetirementYear, Integer> retireAgeColumn;
    @FXML
    private TableColumn<RetirementYear, Integer> retireBeginBalanceColumn;
    @FXML
    private TableColumn<RetirementYear, Integer> retireSpendingColumn;
    @FXML
    private TableColumn<RetirementYear, Integer> socialSecurityColumn;
    @FXML
    private TableColumn<RetirementYear, Integer> retireAppreciationColumn;
    @FXML
    private TableColumn<RetirementYear, Integer> retireEndBalanceColumn;
    @FXML
    private Pane helpPane;
    @FXML
    private Button saveResultsBtn;
    @FXML
    private Button preset1SetBtn;
    @FXML
    private Button preset1RecallBtn;
    @FXML
    private Button preset2SetBtn;
    @FXML
    private Button preset2RecallBtn;
    @FXML
    private Button preset3SetBtn;
    @FXML
    private Button preset3RecallBtn;


    private RetirementViewModel viewModel = new RetirementViewModel();

    @FXML
    void initialize() {
        this.resultMessage.textProperty().bind(this.viewModel.getResultMessageProperty());
        this.initializeSavingsTable();
        this.initializeRetirementTable();
        this.saveResultsBtn.disableProperty().set(true);
        this.showHelp();
    }

    /**
     * Run a retirement scenario using the data in the form
     * @param event The button click event
     */
    @FXML
    public void runScenario(ActionEvent event) {
        this.setViewModelValues();
        this.viewModel.runScenario();
        this.savingsYearsTable.setItems(this.viewModel.getSavingsYearsList());
        this.retirementYearsTable.setItems(this.viewModel.getRetirementYearsList());
        this.helpPane.setVisible(false);
        this.resultsPane.setVisible(true);
        this.resultMessage.setVisible(true);
        this.saveResultsBtn.disableProperty().set(false);
        this.savingsYearsTable.scrollTo(this.savingsYearsTable.getItems().size());
        this.retirementYearsTable.scrollTo(this.retirementYearsTable.getItems().size());
    }

    /**
     * Select a file and write the data from the current scenario to it
     * @param event the button click event
     */
    @FXML
    public void saveResults(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("CSV Files", "*.csv"),
                new ExtensionFilter("All Files", "*.*"));
        fileChooser.setInitialFileName("data.csv");
        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null) {
            this.viewModel.saveToFile(selectedFile);
        }
    }

    /**
     * Save the current form field values to preset1
     */
    @FXML
    public void preset1Set() {
        this.setViewModelValues();
        this.viewModel.setPreset(1);
        this.preset1RecallBtn.disableProperty().set(false);
    }

    /**
     * Save the current form field values to preset2
     */
    @FXML
    public void preset2Set() {
        this.setViewModelValues();
        this.viewModel.setPreset(2);
        this.preset2RecallBtn.disableProperty().set(false);
    }
    /**
     * Save the current form field values to preset3
     */
    @FXML
    public void preset3Set() {
        this.setViewModelValues();
        this.viewModel.setPreset(3);
        this.preset3RecallBtn.disableProperty().set(false);
    }



    /**
     * Recall the preset 1 form values
     */
    @FXML
    public void preset1Recall() {
        this.viewModel.recallPreset(1);
        this.setViewFormFieldsFromViewModelFields();
    }

    /**
     * Recall the preset 2 form values
     */
    @FXML
    public void preset2Recall() {
        this.viewModel.recallPreset(2);
        this.setViewFormFieldsFromViewModelFields();
    }

    /**
     * Recall the preset 3 form values
     */
    @FXML
    public void preset3Recall() {
        this.viewModel.recallPreset(3);
        this.setViewFormFieldsFromViewModelFields();
    }

    /**
     * Show the help dialogue
     */
    public void showHelp() {
        this.helpPane.setVisible(true);
    }

    /**
     * Hide the help dialogue
     */
    public void hideHelp() {
        this.helpPane.setVisible(false);
    }

    private void setViewModelValues() {
        this.viewModel.currentAgeProperty().setValue(this.age.getValue());
        this.viewModel.retireAgeProperty().setValue(this.retireAge.getValue());
        this.viewModel.startBalanceProperty().setValue(this.savings.getValue());
        this.viewModel.annualContributionProperty().setValue(this.contribution.getValue());
        this.viewModel.returnRateProperty().setValue(this.returnRate.getValue() / 100);
        this.viewModel.socialSecurityProperty().setValue(this.socialSecurity.getValue());
        this.viewModel.retirementSpendingProperty().setValue(this.retirementSpending.getValue());
    }


    private void setViewFormFieldsFromViewModelFields() {
        this.age.getValueFactory().setValue(this.viewModel.currentAgeProperty().getValue());
        this.retireAge.getValueFactory().setValue(this.viewModel.retireAgeProperty().getValue());
        this.savings.getValueFactory().setValue(this.viewModel.startBalanceProperty().getValue());
        this.contribution.getValueFactory().setValue(this.viewModel.annualContributionProperty().getValue());
        this.returnRate.getValueFactory().setValue(this.viewModel.returnRateProperty().getValue() * 100);
        this.socialSecurity.getValueFactory().setValue(this.viewModel.socialSecurityProperty().getValue());
        this.retirementSpending.getValueFactory().setValue(this.viewModel.retirementSpendingProperty().getValue());
    }

    private void initializeSavingsTable() {
        this.ageColumn.setCellValueFactory(
                new PropertyValueFactory<SavingsYear, Integer>("age")
        );
        this.beginBalanceColumn.setCellValueFactory(
                new PropertyValueFactory<SavingsYear, Integer>("beginBalance")
        );
        this.contributionColumn.setCellValueFactory(
                new PropertyValueFactory<SavingsYear, Integer>("contribution")
        );
        this.appreciationColumn.setCellValueFactory(
                new PropertyValueFactory<SavingsYear, Integer>("appreciation")
        );
        this.endBalanceColumn.setCellValueFactory(
                new PropertyValueFactory<SavingsYear, Integer>("endBalance")
        );
    }

    private void initializeRetirementTable() {
        this.retireAgeColumn.setCellValueFactory(
                new PropertyValueFactory<RetirementYear, Integer>("age")
        );
        this.retireBeginBalanceColumn.setCellValueFactory(
                new PropertyValueFactory<RetirementYear, Integer>("beginBalance")
        );
        this.retireSpendingColumn.setCellValueFactory(
                new PropertyValueFactory<RetirementYear, Integer>("withdrawal")
        );
        this.socialSecurityColumn.setCellValueFactory(
                new PropertyValueFactory<RetirementYear, Integer>("socialSecurity")
        );
        this.retireAppreciationColumn.setCellValueFactory(
                new PropertyValueFactory<RetirementYear, Integer>("appreciation")
        );
        this.retireEndBalanceColumn.setCellValueFactory(
                new PropertyValueFactory<RetirementYear, Integer>("endBalance")
        );
    }
}

