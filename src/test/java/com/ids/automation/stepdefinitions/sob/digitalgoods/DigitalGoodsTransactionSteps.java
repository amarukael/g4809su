package com.ids.automation.stepdefinitions.sob.digitalgoods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;

import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.sob.SOBGlobalPages;
import pages.sob.SOBHomePages;
import pages.sob.digitalgoods.SOBDigitalGoodsTransactionPages;
import utility.Helper;

public class DigitalGoodsTransactionSteps {
    static WebDriver driver;
    SOBLoginSteps loginSteps = new SOBLoginSteps();
    SOBGlobalPages globalPages;
    SOBHomePages homePage;
    SOBHelper sobHelper = new SOBHelper();
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;
    Integer countImage = 1;
    SOBDigitalGoodsTransactionPages dgmsTransactionPage;

    public void setUpdgmsTransaction() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        globalPages = new SOBGlobalPages(driver);
        dgmsTransactionPage = new SOBDigitalGoodsTransactionPages(driver);
    }

    @Then("I {string} navigate to DigitalGoods Transaction")
    public void iNavigateToDigitalGoodsTransaction(String arg0) {
        setUpdgmsTransaction();
        homePage.MenuDrawer();
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Homepage");
        homePage.openNavMenu("Digital Goods", 0, 250, "smooth");
        sobHelper.delay(1000);
        homePage.openNavSubMenu("Transactions");
        sobHelper.delay(800);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DigitalGoods - Transaction " + arg0);
        homePage.MenuDrawer();
        sobHelper.delay(1000);
    }

    @Given("I am in DigitalGoods Transaction")
    public void iAmInDigitalGoodsTransaction() {
        loginSteps.setUp();
        loginSteps.setUpLogin("adminqa2", "digitalgoods_transactions");
        setUpdgmsTransaction();
        homePage.MenuDrawer();
        homePage.openNavMenu("Digital Goods", 0, 250, "smooth");
        homePage.openNavSubMenu("Transactions");
        sobHelper.delay(800);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DigitalGoods - Transaction");
        homePage.MenuDrawer();
        sobHelper.delay(1000);
    }

    @Given("DGMS show Introduction Feature")
    public void dgmsShowIntroductionFeature() {
        sobHelper.delay(500);
        dgmsTransactionPage.introPage();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DigitalGoods - Transaction");
        sobHelper.delay(500);
    }


    @Then("I Check Functional Introduction Tab")
    public void iCheckFunctionalIntroductionTab() {
        sobHelper.delay(500);
        dgmsTransactionPage.nextIntroBtn();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DigitalGoods - Transaction Intro Tab");
        sobHelper.delay(1000);
        dgmsTransactionPage.backIntroBtn();
        sobHelper.delay(1000);
        dgmsTransactionPage.nextIntroBtn();
        sobHelper.delay(1000);
        dgmsTransactionPage.closeIntroBtn();
        sobHelper.delay(500);
    }

    @Then("I Check Functional Introduction Transaction")
    public void iCheckFunctionalIntroductionTransaction() {
        sobHelper.delay(500);
        performAction(2, "next", true, false);
        performAction(4, "back", false, true);
        performAction(2, "next", false, false);
        dgmsTransactionPage.closeIntroBtn();
        sobHelper.delay(500);
    }

    @When("DGMS not show Introduction Feature")
    public void DGMS_not_show_Introduction_Feature() {
        String data = "[{\"username\":\"adminqa2\",\"menu\":\"/dashboard/dgms/transaction\"}," +
                "{\"username\":\"adminqa2\",\"menu\":\"/dashboard/dgms/transaction/list\"}," +
                "{\"username\":\"adminqa2\",\"menu\":\"/dashboard/dgms/transaction/pending\"}]";
        ((JavascriptExecutor) driver).executeScript(String.format(
                "window.localStorage.setItem('opened-menu', '%s');", data));
        String storedValue = (String) ((JavascriptExecutor) driver).executeScript(
                "return window.localStorage.getItem('opened-menu');");
        System.out.println("Sukses Inject Data : " + storedValue);
        driver.navigate().refresh();
        sobHelper.delay(1000);
        try {
            dgmsTransactionPage.introPage();
            throw new Exception("Introduction page shown");
        } catch (Exception e) {
            System.out.println("Intro tidak muncul");
        }
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DigitalGoods - Transaction");
        sobHelper.delay(1000);
    }

    @Then("I hit Filter Button on DGMS Transaction")
    public void I_hit_Filter_Button_on_DGMS_Transaction() {
        dgmsTransactionPage.hitBtnFilter();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DGMS - Transaction "
                + countImage);
        countImage++;
        sobHelper.delay(100);
    }

    @Given("I am in DigitalGoods Transaction without Intro")
    public void I_am_in_DigitalGoods_Transaction_without_Intro() {
        loginSteps.setUp();
        loginSteps.setUpLogin("adminqa2", "digitalgoods_transactions");
        setUpdgmsTransaction();
        homePage.MenuDrawer();
        homePage.openNavMenu("Digital Goods", 0, 250, "smooth");
        homePage.openNavSubMenu("Transactions");
        sobHelper.delay(800);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DigitalGoods - Transaction");
        homePage.MenuDrawer();
        sobHelper.delay(1000);
    }

    private void performAction(int scrollAt, String action, boolean takeScreenshot, boolean scrollToTop) {
        for (int i = 0; i < 7; i++) {
            if ("next".equals(action)) {
                dgmsTransactionPage.nextIntroBtn();
            } else if ("back".equals(action)) {
                dgmsTransactionPage.backIntroBtn();
            }
            if (takeScreenshot) {
                scenario.attach(Helper.takeScreenshot(driver), "image/png", "DigitalGoods - Transaction Intro Transaction");
            }
            sobHelper.delay(1000);
            if (i == scrollAt) {
                ((JavascriptExecutor) driver).executeScript(scrollToTop ? "window.scrollTo(0,0)" : "window.scrollTo(0, document.body.scrollHeight/3)");
                sobHelper.delay(1000);
            }
        }
    }

    @Given("I fill From Date \\({string}) on DGMS Transaction")
    public void i_fill_from_date_on_dgms_transaction(String sDate) {
        String[] value = sDate.split(",");
        dgmsTransactionPage.fillFromDate(value);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Filter Date");
        sobHelper.delay(500);
    }

    @Given("I fill To Date \\({string}) on DGMS Transaction")
    public void i_fill_to_date_on_dgms_transaction(String sDate) {
        String[] value = sDate.split(",");
        dgmsTransactionPage.fillToDate(value);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Filter Date");
        sobHelper.delay(500);
    }

    @Then("I fill From Date \\({string}) and To Date \\({string}) on DGMS Transaction")
    public void iFillFromDateAndToDateOnDGMSTransaction(String arg0, String arg1) {
        i_fill_from_date_on_dgms_transaction(arg0);
        i_fill_to_date_on_dgms_transaction(arg1);
    }

    @Then("I Click Field {string} and Choose {string} on DGMS Transaction")
    public void iClickFieldAndChooseonDGMSTransaction(String arg0, String arg1) throws Exception {
        sobHelper.delay(500);
        dgmsTransactionPage.fieldFilter(arg0, arg1);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Filter Data");
        sobHelper.delay(500);
    }

    @Then("DGMS Datatable show data Transaction")
    public void dgmsDatatableShowDataTransaction() throws Exception {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight/3)");
        sobHelper.delay(1000);
        globalPages.waitDatatableAppear();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DGMS - Transaction "
                + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Then("I Hit Detail Transaction on Row {string}")
    public void iHitDetailTransactionOnRow(String arg0) throws Exception {
        sobHelper.delay(500);
        globalPages.scrollDataTabletoLeft(1200);
        dgmsTransactionPage.detailTrx(arg0);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DGMS - Transaction "
                + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Then("I Choose {string} as Partner on DGMS Transaction")
    public void iChooseAsPartnerOnDGMSTransaction(String arg0) {
        sobHelper.delay(500);
        dgmsTransactionPage.selectDropdownFilter("mui-component-select-partnerId", arg0);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Choose Partner");
        sobHelper.delay(500);
    }

    @Then("I Hit see more on Top Product")
    public void iHitSeeMoreOnTopProduct() throws Exception {
        sobHelper.delay(500);
        dgmsTransactionPage.seeMoreButton();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DGMS - Transaction "
                + countImage);
        countImage++;
        sobHelper.delay(500);
    }

    @Then("I Hit Button Force {string} on Row {string} DGMS Transaction")
    public void iHitButtonForceOnRowDGMSTransaction(String arg0, String arg1) throws Exception {
        sobHelper.delay(500);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight/3)");
        sobHelper.delay(500);
        globalPages.scrollDataTabletoLeft(1200);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DGMS - Transaction "
                + countImage);
        countImage++;
        if (arg0.equalsIgnoreCase("SUCCESS")){
            dgmsTransactionPage.forceSuccess(arg1);
        } else if (arg0.equalsIgnoreCase("FAILED")) {
            dgmsTransactionPage.forceFailed(arg1);
        }else {
            throw new Exception("Whats button?");
        }
        sobHelper.delay(1000);
    }

    @Then("I Fill Receipt with {string} and Code with {string} and Confirm")
    public void iFillReceiptWithAndCodeWithAndConfirm(String arg0, String arg1) {
        setUpdgmsTransaction();
        sobHelper.delay(500);
        dgmsTransactionPage.modalTransactionApproval(arg0,arg1);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DGMS - Transaction"
                + countImage);
        countImage++;
        sobHelper.delay(500);
        dgmsTransactionPage.modalStatusConfirmation();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DGMS - Transaction"
                + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Then("I Hit All Confirmation on DGMS Transaction")
    public void iHitAllConfirmationOnDGMSTransaction() throws Exception {
        setUpdgmsTransaction();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DGMS - Transaction"
                + countImage);
        countImage++;
        sobHelper.delay(500);
        dgmsTransactionPage.modalTransactionCancellation("YES");
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DGMS - Transaction"
                + countImage);
        countImage++;
        sobHelper.delay(500);
        dgmsTransactionPage.modalStatusConfirmation();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DGMS - Transaction"
                + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Then("DGMS Show Alert {string}")
    public void dgmsShowAlert(String arg0) throws Exception {
        setUpdgmsTransaction();
        if (arg0.equalsIgnoreCase("success")){
            dgmsTransactionPage.alertSuccess();
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "DGMS - Transaction"
                    + countImage);
            countImage++;
            sobHelper.delay(1000);
        } else if (arg0.equalsIgnoreCase("failed")) {
            dgmsTransactionPage.alertFailedDataNotFound();
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "DGMS - Transaction"
                    + countImage);
            countImage++;
            sobHelper.delay(1000);
        } else {
            throw new Exception("Alert not define!");
        }


    }
}
