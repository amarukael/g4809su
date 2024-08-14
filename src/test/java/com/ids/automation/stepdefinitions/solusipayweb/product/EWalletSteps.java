package com.ids.automation.stepdefinitions.solusipayweb.product;

import com.ids.automation.configuration.BrowserSetup;
import helper.SolusipayWebHelper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import pages.solusipayweb.globalpages.SolusipayWebPages;
import pages.solusipayweb.product.EWalletPages;
import constant.SolusipayWebConstant;
import utility.Helper;

public class EWalletSteps {
    public static Scenario scenario;
    static WebDriver driver;
    SolusipayWebHelper solpayWebHelper = new SolusipayWebHelper();
    private byte[] screenshotData;
    Integer countImage = 1;
    SolusipayWebPages solpayWeb;
    EWalletPages ewalletPages;

    public void setUpEWallet() {
        driver = BrowserSetup.getDriver();
        driver.get(SolusipayWebConstant.UrlSolpayWeb);
        solpayWeb = new SolusipayWebPages(driver);
        ewalletPages = new EWalletPages(driver);
    }

    @Before
    public void afterScenario(Scenario scenario) {
        EWalletSteps.scenario = scenario;
    }

    @When("I navigate to solusipayweb e-wallet")
    public void iNavigateToSolusipaywebEWallet(){
        setUpEWallet();
        solpayWeb.scrollToBottomPage();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Navigate to Voucher Game Page");
        solpayWebHelper.delay(1000);
        solpayWeb.hitBtnMenuEW();
    }

    @Then("I'm now on the e-wallet page")
    public void imNowOnTheewalletpage(){
        ewalletPages.vrfyEWalletPage();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Voucher Game Page");
    }

    @Given("I on solusipayweb E-Wallet page")
    public void iOnSolusipaywebEWalletPage() {
        setUpEWallet();
        solpayWebHelper.delay(3000);
        solpayWeb.hitBtnMenuEW();
        solpayWebHelper.delay(400);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Ewallet");
        countImage++;
        solpayWebHelper.delay(2000);
    }

    @When("I choose {string} E-Wallet")
    public void iChooseEWallet(String arg0) throws Exception {
        solpayWebHelper.delay(1000);
        ewalletPages.chooseProduct(arg0);
         }

    @When("I fill {string} nomor pelanggan E-Wallet")
    public void iFillNomorPelangganEWallet(String arg0) {
        ewalletPages.fillNumber(arg0);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Ewallet");
        countImage++;
        solpayWebHelper.delay(2000);
    }

    @When("I choose {string} on {string}")
    public void iChooseOn(String arg0, String arg1) {
        ewalletPages.chooseDenom(arg0);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Ewallet");
        countImage++;
        solpayWebHelper.delay(1000);
    }
}
