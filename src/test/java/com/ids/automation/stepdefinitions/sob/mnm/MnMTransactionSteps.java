package com.ids.automation.stepdefinitions.sob.mnm;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;
import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.sob.SOBHomePages;
import pages.sob.mnm.SOBMnMTransactionPages;
import utility.Helper;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MnMTransactionSteps {
    static WebDriver driver;
    SOBLoginSteps loginSteps = new SOBLoginSteps();
    SOBHomePages homePage;
    SOBHelper sobHelper = new SOBHelper();
    SOBMnMTransactionPages mnmTransactionPage;
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;
    Integer countImage = 1;

    @Then("I {string} navigate to M&M Transaction")
    public void i_navigate_to_m_m_send_transaction(String condition) {
        setUpMandMTransaction();
        homePage.MenuDrawer();
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Homepage");

        // condition check
        if (condition.equals("Successfully")) {
            homePage.openNavMenu("M&M", 0, 450, "smooth");
            sobHelper.delay(1500);
            homePage.openNavSubMenu("Transaction");
            sobHelper.delay(1000);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "M&M - Transaction");
            homePage.MenuDrawer();
            sobHelper.delay(1000);
            assertEquals(mnmTransactionPage.getLblTransaction(), "Transaction");
        } else {
            assertTrue(homePage.checkNoNavMenu("M&M"));
        }

        sobHelper.delay(1000);
    }

    @Given("I am in M&M Transaction Page")
    public void i_am_in_mm_transaction_Page() {
        loginSteps.setUp();
        loginSteps.setUpLogin("ryormd", "m&m_transaction");
        setUpMandMTransaction();
        homePage.MenuDrawer();
        homePage.openNavMenu("M&M", 0, 450, "smooth");
        homePage.openNavSubMenu("Transaction");
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "M&M - Transaction");
        homePage.MenuDrawer();
        sobHelper.delay(1000);
    }

    @Given("I hit Filter Button on M&M Transaction")
    public void i_hit_filter_button_on_mm_transaction() {
        mnmTransactionPage.hitBtnFilter();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "M&M - Transaction " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @When("I fill From Date on Filter Field on M&M Transaction")
    public void i_fill_from_date_on_filter_field_on_mm_transaction() {
        String[] valueDate = {"25", "03"};
        mnmTransactionPage.fillFromDate(valueDate);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "M&M - Transaction " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @When("I choose {string} From List Box Status on Filter Field on M&M Transaction")
    public void i_choose_from_list_box_status_on_filter_field_on_mm_transaction(String status) {
        mnmTransactionPage.displayListBoxStatus();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "M&M - Transaction " + countImage);
        countImage++;
        sobHelper.delay(1000);
        mnmTransactionPage.fillStatus(status);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "M&M - Transaction " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Then("I hit Apply Button on M&M Transaction")
    public void i_hit_apply_button_on_mm_transaction() {
        mnmTransactionPage.hitBtnApply();
        sobHelper.delay(800);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "M&M - Transaction " + countImage);
        countImage++;
        sobHelper.delay(800);
        mnmTransactionPage.scrollDatatable();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "M&M - Transaction " + countImage);
        countImage++;
        sobHelper.delay(1000);
        homePage.scrollBody(350);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "M&M - Transaction " + countImage);
        countImage++;
        String countData = mnmTransactionPage.getCountData();
        scenario.log("Data: " + countData);
        sobHelper.delay(1000);
    }

    @Then("I hit Export Button on M&M Transaction")
    public void i_hit_export_button_on_mm_transaction() {
        homePage.scrollBody(-250);
        mnmTransactionPage.hitBtnExport();
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "M&M - Transaction " + countImage);
        countImage++;
        sobHelper.delay(3000);
        assertEquals(Helper.check_file_exist("Mnm-transaction.csv"), "File Present");
    }

    public void setUpMandMTransaction() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        mnmTransactionPage = new SOBMnMTransactionPages(driver);
    }
}
