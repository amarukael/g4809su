package com.ids.automation.stepdefinitions.solusipayweb.product;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ids.automation.configuration.BrowserSetup;

import constant.SolusipayWebConstant;
import helper.SolusipayWebHelper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.solusipayweb.globalpages.MethodPaymentPages;
import pages.solusipayweb.globalpages.SolusipayWebPages;
import pages.solusipayweb.product.PlnPostpaidPages;
import utility.Helper;

public class PlnPostpaidSteps {
    public static Scenario scenario;
    static WebDriver driver;
    static WebDriverWait wait;
    PlnPostpaidPages plnPostpaid;
    MethodPaymentPages methodpayment;
    SolusipayWebHelper solpayWebHelper = new SolusipayWebHelper();
    SolusipayWebConstant solusipayWebConstant;
    private byte[] screenshotData;
    Integer countImage = 1;
    SolusipayWebPages solpayWeb;

    @Before
    public void afterScenario(Scenario scenario) {
        PlnPostpaidSteps.scenario = scenario;
    }

    public void setUpSolpayWebPLNPostpaid() {
        driver = BrowserSetup.getDriverMobile();
        solpayWeb = new SolusipayWebPages(driver);
        plnPostpaid = new PlnPostpaidPages(driver);
        methodpayment = new MethodPaymentPages(driver);
    }

    @When("I navigate to solusipayweb Tagihan Listrik")
    public void i_navigate_to_solusipayweb_pln_postpaid() {
        setUpSolpayWebPLNPostpaid();
        // solpayWeb.scrollToBottomPage();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Navigate to Tagihan Listrik Page");
        solpayWebHelper.delay(1000);
        solpayWeb.hitBtnMenuPlnPost();
    }

    @Then("I'm now on the PLNPost page")
    public void i_m_now_on_the_pln_post_page() {
        solpayWebHelper.delay(1000);
        plnPostpaid.vrfyElementPlnPostPage();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - On Tagihan Listrik Page");
    }

    @When("I input {string} Tagihan Listrik")
    public void i_input_pln_post(String idpel) {
        setUpSolpayWebPLNPostpaid();
        plnPostpaid.vrfyElementPlnPostPage();
        solpayWebHelper.delay(400);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - On Tagihan Listrik Page");
        solpayWebHelper.delay(800);
        plnPostpaid.inputIdPelanggan(idpel);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Input Data");
        solpayWebHelper.delay(2800);
    }

    @When("I click button bayar on Pln Post")
    public void iClickButtonBayarOnPlnPost() {
        solpayWebHelper.delay(5500);
        plnPostpaid.btnSubmit();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Hit Button Bayar");
    }

    // Start -- Negatif Steps //

    @Then("I can see {string} snackbar massage")
    public void i_can_see_snackbar_massage(String string) {
        solpayWebHelper.delay(3500);
        plnPostpaid.btnSubmit();
    }

    @Then("I can see {string} warning massage")
    public void i_can_see_warning_massage(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}