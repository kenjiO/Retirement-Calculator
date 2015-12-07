package edu.westga.retirement.view;

import java.io.File;

import edu.westga.retirement.model.RetirementYear;
import edu.westga.retirement.model.SavingsYear;
import edu.westga.retirement.viewmodel.RetirementViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

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

    private RetirementViewModel viewModel = new RetirementViewModel();

    @FXML
    void initialize() {
        this.resultMessage.textProperty().bind(this.viewModel.getResultMessageProperty());
        this.initializeSavingsTable();
        this.initializeRetirementTable();
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
    }

    /**
     * Select a file and write the data from the current scenario to it
     * @param event the button click event
     */
    @FXML
    public void saveResults(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null) {
            this.viewModel.saveToFile(selectedFile);
        }
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

