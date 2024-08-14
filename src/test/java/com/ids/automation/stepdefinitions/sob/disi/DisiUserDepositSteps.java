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
import pages.sob.disi.SOBDisiUserDepositPages;
import utility.Helper;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DisiUserDepositSteps {
    static WebDriver driver;
    SOBLoginSteps loginSteps = new SOBLoginSteps();
    SOBHomePages homePage;
    SOBHelper sobHelper = new SOBHelper();
    SOBDisiUserDepositPages disiUserDepositPage;
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;

    @Given("I am in DISI User Deposit Page")
    public void i_am_in_disi_user_deposit_page() {
        loginSteps.setUp();
        loginSteps.setUpLogin("pettycashpartner", "disi_user_deposit");
        setUpDisiUserDeposit();
        homePage.MenuDrawer();
        homePage.openNavMenu("Deposit Sistem", 0, 450, "smooth");
        homePage.openNavSubMenu("User Deposit");
        sobHelper.delay(2000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - User Deposit");
        homePage.MenuDrawer();
        sobHelper.delay(2000);
    }

    @Given("I hit Filter Button on DISI User Deposit")
    public void i_hit_filter_button_on_disi_user_deposit() {
        disiUserDepositPage.hitBtnFilter();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - User Deposit 2");
        sobHelper.delay(1000);
    }

    @Given("I choose {string} in Merchant Name Field")
    public void i_choose_in_merchant_name_field(String merchantNm) {
        disiUserDepositPage.getMerchantNmField(merchantNm);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - User Deposit 3");
        sobHelper.delay(1000);
        disiUserDepositPage.hitBtnApply();
        sobHelper.delay(2000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - User Deposit 4");
        disiUserDepositPage.hitBtnDetail();
        sobHelper.delay(1000);
    }

    @Given("I hit Filter Button on DISI User Deposit Detail")
    public void i_hit_filter_button_on_disi_user_deposit_detail() {
        disiUserDepositPage.hitBtnFilterDetail();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - User Deposit 5");
        sobHelper.delay(1000);
    }

    @When("I fill From Date on Filter Field on DISI User Deposit Detail")
    public void i_fill_from_date_on_filter_field_on_disi_user_deposit_detail() {
        String[] valueDate = {"01", "12"};
        disiUserDepositPage.fillFromDateWithYear(valueDate);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - User Deposit 6");
        sobHelper.delay(1000);
        // Get value from date and to date
        assertTrue(disiUserDepositPage.checkValueFromToDate());
    }

    @Then("I {string} navigate to DISI User Deposit")
    public void i_navigate_to_disi_user_deposit(String condition) {
        setUpDisiUserDeposit();
        homePage.MenuDrawer();
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Homepage");

        // condition check
        if (condition.equals("Successfully")) {
            homePage.openNavMenu("Deposit Sistem", 0, 450, "smooth");
            sobHelper.delay(1500);
            homePage.openNavSubMenu("User Deposit");
            sobHelper.delay(1000);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Disi - User Deposit");
            homePage.MenuDrawer();
            sobHelper.delay(2000);
            assertEquals(disiUserDepositPage.getLblMerchantDepo(), "User Deposit List");
        } else {
            assertTrue(homePage.checkNoNavMenu("Deposit Sistem"));
        }

        sobHelper.delay(1000);
    }

    @Then("I hit Apply Button on DISI User Deposit Detail")
    public void i_hit_apply_button_on_disi_user_deposit_detail() {
        disiUserDepositPage.hitBtnApply();
        sobHelper.delay(2000);
        homePage.scrollBody(120);
        sobHelper.delay(2000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - User Deposit 7");
        sobHelper.delay(1000);
    }

    public void setUpDisiUserDeposit() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        disiUserDepositPage = new SOBDisiUserDepositPages(driver);
    }
}
