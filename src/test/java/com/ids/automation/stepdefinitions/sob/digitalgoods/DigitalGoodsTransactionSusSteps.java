package com.ids.automation.stepdefinitions.sob.digitalgoods;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;
import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pages.sob.SOBGlobalPages;
import pages.sob.digitalgoods.SOBDigitalGoodsTransactionSusPages;
import pages.sob.SOBHomePages;
import utility.Helper;

public class DigitalGoodsTransactionSusSteps {
    static WebDriver driver;
    SOBLoginSteps loginSteps = new SOBLoginSteps();
    SOBHomePages homePage;
    SOBGlobalPages globalPages;
    SOBDigitalGoodsTransactionSusPages transSusPage;
    SOBHelper sobHelper = new SOBHelper();
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;
    Integer countImage = 1;

    public void setUpTransSus() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        globalPages = new SOBGlobalPages(driver);
        transSusPage = new SOBDigitalGoodsTransactionSusPages(driver);
    }

    @Then("I {string} navigate to DigitalGoods Transaction Pending")
    public void iNavigateToDigitalGoodsTransactionPending(String arg0) {
        setUpTransSus();
        String data = "[{\"username\":\"adminqa2\",\"menu\":\"/dashboard/dgms/transaction\"},{\"username\":\"adminqa2\",\"menu\":\"/dashboard/dgms/transaction/list\"}]";
        ((JavascriptExecutor) driver).executeScript(String.format("window.localStorage.setItem('opened-menu', '%s');", data));
        String storedValue = (String) ((JavascriptExecutor) driver).executeScript("return window.localStorage.getItem('opened-menu');");
        System.out.println("Sukses Inject Data : " + storedValue);
        driver.navigate().refresh();
        sobHelper.delay(1000);
        homePage.MenuDrawer();
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Homepage");
        homePage.openNavMenu("Digital Goods", 0, 250, "smooth");
        sobHelper.delay(1000);
        homePage.openNavSubMenu("Transactions");
        sobHelper.delay(800);
        homePage.MenuDrawer();
        transSusPage.transPendButton();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DigitalGoods - Transaction " + arg0);
        sobHelper.delay(1000);
    }


    @Then("I Check Functional Introduction Transaction Pending")
    public void iCheckFunctionalIntroductionTransactionPending() {
        setUpTransSus();
        String data = "[{\"username\":\"adminqa2\",\"menu\":\"/dashboard/dgms/transaction\"},{\"username\":\"adminqa2\",\"menu\":\"/dashboard/dgms/transaction/list\"}]";
        ((JavascriptExecutor) driver).executeScript(String.format("window.localStorage.setItem('opened-menu', '%s');", data));
        String storedValue = (String) ((JavascriptExecutor) driver).executeScript("return window.localStorage.getItem('opened-menu');");
        System.out.println("Sukses Inject Data : " + storedValue);
        driver.navigate().refresh();
        sobHelper.delay(1000);
        transSusPage.transPendButton();
        sobHelper.delay(500);
        for (int i = 0; i < 3; i++) {
            if (i==1){
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight/3)");
            }
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "DigitalGoods - Transaction");
            transSusPage.nextIntroBtn();
            sobHelper.delay(1000);
        }
        for (int i = 0; i < 3; i++) {
            if (i==2){
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight/0)");
            }
            transSusPage.backIntroBtn();
            sobHelper.delay(500);
        }
        for (int i = 0; i < 3; i++) {
            if (i==1){
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight/3)");
            }
            transSusPage.nextIntroBtn();
            sobHelper.delay(500);
        }
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DigitalGoods - Transaction");
        sobHelper.delay(500);
        transSusPage.closeIntroBtn();
        sobHelper.delay(500);
    }

    @Given("I fill From Date \\({string}) on DGMS Transaction Pending")
    public void i_fill_from_date_on_dgms_transaction_pending(String sDate) {
        String[] value = sDate.split(",");
        transSusPage.fillFromDate(value);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Filter Date");
        sobHelper.delay(500);
    }

    @Given("I fill To Date \\({string}) on DGMS Transaction Pending")
    public void i_fill_to_date_on_dgms_transaction_pending(String sDate) {
        String[] value = sDate.split(",");
        transSusPage.fillToDate(value);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Filter Date");
        sobHelper.delay(500);
    }

    @Then("I fill From Date \\({string}) and To Date \\({string}) on DGMS Transaction Pending")
    public void iFillFromDateAndToDateOnDGMSTransactionPending(String arg0, String arg1) {
        i_fill_from_date_on_dgms_transaction_pending(arg0);
        i_fill_to_date_on_dgms_transaction_pending(arg1);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DigitalGoods - Transaction");
        sobHelper.delay(500);
    }

    @Given("I am in DigitalGoods Transaction Pending")
    public void iAmInDigitalGoodsTransactionPending() {
        loginSteps.setUp();
        loginSteps.setUpLogin("adminqa2", "digitalgoods_transactions");
        sobHelper.delay(1000);
        setUpTransSus();
        String data = "[{\"username\":\"adminqa2\",\"menu\":\"/dashboard/dgms/transaction\"}," +
                "{\"username\":\"adminqa2\",\"menu\":\"/dashboard/dgms/transaction/list\"}," +
                "{\"username\":\"adminqa2\",\"menu\":\"/dashboard/dgms/transaction/pending\"}]";
        ((JavascriptExecutor) driver).executeScript(String.format(
                "window.localStorage.setItem('opened-menu', '%s');", data));
        String storedValue = (String) ((JavascriptExecutor) driver).executeScript(
                "return window.localStorage.getItem('opened-menu');");
        System.out.println("Sukses Inject Data : " + storedValue);
        driver.navigate().refresh();
        homePage.MenuDrawer();
        homePage.openNavMenu("Digital Goods", 0, 250, "smooth");
        homePage.openNavSubMenu("Transactions");
        sobHelper.delay(800);
        transSusPage.transPendButton();
        sobHelper.delay(500);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DigitalGoods - Transaction Pending");
        homePage.MenuDrawer();
        sobHelper.delay(1000);
    }

    @Then("DGMS Datatable show data Transaction Pending")
    public void dgmsDatatableShowDataTransactionPending() throws Exception {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight/2.5)");
        sobHelper.delay(1000);
        globalPages.waitDatatableAppear();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Dana - Transaction Suspect "
                + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Then("I Click Field {string} and Fill {string} on DGMS Transaction Pending")
    public void iClickFieldAndFillOnDGMSTransactionPending(String arg0, String arg1) throws Exception {
        sobHelper.delay(500);
        transSusPage.fieldFilter(arg0, arg1);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DigitalGoods - Transaction");
        sobHelper.delay(500);
    }

    @Then("I Hit Button Force {string} on Row {string} DGMS Transaction Pending")
    public void iHitButtonForceOnRowDGMSTransaction(String arg0, String arg1) throws Exception {
        sobHelper.delay(500);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight/2.6)");
        sobHelper.delay(500);
        globalPages.scrollDataTabletoLeft(2000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DGMS - Transaction "
                + countImage);
        countImage++;
        if (arg0.equalsIgnoreCase("SUCCESS")){
            transSusPage.forceSuccess(arg1);
        } else if (arg0.equalsIgnoreCase("FAILED")) {
            transSusPage.forceFailed(arg1);
        }else {
            throw new Exception("Whats button?");
        }
        sobHelper.delay(1000);
    }

    @Then("I Choose Reject Reason with RC {string}")
    public void iChooseRejectReasonWithRC(String arg0) {
        sobHelper.delay(500);
        transSusPage.dropdownRejectReason(arg0);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DGMS - Transaction "
                + countImage);
        countImage++;
        sobHelper.delay(500);
        globalPages.submitButton();
        sobHelper.delay(500);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DGMS - Transaction "
                + countImage);
        countImage++;
        transSusPage.modalStatusConfirmation();
        sobHelper.delay(500);
    }
}
