package edu.westga.retirement.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import edu.westga.retirement.model.RetirementScenario;
import edu.westga.retirement.model.RetirementYear;
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
        int socialSecurity = 10000;
        int retirementSpending = 50000;
        RetirementScenario scenario = new RetirementScenario(currentAge, retireAge, startBalance,
                                        contribution, returnRate, socialSecurity, retirementSpending);
        List<SavingsYear> dataFromModel = scenario.getSavingsYears();

        RetirementViewModel viewModel = new RetirementViewModel();
        viewModel.currentAgeProperty().set(currentAge);
        viewModel.retireAgeProperty().set(retireAge);
        viewModel.startBalanceProperty().set(startBalance);
        viewModel.annualContributionProperty().set(contribution);
        viewModel.returnRateProperty().set(returnRate);
        viewModel.socialSecurityProperty().set(socialSecurity);
        viewModel.retirementSpendingProperty().set(retirementSpending);
        viewModel.runScenario();
        List<SavingsYear> dataFromViewModel = viewModel.getSavingsYearsList();

        assertEquals(dataFromModel.size(), dataFromViewModel.size());
        for (int index = 0; index < dataFromModel.size(); index++) {
            assertEquals(dataFromModel.get(index), dataFromViewModel.get(index));
        }
    }

    /**
     * Test that the socialSecurity field can be set
     */
    @Test
    public void testRetirementViewModelSocialSecurityPropertyCanBeSet() {
        RetirementViewModel viewModel = new RetirementViewModel();
        viewModel.socialSecurityProperty().set(25374);
        assertEquals(25374, viewModel.socialSecurityProperty().get());
    }

    /**
     * Test that the retirement spending field can be set
     */
    @Test
    public void testRetirementViewModelRetirementSpendingPropertyCanBeSet() {
        RetirementViewModel viewModel = new RetirementViewModel();
        viewModel.retirementSpendingProperty().set(79234);
        assertEquals(79234, viewModel.retirementSpendingProperty().get());
    }

    /**
     * Test runScenario will create RetirementYears after the retire age
     */
    @Test
    public void testRunScenarioWillGenerateRetireYearsAfterTheRetireAge() {
        int currentAge = 59;
        int retireAge = 71;
        RetirementViewModel viewModel = new RetirementViewModel();
        viewModel.currentAgeProperty().set(currentAge);
        viewModel.retireAgeProperty().set(retireAge);
        viewModel.runScenario();
        List<RetirementYear> years = viewModel.getRetirementYearsList();
        assertEquals(71, years.get(0).getAge());
    }

    /**
     * Test runScenario will generate the same retirement years data as the
     * retirementScenario model class
     */
    @Test
    public void testRunScenarioWillGenerateTheSameRetiredYearsDataAsModelClass() {
        int currentAge = 51;
        int retireAge = 63;
        int startBalance = 2734;
        int contribution = 375;
        double returnRate = 5.3;
        int socialSecurity = 1735;
        int retirementSpending = 53734;
        RetirementScenario scenario = new RetirementScenario(currentAge, retireAge, startBalance,
                                        contribution, returnRate, socialSecurity, retirementSpending);
        List<RetirementYear> dataFromModel = scenario.getRetirementYears();

        RetirementViewModel viewModel = new RetirementViewModel();
        viewModel.currentAgeProperty().set(currentAge);
        viewModel.retireAgeProperty().set(retireAge);
        viewModel.startBalanceProperty().set(startBalance);
        viewModel.annualContributionProperty().set(contribution);
        viewModel.returnRateProperty().set(returnRate);
        viewModel.socialSecurityProperty().set(socialSecurity);
        viewModel.retirementSpendingProperty().set(retirementSpending);
        viewModel.runScenario();
        List<RetirementYear> dataFromViewModel = viewModel.getRetirementYearsList();
        assertEquals(dataFromModel.size(), dataFromViewModel.size());
        for (int index = 0; index < dataFromModel.size(); index++) {
            assertEquals(dataFromModel.get(index), dataFromViewModel.get(index));
        }
    }

    /**
     * Test that when the properties have not been set getRetirementYears will be empty
     */
    @Test
    public void testWhenPropertiesHaveNotBeenSetGetRetirementYearsWillBeEmpty() {
        RetirementViewModel viewModel = new RetirementViewModel();
        assertEquals(0, viewModel.getRetirementYearsList().size());
    }
    
    /**
     * Test that a scenario for an already retired person has no saving year data
     */
    @Test
    public void testWhenAlreadyRetiredScenarioSavingYearListIsEmpty() {
        int currentAge = 64;
        int retireAge = 63;
        int startBalance = 2734;
        int contribution = 375;
        double returnRate = 5.3;
        int socialSecurity = 1735;
        int retirementSpending = 53734;
        RetirementScenario scenario = new RetirementScenario(currentAge, retireAge, startBalance,
                                        contribution, returnRate, socialSecurity, retirementSpending);
        assertEquals(0, scenario.getSavingsYears().size());
    }
    
}
