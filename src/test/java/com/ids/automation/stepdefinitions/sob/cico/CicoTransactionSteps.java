package com.ids.automation.stepdefinitions.sob.cico;

import org.openqa.selenium.WebDriver;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;

import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import pages.sob.SOBGlobalPages;
import pages.sob.SOBHomePages;
import pages.sob.cico.SOBCicoTransactionPages;
import utility.Helper;

public class CicoTransactionSteps {
    static WebDriver driver;
    SOBGlobalPages globalPages;
    SOBHomePages homePage;
    SOBHelper sobHelper = new SOBHelper();
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;
    SOBCicoTransactionPages cicoTransactionPages;

    public void setUpTransaction() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        cicoTransactionPages = new SOBCicoTransactionPages(driver);
        globalPages = new SOBGlobalPages(driver);
    }

    @Then("I {string} navigate to CICO Transaction")
    public void i_navigate_to_CICO_transaction(String condition) {
        setUpTransaction();
        homePage.MenuDrawer();
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Homepage");
        homePage.openNavMenu("CICO", 0, 250, "smooth");
        sobHelper.delay(1000);
        homePage.openNavSubMenu("Transaction List");
        sobHelper.delay(800);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "CICO - Transaction --" + condition);
        homePage.MenuDrawer();
        sobHelper.delay(1000);
    }

    @Then("I Click Field {string} and Fill With {string} on CICO Transaction")
    public void i_click_field_and_fill_with_on_cico_transaction(String arg0, String arg1) {
        setUpTransaction();
        String[] value = arg1.split(",");
        sobHelper.delay(500);

        if (arg0.equalsIgnoreCase("Status")) {
            globalPages.dropList(arg0, arg1);
        } else {
            if (value[0].equalsIgnoreCase("random")) {
                arg1 = globalPages.inputText(arg0, Helper.generateRandomString(Integer.parseInt(value[1])));
            } else if (value[0].equalsIgnoreCase("randomCase")) {
                arg1 = globalPages.inputText(arg0, Helper.randomCase(value[1]));
            } else if (value[0].equalsIgnoreCase("number")) {
                arg1 = globalPages.inputText(arg0, Helper.randomString2(Integer.parseInt(value[1])));
            } else if (value[0].equals("space")) {
                String spaces = " ".repeat(Integer.parseInt(value[1]));
                arg1 = globalPages.inputText(arg0, spaces);
            } else {
                globalPages.inputText(arg0, value[0]);
            }
        }

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Field : " + arg0 + "\nValue : " + arg1);
        sobHelper.delay(500);
    }
}
