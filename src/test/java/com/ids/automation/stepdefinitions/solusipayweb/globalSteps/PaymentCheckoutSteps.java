package com.ids.automation.stepdefinitions.solusipayweb.globalSteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ids.automation.configuration.BrowserSetup;

import helper.SolusipayWebHelper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.solusipayweb.globalpages.PaymentCheckoutPages;
import pages.solusipayweb.globalpages.SolusipayWebPages;
import utility.Helper;

public class PaymentCheckoutSteps {
    public static Scenario scenario;
    static WebDriver driver;
    static WebDriverWait wait;
    SolusipayWebHelper solpayWebHelper = new SolusipayWebHelper();
    private byte[] screenshotData;
    SolusipayWebPages solpayWeb;
    PaymentCheckoutPages paymentCheckout;

    @Before
    public void afterScenario(Scenario scenario) {
        PaymentCheckoutSteps.scenario = scenario;
    }

    public void setUpPaymentCheckout() {
        driver = BrowserSetup.getDriverMobile();
        if (solpayWeb == null) {
            solpayWeb = new SolusipayWebPages(driver);
        }

        if (paymentCheckout == null) {
            paymentCheckout = new PaymentCheckoutPages(driver);
        }
    }

    @When("I choose a payment method {string}")
    public void iChooseAPayentMethod(String value) {
        setUpPaymentCheckout();
        solpayWebHelper.delay(1500);
        paymentCheckout.paymentMethod(value);
        solpayWebHelper.delay(400);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Payment Checkout Page - Choose a payment method");
        solpayWebHelper.delay(1000);
    }

    @When("I click button bayar on payment checkout")
    public void iClickButtonBayar() {
        setUpPaymentCheckout();
        paymentCheckout.hitBtnBayar();
        solpayWebHelper.delay(400);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Payment Checkout Page - Choose a payment method");
        solpayWebHelper.delay(1000);
    }

    @When("I success choose a payment method")
    public void successChooseAPaymentMethod() {
        setUpPaymentCheckout();
        paymentCheckout.vrfyAfterChoosePaymentMethod();
        solpayWebHelper.delay(500);
        paymentCheckout.hitBtnCopyVAToken();
        solpayWebHelper.delay(500);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Payment Checkout Page - Copy VA Token");
        solpayWebHelper.delay(800);
        // paymentCheckout.hitBtnCopyTotalAmount();
        // paymentCheckout.hitBtnCopyTotalAmount();
        // solpayWebHelper.delay(500);
        // screenshotData = Helper.takeScreenshot(driver);
        // scenario.attach(screenshotData, "image/png", "Payment Checkout Page - Copy
        // Total Amount");
        solpayWebHelper.delay(800);
        solpayWeb.hitBtnKembali();
        solpayWebHelper.delay(800);
        // solpayWeb.vrfyElementSolpayWebPage();
    }

    @When("I scroll to buttom page on payment checkout page")
    public void iScrollToButtomPageOnPaymentCheckoutPage() {
        setUpPaymentCheckout();
        solpayWeb.scrollToBottomPage();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Payment Checkout Page - Scroll to buttom page");
        solpayWebHelper.delay(3000);
    }

    @Given("I do manual pay and wait for {string} milisecond")
    public void I_do_manual_pay_and_wait_for_milisecond(String s) {
        solpayWebHelper.delay(Integer.parseInt(s));
    }

}
