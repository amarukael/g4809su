package com.ids.automation.stepdefinitions.solusipayweb.globalSteps;

import com.ids.automation.configuration.BrowserSetup;
import constant.SolusipayWebConstant;
import helper.SolusipayWebHelper;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.solusipayweb.globalpages.SolusipayWebPages;
import utility.Helper;
import io.cucumber.java.Scenario;

public class SolusipayWebSteps {
    public static Scenario scenario;
    static WebDriver driver;
    static WebDriverWait wait;
    SolusipayWebHelper solpayWebHelper = new SolusipayWebHelper();
    SolusipayWebConstant solusipayWebConstant;
    private byte[] screenshotData;
    SolusipayWebPages solpayWeb;

    @Before
    public void afterScenario(Scenario scenario) {
        SolusipayWebSteps.scenario = scenario;
    }

    @Given("I open solusipayweb page")
    public void iOpenSolusipayWebPage(){
        setUpSolusipayWeb();
        solpayWeb.vrfyElementSolpayWebPage();
        solpayWebHelper.delay(3000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page");
        solpayWebHelper.delay(1000);
    }

    public void setUpSolusipayWeb(){
        driver = BrowserSetup.getDriver();
        driver.get(SolusipayWebConstant.UrlSolpayWeb);
        if(solpayWeb == null){
            solpayWeb = new SolusipayWebPages(driver);
        }
    }

    public void setUpSolusipayWeb2(){
        driver = BrowserSetup.getDriver();
        if(solpayWeb == null){
            solpayWeb = new SolusipayWebPages(driver);
        }
    }

    @When("I click back button")
    public void iClickBackButton(){
        solpayWeb.hitBackButton();
        solpayWebHelper.delay(850);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Hit back button");
    }

    @When("I click buy button")
    public void iClickBuyButton(){
        setUpSolusipayWeb2();
        solpayWeb.hitBuyButton();
        solpayWebHelper.delay(500);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Hit buy button");
        solpayWebHelper.delay(2000);
    }

    @Then("I click detail payment button")
    public void i_click_detail_payment_button() {
        setUpSolusipayWeb2();
        solpayWebHelper.delay(1400);
        solpayWeb.vrfyDetailPembayaranPopUp();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - On Pop Up Detail Pembayaran");
        solpayWebHelper.delay(2500);
        solpayWeb.hitDetailPaymentButton();
        solpayWebHelper.delay(1500);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - On Detail Payment Page");
    }

    // Start -- Steps Success Payment until Invoice //

    @When("Solusipayweb show Success Payment")
    public void solusipayweb_show_success_payment() {
        setUpSolusipayWeb2();
        solpayWebHelper.delay(400);
        solpayWeb.vrfyElementPaymentSuccessPage();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - On Payment Success Page");
        solpayWebHelper.delay(1500);
        solpayWeb.btnPaymentSuccess();
        System.out.println("I'm now on the Success Payment page");
    }

    @Then("Solusipayweb show process payment")
    public void solusipayweb_show_process_payment(){
        setUpSolusipayWeb2();
        solpayWebHelper.delay(500);
        solpayWeb.vrfyElementPaymentProcessPage();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - On Payment Success Page");
        solpayWebHelper.delay(1500);
        solpayWeb.hitBtnKembali();
    }

    @Then("I can check detail transaction")
    public void i_can_check_detail_transaction() {
        solpayWeb.vrfyDetailTransaction();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - On Detail Transaction PLN Postpaid Page");
        solpayWebHelper.delay(1500);
        System.out.println("I'm now on the Detail Transaction page");
    }

    @Then("I can check Invoice transaction")
    public void i_can_check_invoice_transaction() {
        solpayWebHelper.delay(1500);
        solpayWeb.btnInvoice();
        solpayWebHelper.delay(400);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - On Invoice Transaction PLN Postpaid Page");
        System.out.println("I'm now on the Invoice page");
    }

    @Then("I navigate to check detail transaction")
    public void iNavigateToDetailTransaction(){
        setUpSolusipayWeb2();
        solpayWebHelper.delay(3000);
        solpayWeb.hitBtnTransaction();
        solpayWebHelper.delay(2000);
        solpayWeb.listTransactionCard();
        solpayWebHelper.delay(1000);
        solpayWeb.vrfyDetailTransaction();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - On Detail Transaction PLN Postpaid Page");
    }

    // End -- Steps Success Payment until Invoice //

    //Filter Dedom Product
    @When("I click button filter")
    public void iClickButtonFilter() {
        setUpSolusipayWeb2();
        solpayWeb.hitBtnFilter();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Pulsa Page - Hit Button Filter");
        solpayWebHelper.delay(500);
    }

    @When("I select filter {string}, {string}, {string}, {string}")
    public void iSelectFilter(String condition, String priceMin, String priceMax, String filterRange) {
        solpayWeb.selectFilter(condition,priceMin,priceMax,filterRange);
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Pulsa Page - Select or Fill Filter");
        solpayWebHelper.delay(500);
    }

    @When("I click button save filter")
    public void iClickButtonSaveFilter() {
        solpayWeb.saveFilter();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Pulsa Page - Click Button Save");
        solpayWebHelper.delay(500);
    }
    //End filter denom product

}