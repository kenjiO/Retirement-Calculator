package edu.westga.retirement.view;

import edu.westga.retirement.model.SavingsYear;
import edu.westga.retirement.viewmodel.RetirementViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

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
    private Pane helpPane;

    private RetirementViewModel viewModel = new RetirementViewModel();

    @FXML
    void initialize() {
        this.initializeSavingsTable();
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
        this.helpPane.setVisible(false);
        this.savingsYearsTable.visibleProperty().setValue(true);
    }

    private void setViewModelValues() {
        this.viewModel.currentAgeProperty().setValue(this.age.getValue());
        this.viewModel.retireAgeProperty().setValue(this.retireAge.getValue());
        this.viewModel.startBalanceProperty().setValue(this.savings.getValue());
        this.viewModel.annualContributionProperty().setValue(this.contribution.getValue());
        this.viewModel.returnRateProperty().setValue(this.returnRate.getValue() / 100);
    }

    private void initializeSavingsTable() {
        this.savingsYearsTable.visibleProperty().setValue(false);

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

}
