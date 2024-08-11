package com.ids.automation.stepdefinitions.sob.cico;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;
import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.sob.SOBHomePages;
import pages.sob.cico.SOBCicoTransactionPages;
import utility.Helper;

public class CicoTransactionSteps {
    static WebDriver driver;
    SOBLoginSteps loginSteps = new SOBLoginSteps();
    SOBHomePages homePage;
    SOBHelper sobHelper = new SOBHelper();
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;
    Integer countImage = 1;
    SOBCicoTransactionPages cicoTransactionPages;


    public void setUpTransaction() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        cicoTransactionPages = new SOBCicoTransactionPages(driver);
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
    public void iClickFieldAndFillWithOnCICOTransaction(String arg0, String arg1) {
        setUpTransaction();
        sobHelper.delay(500);
        cicoTransactionPages.fieldFilter(arg0, arg1);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "CICO - Transaction Field " + arg0);
        sobHelper.delay(500);
    }

    @Then("I fill all fields on filter CICO Transaction")
    public void iFillAllFieldsOnFilterCICOTransaction() {
        setUpTransaction();
        String[] field = {"BCI Transaction ID", "Partner Transaction ID", "Partner Name",
                "Merchant Transaction ID", "Merchant Name", "Customer ID", "Customer Name",
                "Token", "Transaction Type", "Nominal", "Status"};
        String[] data = {"IBTT0561704438438174","IDM20240105155344099","IDM","TTPB2401051553250102",
                "INDOMARET","085921567404","RIYANO CARDIN","6815009403","CASHOUT","50000","SUCCESS"};

        int numField = field.length;

        for (int i = 0; i < numField; i++) {
            sobHelper.delay(500);
            cicoTransactionPages.fieldFilter(field[i], data[i]);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "CICO - Transaction"
                    + countImage);
            countImage++;
            sobHelper.delay(500);
        }
    }
}
