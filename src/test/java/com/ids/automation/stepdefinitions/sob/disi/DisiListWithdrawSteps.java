package com.ids.automation.stepdefinitions.sob.disi;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;
import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.sob.SOBHomePages;
import pages.sob.disi.SOBDisiListWithdrawPages;
import utility.Helper;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DisiListWithdrawSteps {
    static WebDriver driver;
    SOBLoginSteps loginSteps = new SOBLoginSteps();
    SOBHomePages homePage;
    SOBHelper sobHelper = new SOBHelper();
    SOBDisiListWithdrawPages disiListWithdrawPage;
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;
    Integer countImage = 1;

    @Given("I am in DISI List Withdraw")
    public void i_am_in_disi_list_withdraw() {
        loginSteps.setUp();
        loginSteps.setUpLogin("adminstg", "disi_list_withdraw");
        setUpDisiListWithdraw();
        homePage.MenuDrawer();
        homePage.openNavMenu("Deposit Sistem", 0, 450, "smooth");
        homePage.openNavSubMenu("List Withdraw");
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - List Withdraw");
        homePage.MenuDrawer();
        sobHelper.delay(1000);
    }

    @Given("I hit Filter Button on DISI List Withdraw")
    public void i_hit_filter_button_on_disi_list_withdraw() {
        disiListWithdrawPage.hitBtnFilter();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - List Withdraw " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @When("I fill all Fields \\({string}, {string}, {string}, {string}) on Filter Field on DISI List Withdraw")
    public void i_fill_all_fields_on_filter_field_on_disi_list_withdraw(String fromDate, String trackingReff
            , String partnerId, String merchantId) {
        String[] valueDate = fromDate.split(",", -1);

        disiListWithdrawPage.fillFromDate(valueDate);
        disiListWithdrawPage.fillTrackingReff(trackingReff);
        sobHelper.delay(2000);
        disiListWithdrawPage.fillPartnerId(partnerId);
        sobHelper.delay(3000);
        disiListWithdrawPage.fillMerchantId(merchantId);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - List Withdraw " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @When("I fill From Date \\({string}) on Filter Field on DISI List Withdraw")
    public void i_fill_from_date_on_filter_field_on_disi_list_withdraw(String fromDate) {
//        String[] valueDate = {"24", "03"};
        String[] valueDate = fromDate.split(",", -1);
        disiListWithdrawPage.fillFromDate(valueDate);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - List Withdraw " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @When("I fill Tracking Reff \\({string}) on Filter Field on Disi List Withdraw")
    public void i_fill_tracking_reff_on_filter_field_on_disi_list_withdraw(String trackingReff) {
        disiListWithdrawPage.fillTrackingReff(trackingReff);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - List Withdraw " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @When("I hit Advice Button on DISI List Withdraw case {string}")
    public void i_hit_advice_button_on_disi_list_withdraw_case(String condition) {
        homePage.scrollBody(-250);
        disiListWithdrawPage.scrollDatatable();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - List Withdraw " + countImage);
        countImage++;
        sobHelper.delay(800);
        disiListWithdrawPage.hitBtnAdvice();
        disiListWithdrawPage.hitBtnConfirm();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - List Withdraw " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Then("I {string} navigate to DISI List Withdraw")
    public void i_navigate_to_disi_list_withdraw(String condition) {
        setUpDisiListWithdraw();
        homePage.MenuDrawer();
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Homepage");

        // condition check
        if (condition.equals("Successfully")) {
            homePage.openNavMenu("Deposit Sistem", 0, 450, "smooth");
            sobHelper.delay(1500);
            homePage.openNavSubMenu("List Withdraw");
            sobHelper.delay(2000);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Disi - List Withdraw");
            homePage.MenuDrawer();
            sobHelper.delay(2000);
            assertEquals(disiListWithdrawPage.getLblListWithdraw(), "List Withdraw");
        } else {
            assertTrue(homePage.checkNoNavMenu("Deposit Sistem"));
        }

        sobHelper.delay(1000);
    }

    @Then("I hit Apply Button on DISI List Withdraw")
    public void i_hit_apply_button_on_disi_list_withdraw() {
        disiListWithdrawPage.hitBtnApply();
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - List Withdraw " + countImage);
        countImage++;
        homePage.scrollBody(350);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - List Withdraw " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Then("I hit Export Button on DISI List Withdraw")
    public void i_hit_export_button_on_disi_list_withdraw() {
        homePage.scrollBody(-250);
        disiListWithdrawPage.hitBtnExport();
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "M&M - List Withdraw " + countImage);
        countImage++;
        sobHelper.delay(3000);
        assertEquals(Helper.check_file_exist("list-withdraw.xlsx"), "File Present");
    }

    @Then("I {string} Advice Suspect Withdraw Transaction")
    public void i_advice_suspect_withdraw_transaction(String condition) {
        if (condition.equals("Success")) {
            assertTrue(disiListWithdrawPage.getSuccessAlert());
        } else if (condition.equals("Pending")) {
            assertTrue(disiListWithdrawPage.getPendingAlert());
        } else if (condition.equals("Failed")) {
            assertTrue(disiListWithdrawPage.getFailedAlert());
        }

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - " + condition + " Alert");
    }

    public void setUpDisiListWithdraw() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        disiListWithdrawPage = new SOBDisiListWithdrawPages(driver);
    }
}
