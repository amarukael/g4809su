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
import pages.sob.disi.SOBDisiPettyCashPages;
import utility.Helper;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DisiPettyCashSteps {
    static WebDriver driver;
    SOBLoginSteps loginSteps = new SOBLoginSteps();
    SOBHomePages homePage;
    SOBHelper sobHelper = new SOBHelper();
    SOBDisiPettyCashPages disiPettyCashPage;
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;

    @Given("I am in DISI Petty Cash Page")
    public void i_am_in_disi_petty_cash_page() {
        loginSteps.setUp();
        loginSteps.setUpLogin("pettycashpartner", "disi_petty_cash");
        setUpDisiPettyCash();
        homePage.MenuDrawer();
        homePage.openNavMenu("Deposit Sistem", 0, 450, "smooth");
        homePage.openNavSubMenu("Petty Cash");
        sobHelper.delay(5000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - Petty Cash");
        homePage.MenuDrawer();
        sobHelper.delay(2000);
    }

    @Given("I hit Filter Button on DISI Petty Cash")
    public void i_hit_filter_button_on_disi_petty_cash() {
        disiPettyCashPage.hitBtnFilter();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - Petty Cash 1");
        sobHelper.delay(1000);
    }

    @When("I fill From Date on Filter Field on DISI Petty Cash")
    public void i_fill_from_date_on_filter_field_on_disi_petty_cash() {
        String[] valueDate = {"01", "12"};
        disiPettyCashPage.fillFromDateWithYear(valueDate);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - Petty Cash 2");
        sobHelper.delay(1000);
        // Get value from date and to date
        assertTrue(disiPettyCashPage.checkValueFromToDate());
    }

    @Then("I {string} navigate to DISI Petty Cash")
    public void i_navigate_to_disi_petty_cash(String condition) {
        setUpDisiPettyCash();
        homePage.MenuDrawer();
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Homepage");

        // condition check
        if (condition.equals("Successfully")) {
            homePage.openNavMenu("Deposit Sistem", 0, 450, "smooth");
            sobHelper.delay(1500);
            homePage.openNavSubMenu("Petty Cash");
            sobHelper.delay(5000);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Disi - Petty Cash");
            homePage.MenuDrawer();
            sobHelper.delay(2000);
            assertEquals(disiPettyCashPage.getLblPettyCash(), "Petty Cash");
        } else {
            assertTrue(homePage.checkNoNavMenu("Deposit Sistem"));
        }

        sobHelper.delay(1000);
    }

    @Then("I hit Apply Button on DISI Petty Cash")
    public void i_hit_apply_button_on_disi_petty_cash() {
        disiPettyCashPage.hitBtnApply();
        sobHelper.delay(1000);
        homePage.scrollBody(170);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - Petty Cash 3");
        sobHelper.delay(1000);
    }

    public void setUpDisiPettyCash() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        disiPettyCashPage = new SOBDisiPettyCashPages(driver);
    }
}
