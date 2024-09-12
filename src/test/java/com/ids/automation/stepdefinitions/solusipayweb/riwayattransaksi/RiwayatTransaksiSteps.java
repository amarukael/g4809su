package com.ids.automation.stepdefinitions.solusipayweb.riwayattransaksi;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.solusipayweb.product.PdamPostpaidSteps;

import constant.SolusipayWebConstant;
import helper.SolusipayWebHelper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import pages.solusipayweb.globalpages.SolusipayWebPages;
import pages.solusipayweb.product.PdamPostpaidPages;
import utility.Helper;

public class RiwayatTransaksiSteps {
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
    public void afterScenario(Scenario scenario) {
        PdamPostpaidSteps.scenario = scenario;
    }

    public void setUpSolusipayWebRiwayat() {
        driver = BrowserSetup.getDriverMobile();
        if (solpayWeb == null) {
            solpayWeb = new SolusipayWebPages(driver);
        }
        if (pdamPostpaid == null) {
            pdamPostpaid = new PdamPostpaidPages(driver);
        }
    }

    @Given("I navigate to solusipayweb Transaksi")
    public void i_navigate_to_solusipayweb_transaksi() {
        setUpSolusipayWebRiwayat();
        solpayWebHelper.delay(2500);
        solpayWeb.hitBtnTransaction();
        solpayWebHelper.delay(400);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Navigate to PDAM Page");
        solpayWebHelper.delay(1000);
    }
}
