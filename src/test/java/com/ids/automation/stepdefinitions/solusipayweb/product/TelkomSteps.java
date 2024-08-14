package com.ids.automation.stepdefinitions.solusipayweb.product;

import com.ids.automation.configuration.BrowserSetup;
import helper.SolusipayWebHelper;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.solusipayweb.globalpages.SolusipayWebPages;
import constant.SolusipayWebConstant;
import pages.solusipayweb.product.TelkomPages;
import utility.Helper;
import io.cucumber.java.Scenario;

public class TelkomSteps {
    public static Scenario scenario;
    static WebDriver driver;
    static WebDriverWait wait;
    TelkomPages telkom;
    SolusipayWebHelper solpayWebHelper = new SolusipayWebHelper();
    SolusipayWebConstant solusipayWebConstant;
    private byte[] screenshotData;
    SolusipayWebPages solpayWeb;

    @Before
    public void afterScenario(Scenario scenario) {
        TelkomSteps.scenario = scenario;
    }

    public void setUpTelkom(){
        driver = BrowserSetup.getDriver();
        if(solpayWeb == null){
            solpayWeb = new SolusipayWebPages(driver);
        }

        if(telkom == null){
            telkom = new TelkomPages(driver);
        }
    }

    public void setUpTelkom2(){
        driver = BrowserSetup.getDriver();
        driver.get(SolusipayWebConstant.UrlSolpayWeb);
        if(solpayWeb == null){
            solpayWeb = new SolusipayWebPages(driver);
        }

        if(telkom == null){
            telkom = new TelkomPages(driver);
        }
    }

    @When("I navigate to solusipayweb Telkom")
    public void iNavigateToSolusipaywebTelkom(){
        setUpTelkom();
        solpayWeb.scrollToMiddlePage();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Navigate to Telkom Page");
        solpayWebHelper.delay(1000);
        solpayWeb.hitBtnMenuTelkom();
    }

    @Then("I'm now on solusipayweb Telkom")
    public void iMNowOnSolusipaywebTelkom() {
        telkom.verifyTelkomPage();
        solpayWebHelper.delay(3000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - I'm now on solusipayweb Telkom");
        solpayWebHelper.delay(1000);
    }

    @Given("I on solusipayweb Telkom")
    public void iOnSolusipaywebTelkom() {
        setUpTelkom2();
        solpayWebHelper.delay(3000);
        solpayWeb.scrollToMiddlePage();
        solpayWebHelper.delay(1000);
        solpayWeb.hitBtnMenuTelkom();
        solpayWebHelper.delay(2000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Telkom Page");
        solpayWebHelper.delay(1000);
    }

    @When("I choose penyedia layanan {string} telkom page")
    public void iChoosePenyediaLayananTelkomPage(String layanan) {
        telkom.choosePenyediaLayanan(layanan);
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Choose penyedia layanan");
        solpayWebHelper.delay(1000);
    }

    @When("I fill nomor pengguna {string} on telkom page")
    public void iFillNomorPenggunaOnTelkomPage(String customerId) {
        telkom.fillCustomerId(customerId);
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - fill nomor pengguna");
        solpayWebHelper.delay(1000);
    }

    @Then("I verify error message field customerId on Telkom page")
    public void iVerifyErrorMessageFieldCustomerIdOnTelkomPage() {
        telkom.verifyErrorMessageCustId();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Verify error message");
        solpayWebHelper.delay(1000);
    }
}
