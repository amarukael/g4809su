package com.ids.automation.stepdefinitions.solusipayweb.product;

import org.openqa.selenium.WebDriver;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.solusipayweb.globalSteps.SolusipayWebSteps;

import constant.SolusipayWebConstant;
import helper.SolusipayWebHelper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.solusipayweb.globalpages.SolusipayWebPages;
import pages.solusipayweb.product.AsuransiPages;
import utility.Helper;

public class AsuransiSteps {
    public static Scenario scenario;
    static WebDriver driver;
    SolusipayWebHelper solpayWebHelper = new SolusipayWebHelper();
    private byte[] screenshotData;
    Integer countImage = 1;
    SolusipayWebPages solpayWeb;
    AsuransiPages asuransiPages;
    SolusipayWebSteps solusipayWebSteps;

    public void setUp() {
        driver = BrowserSetup.getDriverMobile();
        driver.get(SolusipayWebConstant.UrlSolpayWeb);
        solpayWeb = new SolusipayWebPages(driver);
        asuransiPages = new AsuransiPages(driver);
    }

    public void setUpAsuransi() {
        driver = BrowserSetup.getDriverMobile();
        // driver.get(SolusipayWebConstant.UrlSolpayWeb);
        solpayWeb = new SolusipayWebPages(driver);
        asuransiPages = new AsuransiPages(driver);
    }

    @Before
    public void afterScenario(Scenario scenario) {
        AsuransiSteps.scenario = scenario;
    }

    @When("I navigate to solusipayweb asuransi")
    public void iNavigateToSolusipaywebAsuransi() {
        setUpAsuransi();
        solpayWeb.scrollToBottomPage();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Navigate to Voucher Game Page");
        solpayWebHelper.delay(1000);
        solpayWeb.hitBtnMenuInsurance();
    }

    @Then("I'm now on the asuransi page")
    public void imNowOnTheewalletpage() {
        asuransiPages.vrfyAsuransiPage();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Voucher Game Page");
        solpayWebHelper.delay(2000);
    }

    @Given("I on solusipayweb Asuransi page")
    public void iOnSolusipaywebAsuransiPage() {
        setUp();
        solpayWebHelper.delay(3000);
        solpayWeb.hitBtnMenuInsurance();
        solpayWebHelper.delay(1500);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Ewallet");
        countImage++;
        solpayWebHelper.delay(1000);
    }

    @When("I choose {string} Asuransi")
    public void iChooseAsuransi(String arg0) throws Exception {
        solpayWebHelper.delay(1000);
        asuransiPages.chooseProduct(arg0);
    }

    @When("I fill {string} nomor pelanggan Asuransi")
    public void iFillNomorPelangganAsuransi(String arg0) {
        asuransiPages.fillNumber(arg0);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Ewallet");
        countImage++;
        solpayWebHelper.delay(2000);
    }

    @Given("I on solusipayweb Token Listrik page")
    public void I_on_solusipayweb_Token_Listrik_page() {
        setUp();
        solpayWebHelper.delay(3000);
        solpayWeb.hitBtnMenuPlnPre();
        solpayWebHelper.delay(5000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Ewallet");
        countImage++;
        solpayWebHelper.delay(3000);
    }

    @Given("I on solusipayweb Tagihan Listrik page")
    public void I_on_solusipayweb_Tagihan_Listrik_page() {
        setUp();
        solpayWebHelper.delay(3000);
        solpayWeb.hitBtnMenuPlnPost();
        solpayWebHelper.delay(5000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Ewallet");
        countImage++;
        solpayWebHelper.delay(3000);
    }

    @Given("I on solusipayweb Pascabayar page")
    public void I_on_solusipayweb_Pascabayar_page() {
        setUp();
        solpayWebHelper.delay(3000);
        solpayWeb.hitBtnMenuPascabayar();
        solpayWebHelper.delay(5000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Ewallet");
        countImage++;
        solpayWebHelper.delay(3000);
    }

    @Given("I on solusipayweb TV Cable page")
    public void I_on_solusipayweb_TV_Cable_page() {
        setUp();
        solpayWebHelper.delay(3000);
        solpayWeb.hitBtnMenuTv();
        solpayWebHelper.delay(5000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Ewallet");
        countImage++;
        solpayWebHelper.delay(3000);
    }

    @Given("I on solusipayweb Internet page")
    public void I_on_solusipayweb_Internet_page() {
        setUp();
        solpayWebHelper.delay(3000);
        solpayWeb.hitBtnMenuTelkom();
        solpayWebHelper.delay(5000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Ewallet");
        countImage++;
        solpayWebHelper.delay(3000);
    }

    @Given("I on solusipayweb Pulsa page")
    public void I_on_solusipayweb_Pulsa_page() {
        setUp();
        solpayWebHelper.delay(3000);
        solpayWeb.hitBtnMenuPulsa();
        solpayWebHelper.delay(5000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Ewallet");
        countImage++;
        solpayWebHelper.delay(3000);
    }

    
}
