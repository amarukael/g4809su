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
import pages.solusipayweb.product.PdamPostpaidPages;
import pages.solusipayweb.product.PulsaPages;
import utility.Helper;
import constant.SolusipayWebConstant;

public class PulsaSteps {
    public static Scenario scenario;
    static WebDriver driver;
    static WebDriverWait wait;
    SolusipayWebHelper solpayWebHelper = new SolusipayWebHelper();
    private byte[] screenshotData;
    SolusipayWebPages solpayWeb;
    PulsaPages pulsa;
    SolusipayWebConstant solusipayWebConstant;


    @Before
    public void afterScenario(Scenario scenario) {
        PulsaSteps.scenario = scenario;
    }

    public void setUpPulsa(){
        driver = BrowserSetup.getDriver();
        if(solpayWeb == null){
            solpayWeb = new SolusipayWebPages(driver);
        }
        if(pulsa == null){
            pulsa = new PulsaPages(driver);
        }
    }

    public void setUpulsa2(){
        driver = BrowserSetup.getDriver();
        driver.get(SolusipayWebConstant.UrlSolpayWeb);
        if(solpayWeb == null){
            solpayWeb = new SolusipayWebPages(driver);
        }
        if(pulsa == null){
            pulsa = new PulsaPages(driver);
        }
    }

    @When("I navigate to solusipayweb pulsa")
    public void iNavegateToSolusipaywebPulsa(){
        setUpPulsa();
        solpayWeb.scrollToBottomPage();
        solpayWebHelper.delay(1500);
        solpayWeb.hitBtnMenuPulsa();
        solpayWebHelper.delay(1500);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Navigate to Pulsa Page");
        solpayWebHelper.delay(500);
    }

    @Then("I'm now on the pulsa page")
    public void imNowOnThePulsaPage(){
        pulsa.vrfyPulsaPage();
        solpayWebHelper.delay(2000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - User on Pulsa page");
    }

    @Given("I on solusipayweb pulsa")
    public void iOnSolusipaywebPulsa() {
        setUpulsa2();
        solpayWebHelper.delay(3000);
        solpayWeb.scrollToBottomPage();
        solpayWebHelper.delay(1000);
        solpayWeb.hitBtnMenuPulsa();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Navigate to Pulsa Page");
        solpayWebHelper.delay(1500);
    }

    @When("I fill phone number on pulsa page {string}")
    public void iFillPhoneNumberOnPulsaPage(String phoneNumber) {
        solpayWebHelper.delay(500);
        pulsa.fillPhoneNumberFieldOnPulsaPage(phoneNumber);
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Pulsa Page - Fill phone number");
        solpayWebHelper.delay(500);
    }


    @When("I choose nominal pulsa {string}")
    public void iChooseNominalPulsa(String nominalPulsa) {
        pulsa.chooseNominalPulsa(nominalPulsa);
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Pulsa Page - Choose nominal pulsa");
        solpayWebHelper.delay(500);
    }

    @Then("I verify error message phone number field on pulsa page")
    public void iVerifyErrorMessagePhoneNumberFieldOnPulsaPage() {
        pulsa.errorMessageFieldPhoneNumberOnPulsaPage();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Pulsa Page - Error Message Field Phone Number");
        solpayWebHelper.delay(500);
    }

    @Then("I can see denom pulsa base on filter")
    public void iCanSeeDenomPulsaBaseOnFilter() {
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Pulsa Page - Show denom pulsa after select filter");
        solpayWebHelper.delay(500);
    }
}
