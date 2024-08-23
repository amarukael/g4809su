package com.ids.automation.stepdefinitions.sob.setorku;

import org.openqa.selenium.WebDriver;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;

import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.sob.SOBGlobalPages;
import pages.sob.SOBHomePages;
import pages.sob.setorku.SOBSetorkuPaymentCodeListPages;
import utility.Helper;

public class SetorkuPaymentCodeListSteps {
    static WebDriver driver;
    SOBHomePages homePage;
    SOBGlobalPages globalPages;
    SOBSetorkuPaymentCodeListPages paymentCodeListPages;
    SOBHelper sobHelper = new SOBHelper();
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;
    Integer countImage = 1;

    public void setUpPaymentCodeList() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        globalPages = new SOBGlobalPages(driver);
        paymentCodeListPages = new SOBSetorkuPaymentCodeListPages(driver);
    }

    @Then("I click field {string} and fill with {string} on Setorku Payment Code List")
    public void i_click_field_and_fill_with_on_setorku_partner(String arg0, String arg1) {
        setUpPaymentCodeList();
        String[] value = arg1.split(",");
        sobHelper.delay(500);
        if (arg0.equalsIgnoreCase("Product Name")) {
            if (!arg1.isEmpty()) {
                globalPages.dropList(arg0, arg1);
            }
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

    @Given("I click detail Payment Code List on row {string}")
    public void I_click_detail_Payment_Code_List_on_row(String s) {
        setUpPaymentCodeList();
        paymentCodeListPages.detailPaymentCode(s);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Detail payment Code");
        sobHelper.delay(1000);
    }

}
