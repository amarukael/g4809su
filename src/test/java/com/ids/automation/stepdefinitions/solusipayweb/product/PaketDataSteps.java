package com.ids.automation.stepdefinitions.solusipayweb.product;

import com.ids.automation.configuration.BrowserSetup;
import helper.SolusipayWebHelper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.solusipayweb.globalpages.SolusipayWebPages;
import pages.solusipayweb.product.PaketDataPages;
import utility.Helper;
import constant.SolusipayWebConstant;

public class PaketDataSteps {
    public static Scenario scenario;
    static WebDriver driver;
    static WebDriverWait wait;
    SolusipayWebHelper solpayWebHelper = new SolusipayWebHelper();
    private byte[] screenshotData;
    SolusipayWebPages solpayWeb;
    PaketDataPages paketData;
    SolusipayWebConstant solusipayWebConstant;

    @Before
    public void afterScenario(Scenario scenario) {
        PaketDataSteps.scenario = scenario;
    }

    public void setUpPaketData(){
        driver = BrowserSetup.getDriver();
        if(solpayWeb == null){
            solpayWeb = new SolusipayWebPages(driver);
        }
        if(paketData == null){
            paketData = new PaketDataPages(driver);
        }
    }

    public void setPaketData2(){
        driver = BrowserSetup.getDriver();
        driver.get(SolusipayWebConstant.UrlSolpayWeb);
        if(solpayWeb == null){
            solpayWeb = new SolusipayWebPages(driver);
        }
        if(paketData == null){
            paketData = new PaketDataPages(driver);
        }
    }

    @When("I navigate to solusipayweb paket data")
    public void iNavigateToSolusipaywebPaketData(){
        setUpPaketData();
        solpayWeb.scrollToBottomPage();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Navigate to Paket Data Page");
        solpayWebHelper.delay(1000);
        solpayWeb.hitBtnMenuPktData();
    }

    @Given("I on solusipayweb paket data")
    public void iOnSolusipaywebPaketData() {
        setPaketData2();
        solpayWebHelper.delay(3000);
        solpayWeb.scrollToBottomPage();
        solpayWebHelper.delay(1000);
        solpayWeb.hitBtnMenuPktData();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Navigate to Paket Data Page");
        solpayWebHelper.delay(1500);
    }

    @Then("I'm now on the paket data page")
    public void imNowOnThePaketDataPage(){
        paketData.vrfyPaketDataPage();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - User on Paket Data page");
    }

    @When("I choose denom paket data {string}")
    public void iChooseDenomPaketData(String denomPaketData) {
        paketData.chooseDenomPaketData(denomPaketData);
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Choose denom paket data");
        solpayWebHelper.delay(1000);
    }

    @When("I fill phone number paket data page {string}")
    public void iFillPhoneNumberPaketDataPage(String phoneNumber) {
        paketData.fillPhoneNumberOnPaketDataPage(phoneNumber);
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Fill phone number");
        solpayWebHelper.delay(1000);
    }


    @Then("I verify error message phone number field on paket data page")
    public void iVerifyErrorMessagePhoneNumberFieldOnPaketDataPage() {
        paketData.errorMessageFieldPhoneNumberOnPaketDataPage();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Pulsa Page - Error Message Field Phone Number");
        solpayWebHelper.delay(500);
    }

    @Then("I can see denom paket data base on filter")
    public void iCanSeeDenomPaketDataBaseOnFilter() {
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Pulsa Page - Show denom paket data after select filter");
        solpayWebHelper.delay(500);
    }
}
