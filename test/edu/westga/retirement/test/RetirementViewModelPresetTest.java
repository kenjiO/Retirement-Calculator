package edu.westga.retirement.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.westga.retirement.viewmodel.RetirementViewModel;

/**
 * Test the RetirementViewModel class set and recall preset methods
 * @author Kenji Okamoto
 * @version 20151206
 *
 */
public class RetirementViewModelPresetTest {

    /**
     * Test preset 1 can be set and recalled
     */
    @Test
    public void testPreset1CanBeSetAndRecalled() {
        RetirementViewModel viewModel = new RetirementViewModel();
        viewModel.currentAgeProperty().set(50);
        viewModel.retireAgeProperty().set(65);
        viewModel.startBalanceProperty().setValue(100000);
        viewModel.annualContributionProperty().setValue(5000);
        viewModel.returnRateProperty().setValue(0.06);
        viewModel.socialSecurityProperty().setValue(12000);
        viewModel.retirementSpendingProperty().setValue(50000);

        viewModel.setPreset(1);

        viewModel.currentAgeProperty().set(51);
        viewModel.retireAgeProperty().set(66);
        viewModel.startBalanceProperty().setValue(10001);
        viewModel.annualContributionProperty().setValue(5001);
        viewModel.returnRateProperty().setValue(0.07);
        viewModel.socialSecurityProperty().setValue(12001);
        viewModel.retirementSpendingProperty().setValue(50001);

        viewModel.recallPreset(1);

        assertEquals(viewModel.currentAgeProperty().get(), 50);
        assertEquals(viewModel.retireAgeProperty().get(), 65);
        assertEquals(viewModel.startBalanceProperty().get(), 100000);
        assertEquals(viewModel.annualContributionProperty().get(), 5000);
        assertEquals(viewModel.returnRateProperty().get(), 0.06, 0.000001);
        assertEquals(viewModel.socialSecurityProperty().get(), 12000);
        assertEquals(viewModel.retirementSpendingProperty().get(), 50000);
    }

    /**
     * Test the last preset can be set and recalled
     */
    @Test
    public void testTheLastPresetCanBeSetAndRecalled() {
        RetirementViewModel viewModel = new RetirementViewModel();
        viewModel.currentAgeProperty().set(50);
        viewModel.retireAgeProperty().set(65);
        viewModel.startBalanceProperty().setValue(100000);
        viewModel.annualContributionProperty().setValue(5000);
        viewModel.returnRateProperty().setValue(0.06);
        viewModel.socialSecurityProperty().setValue(12000);
        viewModel.retirementSpendingProperty().setValue(50000);

        viewModel.setPreset(RetirementViewModel.MAX_NUMBER_PRESETS);

        viewModel.currentAgeProperty().set(51);
        viewModel.retireAgeProperty().set(66);
        viewModel.startBalanceProperty().setValue(10001);
        viewModel.annualContributionProperty().setValue(5001);
        viewModel.returnRateProperty().setValue(0.07);
        viewModel.socialSecurityProperty().setValue(12001);
        viewModel.retirementSpendingProperty().setValue(50001);

        viewModel.recallPreset(RetirementViewModel.MAX_NUMBER_PRESETS);

        assertEquals(viewModel.currentAgeProperty().get(), 50);
        assertEquals(viewModel.retireAgeProperty().get(), 65);
        assertEquals(viewModel.startBalanceProperty().get(), 100000);
        assertEquals(viewModel.annualContributionProperty().get(), 5000);
        assertEquals(viewModel.returnRateProperty().get(), 0.06, 0.000001);
        assertEquals(viewModel.socialSecurityProperty().get(), 12000);
        assertEquals(viewModel.retirementSpendingProperty().get(), 50000);
    }

    /**
     * Test recalling a preset that has not been set does not change the form fields
     */
    @Test
    public void testFormFieldsDoNotChangeWhenRecallingUnsetPreset() {
        RetirementViewModel viewModel = new RetirementViewModel();
        viewModel.currentAgeProperty().set(50);
        viewModel.retireAgeProperty().set(65);
        viewModel.startBalanceProperty().setValue(100000);
        viewModel.annualContributionProperty().setValue(5000);
        viewModel.returnRateProperty().setValue(0.06);
        viewModel.socialSecurityProperty().setValue(12000);
        viewModel.retirementSpendingProperty().setValue(50000);

        for (int index = 1; index <= RetirementViewModel.MAX_NUMBER_PRESETS; index++) {
            viewModel.recallPreset(index);

            assertEquals(viewModel.currentAgeProperty().get(), 50);
            assertEquals(viewModel.retireAgeProperty().get(), 65);
            assertEquals(viewModel.startBalanceProperty().get(), 100000);
            assertEquals(viewModel.annualContributionProperty().get(), 5000);
            assertEquals(viewModel.returnRateProperty().get(), 0.06, 0.000001);
            assertEquals(viewModel.socialSecurityProperty().get(), 12000);
            assertEquals(viewModel.retirementSpendingProperty().get(), 50000);
        }
    }

    /**
     * Test setting one preset does not change another one
     */
    @Test
    public void testSettingOnePresetDoesNotChangeAnotherOne() {
        RetirementViewModel viewModel = new RetirementViewModel();
        viewModel.currentAgeProperty().set(50);
        viewModel.retireAgeProperty().set(65);
        viewModel.startBalanceProperty().setValue(100000);
        viewModel.annualContributionProperty().setValue(5000);
        viewModel.returnRateProperty().setValue(0.06);
        viewModel.socialSecurityProperty().setValue(12000);
        viewModel.retirementSpendingProperty().setValue(50000);
        viewModel.setPreset(2);

        viewModel.currentAgeProperty().set(51);
        viewModel.retireAgeProperty().set(66);
        viewModel.startBalanceProperty().setValue(10001);
        viewModel.annualContributionProperty().setValue(5001);
        viewModel.returnRateProperty().setValue(0.07);
        viewModel.socialSecurityProperty().setValue(12001);
        viewModel.retirementSpendingProperty().setValue(50001);
        viewModel.setPreset(1);
        viewModel.setPreset(3);

        viewModel.recallPreset(2);

        assertEquals(viewModel.currentAgeProperty().get(), 50);
        assertEquals(viewModel.retireAgeProperty().get(), 65);
        assertEquals(viewModel.startBalanceProperty().get(), 100000);
        assertEquals(viewModel.annualContributionProperty().get(), 5000);
        assertEquals(viewModel.returnRateProperty().get(), 0.06, 0.000001);
        assertEquals(viewModel.socialSecurityProperty().get(), 12000);
        assertEquals(viewModel.retirementSpendingProperty().get(), 50000);
    }

    /**
     * Test recalling a set preset returns true
     */
    @Test
    public void testRecallingASetPresetReturnsTrue() {
        RetirementViewModel viewModel = new RetirementViewModel();
        viewModel.currentAgeProperty().set(50);
        viewModel.retireAgeProperty().set(65);
        viewModel.startBalanceProperty().setValue(100000);
        viewModel.annualContributionProperty().setValue(5000);
        viewModel.returnRateProperty().setValue(0.06);
        viewModel.socialSecurityProperty().setValue(12000);
        viewModel.retirementSpendingProperty().setValue(50000);

        for (int index = 1; index <= RetirementViewModel.MAX_NUMBER_PRESETS; index++) {
            viewModel.setPreset(index);
            assertEquals(true, viewModel.recallPreset(index));
        }
    }

    /**
     * Test recalling an unset preset returns false
     */
    @Test
    public void testRecallingAnUnsetPresetReturnsFalse() {
        RetirementViewModel viewModel = new RetirementViewModel();
        for (int index = 1; index <= RetirementViewModel.MAX_NUMBER_PRESETS; index++) {
            assertEquals(false, viewModel.recallPreset(index));
        }
    }

}
