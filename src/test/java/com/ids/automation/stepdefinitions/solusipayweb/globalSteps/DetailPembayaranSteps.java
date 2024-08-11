package com.ids.automation.stepdefinitions.solusipayweb.globalSteps;

import com.ids.automation.configuration.BrowserSetup;
import helper.SolusipayWebHelper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.solusipayweb.globalpages.DetailPembayaranPages;
import utility.Helper;

public class DetailPembayaranSteps {
    public static Scenario scenario;
    static WebDriver driver;
    static WebDriverWait wait;
    SolusipayWebHelper solpayWebHelper = new SolusipayWebHelper();
    private byte[] screenshotData;
    DetailPembayaranPages detailPembayaran;

    @Before
    public void afterScenario(Scenario scenario) {
        DetailPembayaranSteps.scenario = scenario;
    }

    public void setUpDetailPembayaran(){
        driver = BrowserSetup.getDriver();

        if(detailPembayaran == null){
            detailPembayaran = new DetailPembayaranPages(driver);
        }
    }

    @When("I click button pilih pembayaran")
    public void iClickButtonPilihPembayaran(){
        setUpDetailPembayaran();
        solpayWebHelper.delay(1000);
        detailPembayaran.hitBtnPilihPembayaran();
        solpayWebHelper.delay(850);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Hit button pilih pembayaran");
        solpayWebHelper.delay(850);
    }

    @When("I now on detail pembayaran top up game page")
    public void iNowOnDetailPembayaranTopUpGame(){
        setUpDetailPembayaran();
        detailPembayaran.vrfyDetailPembayaranTopUpGame();
        solpayWebHelper.delay(850);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Detail Pembayaran Page");
        solpayWebHelper.delay(850);
    }

    @Then("I now on detail pembayaran voucher game page")
    public void iNowOnDetailPembayaranVoucherGamePage(){
        setUpDetailPembayaran();
        detailPembayaran.vrfyDetailPembayaranVoucherGame();
        solpayWebHelper.delay(850);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Detail Pembayaran Page");
        solpayWebHelper.delay(850);
    }

    @When("I now on detail pembayaran PDAM page {string}")
    public void iNowOnDetailPembayaranPDAMPage(String condition) {
        if (condition.equals("no discount")) {
            setUpDetailPembayaran();
            detailPembayaran.vrfyDetailPembayaranPDAM();
            solpayWebHelper.delay(850);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Detail Pembayaran Page - No Discount");
            solpayWebHelper.delay(850);
        } else if (condition.equals("discount admin")) {
            setUpDetailPembayaran();
            detailPembayaran.discountAdmin();
            solpayWebHelper.delay(850);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Detail Pembayaran Page - Discount Admin");
            solpayWebHelper.delay(850);
        } else if (condition.equals("discount nominal")) {
            setUpDetailPembayaran();
            detailPembayaran.discountNominal();
            solpayWebHelper.delay(850);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Detail Pembayaran Page -  Discount Nominal");
            solpayWebHelper.delay(850);
        }
    }

    @Then("I now on detail pembayaran pulsa page {string}")
    public void iNowOnDetailPembayaranPulsaPage(String condition) {
        setUpDetailPembayaran();
        detailPembayaran.vrfyDetailPembayaranPulsa();
        if (condition.equals("no discount")) {
            solpayWebHelper.delay(850);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Detail Pembayaran Page - No Discount");
            solpayWebHelper.delay(850);
        } else if (condition.equals("discount admin")) {
            detailPembayaran.discountAdmin();
            solpayWebHelper.delay(850);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Detail Pembayaran Page - Discount Admin");
        } else if (condition.equals("discount nominal")) {
            detailPembayaran.discountNominal();
            solpayWebHelper.delay(850);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Detail Pembayaran Page -  Discount Nominal");
            solpayWebHelper.delay(850);
        }
    }

    @Then("I now on detail pembayaran paket data page {string}")
    public void iNowOnDetailPembayaranPaketDataPage(String condition) {
        setUpDetailPembayaran();
        detailPembayaran.vrfyDetailPembayaranPaketData();
        if (condition.equals("no discount")) {
            solpayWebHelper.delay(850);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Detail Pembayaran Page - No Discount");
            solpayWebHelper.delay(850);
        } else if (condition.equals("discount admin")) {
            detailPembayaran.discountAdmin();
            solpayWebHelper.delay(850);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Detail Pembayaran Page - Discount Admin");
        } else if (condition.equals("discount nominal")) {
            detailPembayaran.discountNominal();
            solpayWebHelper.delay(850);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Detail Pembayaran Page -  Discount Nominal");
            solpayWebHelper.delay(850);
        }
    }

    @Then("I now on detail pembayaran Telkom page {string}")
    public void iNowOnDetailPembayaranTelkomPage(String condition) {
        setUpDetailPembayaran();
        detailPembayaran.vrfyDetailPembayaranTelkom();
        if (condition.equals("no discount")) {
            solpayWebHelper.delay(850);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Detail Pembayaran Page - No Discount");
            solpayWebHelper.delay(850);
        } else if (condition.equals("discount admin")) {
            detailPembayaran.discountAdmin();
            solpayWebHelper.delay(850);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Detail Pembayaran Page - Discount Admin");
        } else if (condition.equals("discount nominal")) {
            detailPembayaran.discountNominal();
            solpayWebHelper.delay(850);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Detail Pembayaran Page -  Discount Nominal");
            solpayWebHelper.delay(850);
        }
    }
}
