package com.ids.automation.stepdefinitions.solusipayweb.product;

import com.ids.automation.configuration.BrowserSetup;
import constant.SolusipayWebConstant;
import helper.SolusipayWebHelper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.solusipayweb.globalpages.SolusipayWebPages;
import pages.solusipayweb.product.PlnPrepaidPages;
import utility.Helper;

public class PlnPrepaidSteps {
    public static Scenario scenario;
    static WebDriver driver;
    static WebDriverWait wait;
    PlnPrepaidPages plnPrepaid;
    SolusipayWebHelper solpayWebHelper = new SolusipayWebHelper();
    SolusipayWebConstant solusipayWebConstant;
    private byte[] screenshotData;
    Integer countImage = 1;
    SolusipayWebPages solpayWeb;

    @Before
    public void afterScenario(Scenario scenario) {
        PlnPrepaidSteps.scenario = scenario;
    }

    public void setUpSolpayWebPLNPrepaid(){
        driver = BrowserSetup.getDriver();
        solpayWeb = new SolusipayWebPages(driver);
        plnPrepaid = new PlnPrepaidPages(driver);
    }


    @When("I navigate to solusipayweb PLN Prepaid")
    public void i_navigate_to_solusipayweb_pln_prepaid() {
        setUpSolpayWebPLNPrepaid();
        solpayWeb.scrollToBottomPage();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Navigate to PLN Prepaid Page");
        solpayWebHelper.delay(1000);
        solpayWeb.hitBtnMenuPlnPre();
    }

    @Then("I'm now on the PLNPre page")
    public void i_m_now_on_the_pln_pre_page() {
        solpayWebHelper.delay(1000);
        plnPrepaid.vrfyElementPlnPrePage();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - On PLN Prepaid Page");
    }

    @When("I input {string} Pln Pre")
    public void i_input_pln_pre(String idpel) {
        solpayWebHelper.delay(400);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - On PLN Prepaid Page");
        solpayWebHelper.delay(800);
        plnPrepaid.inputIdPelanggan(idpel);
        plnPrepaid.denomPln20k();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Input Data");
        solpayWebHelper.delay(800);
        System.out.println("I'm now on the PLN Post page");
    }

    @When("I choose {string} Pln Pre")
    public void i_choose_pln_pre(String denom) {
        plnPrepaid.denomPlnPre(denom);
        solpayWebHelper.delay(2000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Input Data");
        solpayWebHelper.delay(2000);
    }

}
