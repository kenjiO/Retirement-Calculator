package edu.westga.retirement.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import edu.westga.retirement.model.RetirementScenario;
import edu.westga.retirement.model.SavingsYear;
import edu.westga.retirement.viewmodel.RetirementViewModel;


/**
 * Class to test the RetirementViewModel class
 * @author Kenji Okamoto
 * @version 20151123
 *
 */
public class RetirementViewModelTest {

    /**
     * Test that the view model field can be set
     */
    @Test
    public void testRetirementViewModelAgePropertyCanBeSet() {
        RetirementViewModel viewModel = new RetirementViewModel();
        viewModel.currentAgeProperty().set(47);
        assertEquals(47, viewModel.currentAgeProperty().get());
    }

    /**
     * Test that the view model retireAge field can be set
     */
    @Test
    public void testRetirementViewModelretireAgePropertyCanBeSet() {
        RetirementViewModel viewModel = new RetirementViewModel();
        viewModel.retireAgeProperty().set(53);
        assertEquals(53, viewModel.retireAgeProperty().get());
    }

    /**
     * Test that the view model startBalance field can be set
     */
    @Test
    public void testRetirementViewModelStartBalancePropertyCanBeSet() {
        RetirementViewModel viewModel = new RetirementViewModel();
        viewModel.startBalanceProperty().set(1029);
        assertEquals(1029, viewModel.startBalanceProperty().get());
    }

    /**
     * Test that the view model annualContribution field can be set
     */
    @Test
    public void testRetirementViewModelAnnualContributionPropertyCanBeSet() {
        RetirementViewModel viewModel = new RetirementViewModel();
        viewModel.annualContributionProperty().set(491);
        assertEquals(491, viewModel.annualContributionProperty().get());
    }

    /**
     * Test that the view model returnRate field can be set
     */
    @Test
    public void testRetirementViewModelReturnRatePropertyCanBeSet() {
        RetirementViewModel viewModel = new RetirementViewModel();
        viewModel.returnRateProperty().set(9.1);
        assertEquals(9.1, viewModel.returnRateProperty().get(), .000001);
    }

    /**
     * Test getSavingsYearsList will be empty before fields are set
     */
    @Test
    public void testGetSavingsYearsListWillBeEmptyBeforeFieldsSet() {
        RetirementViewModel viewModel = new RetirementViewModel();
        List<SavingsYear> years = viewModel.getSavingsYearsList();
        assertEquals(0, years.size());
    }

    /**
     * Test runScenario will create SavingsYears until retired
     */
    @Test
    public void testRunScenarioWillGenerateSavingsYearsUntilRetireAge() {
        int currentAge = 59;
        int retireAge = 71;
        RetirementViewModel viewModel = new RetirementViewModel();
        viewModel.currentAgeProperty().set(currentAge);
        viewModel.retireAgeProperty().set(retireAge);
        viewModel.runScenario();
        List<SavingsYear> years =  viewModel.getSavingsYearsList();
        assertEquals(retireAge - currentAge, years.size());
    }

    /**
     * Test runScenario will generate the same savings data as the retirementScenario model class
     */
    @Test
    public void testRunScenarioWillGenerateTheSameSavingsYearDataAsModelClass() {
        int currentAge = 51;
        int retireAge = 63;
        int startBalance = 2734;
        int contribution = 375;
        double returnRate = 5.3;
        RetirementScenario scenario = new RetirementScenario(currentAge, retireAge,
                                                startBalance, contribution, returnRate);
        List<SavingsYear> dataFromModel = scenario.getSavingsYears();

        RetirementViewModel viewModel = new RetirementViewModel();
        viewModel.currentAgeProperty().set(currentAge);
        viewModel.retireAgeProperty().set(retireAge);
        viewModel.startBalanceProperty().set(startBalance);
        viewModel.annualContributionProperty().set(contribution);
        viewModel.returnRateProperty().set(returnRate);
        viewModel.runScenario();
        List<SavingsYear> dataFromViewModel = viewModel.getSavingsYearsList();

        assertEquals(dataFromModel.size(), dataFromViewModel.size());
        for (int index = 0; index < dataFromModel.size(); index++) {
            assertEquals(dataFromModel.get(index), dataFromViewModel.get(index));
        }
    }

}
