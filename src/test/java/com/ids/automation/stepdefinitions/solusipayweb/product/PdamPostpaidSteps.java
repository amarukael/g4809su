package com.ids.automation.stepdefinitions.solusipayweb.product;

import com.ids.automation.configuration.BrowserSetup;
import constant.SolusipayWebConstant;
import helper.SolusipayWebHelper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.solusipayweb.globalpages.SolusipayWebPages;
import pages.solusipayweb.product.PdamPostpaidPages;
import utility.Helper;

public class PdamPostpaidSteps {
    public static Scenario scenario;
    static WebDriver driver;
    static WebDriverWait wait;
    PdamPostpaidPages pdamPostpaid;
    SolusipayWebPages solpayWeb;
    SolusipayWebHelper solpayWebHelper = new SolusipayWebHelper();
    SolusipayWebConstant solusipayWebConstant;
    private byte[] screenshotData;
    Integer countImage = 1;

    @Before
    public void afterScenario(Scenario scenario){
        PdamPostpaidSteps.scenario = scenario;
    }

    public void setUpSolusipayWebPDAM(){
        driver = BrowserSetup.getDriver();
        driver.get(SolusipayWebConstant.UrlSolpayWeb);
        if(solpayWeb == null){
            solpayWeb = new SolusipayWebPages(driver);
        }
        if(pdamPostpaid == null){
            pdamPostpaid = new PdamPostpaidPages(driver);
        }
    }
    public void setUpSolusipayWebPDAM2(){
        driver = BrowserSetup.getDriver();
        if(solpayWeb == null){
            solpayWeb = new SolusipayWebPages(driver);
        }
        if(pdamPostpaid == null){
            pdamPostpaid = new PdamPostpaidPages(driver);
        }
    }

    @Given("I navigate to solusipayweb PDAM")
    public void iNavigateToSolusipaywebPDAM(){
        setUpSolusipayWebPDAM2();
        solpayWebHelper.delay(2500);
        solpayWeb.hitBtnMenuPdam();
        solpayWebHelper.delay(400);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Navigate to PDAM Page");
        solpayWebHelper.delay(1000);
    }

    @Given("I on solusipayweb PDAM page")
    public void iOnSolusipaywebPDAMPage() {
        setUpSolusipayWebPDAM();
        solpayWebHelper.delay(3000);
        solpayWeb.hitBtnMenuPdam();
        solpayWebHelper.delay(400);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Navigate to PDAM Page");
        solpayWebHelper.delay(2000);
    }

    @Then("I'm now on solusipayweb PDAM")
    public void imNowOnSolusipaywebPDAM(){
        pdamPostpaid.verifyPDAMPage();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "PDAM Page");
        solpayWebHelper.delay(1000);
    }

    @When("I choose {string} wilayah penyedia")
    public void iChooseWilayahPenyedia(String pdam) {
        solpayWebHelper.delay(3000);
        pdamPostpaid.choosePDAMWilayahPenyedia(pdam);
        solpayWebHelper.delay(500);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "PDAM Page - Choose wilayah penyedia");
        solpayWebHelper.delay(1000);
    }

    @When("I fill {string} nomor pelanggan")
    public void iFillNomorPelanggan(String customerId) {
        pdamPostpaid.fillNoPelanggan(customerId);
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "PDAM Page - Fill Nomor Pelanggan");
        solpayWebHelper.delay(1000);
    }

    @Then("I verify error message {string} invalid input nomor pelanggan")
    public void iVerifyErrorMessageInvalidInputNomorPelanggan(String condition) {
        if(condition.equals("less then 5 characters")){
            pdamPostpaid.errMsgCustId();
            solpayWebHelper.delay(1000);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "PDAM Page - Error Message field Nomor Pelanggan");
            solpayWebHelper.delay(1000);
        } else if (condition.equals("more then 15 characters")) {
            pdamPostpaid.errMsgCustId();
            solpayWebHelper.delay(1000);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "PDAM Page - Error Message field Nomor Pelanggan");
            solpayWebHelper.delay(1000);
        } else if (condition.equals("wrong customer id")){
            solpayWeb.hitBuyButton();
            pdamPostpaid.errMsgSnackBarWrongCustId();
            solpayWebHelper.delay(1000);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "PDAM Page - Error Message Snackbar");
            solpayWebHelper.delay(1000);
        } else if (condition.equals("customer id has been pay")){
            solpayWeb.hitBuyButton();
            pdamPostpaid.errMsgSnackBarCustIdHasBeenPay();
            solpayWebHelper.delay(1000);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "PDAM Page - Error Message Snackbar");
            solpayWebHelper.delay(1000);
        }
    }
}
