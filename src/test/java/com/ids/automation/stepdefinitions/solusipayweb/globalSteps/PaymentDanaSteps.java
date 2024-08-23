package com.ids.automation.stepdefinitions.solusipayweb.globalSteps;

import com.ids.automation.configuration.BrowserSetup;
import helper.SolusipayWebHelper;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.solusipayweb.globalpages.PaymentDanaPages;
import utility.Helper;
import io.cucumber.java.Scenario;

public class PaymentDanaSteps {
    public static Scenario scenario;
    static WebDriver driver;
    static WebDriverWait wait;
    PaymentDanaPages paymentDanaPages;
    SolusipayWebHelper solpayWebHelper = new SolusipayWebHelper();
    private byte[] screenshotData;

    @Before
    public void afterScenario(Scenario scenario) {
        PaymentDanaSteps.scenario = scenario;
    }

    public void setUpPaymentDana(){
        driver = BrowserSetup.getDriver();
        if(paymentDanaPages == null){
            paymentDanaPages = new PaymentDanaPages(driver);
        }
    }

    @Then("I proceed payment for DANA {string} and {string}")
    public void iProceedPaymentForDANA(String phoneNumber, String passcode) {
        setUpPaymentDana();
        solpayWebHelper.delay(1000);
        paymentDanaPages.fillPhoneNumberDana(phoneNumber);
        solpayWebHelper.delay(1000);
        paymentDanaPages.hitBtnNextOnDanaPage();
        solpayWebHelper.delay(2500);
        paymentDanaPages.fillPasscodeDana(passcode);
        solpayWebHelper.delay(1000);
        paymentDanaPages.hitBtnPayOnDanaPage();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Payment DANA");
        solpayWebHelper.delay(1000);
        paymentDanaPages.hitBtnOKDanaPage();
        solpayWebHelper.delay(1000);
    }

}
