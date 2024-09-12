package com.ids.automation.stepdefinitions.solusipayweb.globalSteps;

import com.ids.automation.configuration.BrowserSetup;
import helper.SolusipayWebHelper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.solusipayweb.globalpages.DetailPembayaranPages;
import pages.solusipayweb.globalpages.MethodPaymentPages;
import pages.solusipayweb.globalpages.SolusipayWebPages;
import utility.Helper;

public class MethodPaymentSteps {
    public static Scenario scenario;
    static WebDriver driver;
    static WebDriverWait wait;
    SolusipayWebHelper solpayWebHelper = new SolusipayWebHelper();
    private byte[] screenshotData;
    SolusipayWebPages solpayWeb;
    MethodPaymentPages methodpayment;
    DetailPembayaranPages detailPembayaran;

    @Before
    public void afterScenario(Scenario scenario) {
        MethodPaymentSteps.scenario = scenario;
    }

    public void setUpMethodPayment(){
        driver = BrowserSetup.getDriverMobile();
        if(solpayWeb == null){
            solpayWeb = new SolusipayWebPages(driver);
        }
        if (methodpayment == null){
            methodpayment = new MethodPaymentPages(driver);
        }
    }


    @Then("I choose payment method Virgo")
    public void i_choose_payment_method_virgo() {
        setUpMethodPayment();
        methodpayment.vrfyElementPaymentMethodPage();
        solpayWebHelper.delay(3000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - On Payment Method Page");
        methodpayment.radioVirgo();
        solpayWebHelper.delay(1400);
        methodpayment.btnPayment();
        solpayWebHelper.delay(1500);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - On Payment Method Virgo Confirmation Page");
        solpayWebHelper.delay(1500);
    }
    @Then("I proceed payment for Virgo")
    public void i_proceed_payment_for_virgo() throws InterruptedException {
        solpayWebHelper.delay(400);
        methodpayment.btnPayment();
        solpayWebHelper.delay(3500);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - On Virgo Passcode Page");
        methodpayment.inputPasscodeVirgo();
        solpayWebHelper.delay(1500);
        screenshotData = Helper.takeScreenshot(driver);
        System.out.println("I'm now on the Payment Virgo page");
    }


    @When("I scroll to buttom page on paymentgateway page")
    public void iScrollToButtomPageOnPaymentgatewayPage() {
        setUpMethodPayment();
        methodpayment.scrollToButtomOnCardMethod();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Scroll to buttom page");
        solpayWebHelper.delay(1000);
    }

    @When("I choose a payment method dana on paymentgateway page")
    public void iChooseAPaymentMethodOnPaymentgatewayPage() {
        methodpayment.choosePayMethodPGDana();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Choose pay method DANA");
        solpayWebHelper.delay(1000);
    }

    @When("I click button bayar on paymentgateway page")
    public void iClickButtonBayarOnPaymentgatewayPage() {
        methodpayment.btnPayment();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Hit button Bayar");
        solpayWebHelper.delay(2000);
    }

    @When("I click button bayar again on paymentgateway page")
    public void iClickButtonBayarAgainOnPaymentgatewayPage() {
        methodpayment.btnPaymentAgain();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Hit button Bayar again");
        solpayWebHelper.delay(2000);
    }
}