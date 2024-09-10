package com.ids.automation.stepdefinitions.sob.dana;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;
import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.sob.SOBGlobalPages;
import pages.sob.SOBHomePages;
import pages.sob.cico.SOBCicoTransactionPages;
import pages.sob.dana.SOBDanaTransactionPages;
import pages.sob.dana.SOBDanaTransactionSusPages;
import utility.Helper;

import static org.testng.Assert.*;

public class DanaTransactionSteps {
    static WebDriver driver;
    SOBLoginSteps loginSteps = new SOBLoginSteps();
    SOBHomePages homePage;
    SOBGlobalPages globalPages;
    SOBDanaTransactionPages danaTransactionPages;
    SOBHelper sobHelper = new SOBHelper();
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;
    Integer countImage = 1;

    public void setUpTransaction() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        danaTransactionPages = new SOBDanaTransactionPages(driver);
    }

    @Then("I Click Field {string} and Fill With {string} on DANA Transaction")
    public void iClickFieldAndFillWithOnDANATransaction(String arg0, String arg1) {
        setUpTransaction();
        sobHelper.delay(500);
        danaTransactionPages.fieldFilter(arg0, arg1);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "CICO - Transaction Field " + arg0);
        sobHelper.delay(500);
    }


    @Then("I fill all fields on filter DANA Transaction")
    public void iFillAllFieldsOnFilterDANATransaction() {
        setUpTransaction();
        String[] field = {"Tracking Reff", "Acquisition ID", "Customer ID",
                "Product Name", "Ref/Token", "Amount", "Status"};
        String[] data = {"20240716025249100513323", "20240716111212800100166534918028351", "131238113323",
                "TELKOM", "20240716025249100513323", "911590", "Success"};

        int numField = field.length;

        for (int i = 0; i < numField; i++) {
            sobHelper.delay(500);
            danaTransactionPages.fieldFilter(field[i], data[i]);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "CICO - Transaction"
                    + countImage);
            countImage++;
            sobHelper.delay(500);
        }
    }
}
