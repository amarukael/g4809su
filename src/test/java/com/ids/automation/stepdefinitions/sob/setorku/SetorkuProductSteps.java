package com.ids.automation.stepdefinitions.sob.setorku;

import org.openqa.selenium.WebDriver;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;

import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import pages.sob.SOBGlobalPages;
import pages.sob.SOBHomePages;
import utility.Helper;

public class SetorkuProductSteps {
    static WebDriver driver;
    SOBHomePages homePage;
    SOBGlobalPages globalPages;
    SOBHelper sobHelper = new SOBHelper();
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;
    Integer countImage = 1;

    public void setUpProduct() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        globalPages = new SOBGlobalPages(driver);
    }

    @Then("I click field {string} and fill with {string} on Setorku Product")
    public void i_click_field_and_fill_with_on_setorku_partner(String arg0, String arg1) {
        setUpProduct();
        String[] value = arg1.split(",");
        sobHelper.delay(500);
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

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Field : " + arg0 + "\nValue : " + arg1);
        sobHelper.delay(500);
    }
}
