package com.ids.automation.stepdefinitions.solusipayweb.product;

import com.ids.automation.configuration.BrowserSetup;
import constant.SolusipayWebConstant;
import helper.SolusipayWebHelper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.solusipayweb.globalpages.MethodPaymentPages;
import pages.solusipayweb.globalpages.SolusipayWebPages;
import pages.solusipayweb.product.PascabayarPages;
import utility.Helper;

import java.util.HashMap;
import java.util.Map;

public class PascabayarSteps {
    public static Scenario scenario;
    static WebDriver driver;
    static WebDriverWait wait;
    PascabayarPages pascbayar;
    MethodPaymentPages methodpayment;
    SolusipayWebHelper solpayWebHelper = new SolusipayWebHelper();
    SolusipayWebConstant solusipayWebConstant;
    private byte[] screenshotData;
    Integer countImage = 1;
    SolusipayWebPages solpayWeb;

    @Before
    public void afterScenario(Scenario scenario) {
        PascabayarSteps.scenario = scenario;
    }
    public void setUpSolpayWebPascabayar(){
        driver = BrowserSetup.getDriver();
        solpayWeb = new SolusipayWebPages(driver);
        pascbayar = new PascabayarPages(driver);
        methodpayment = new MethodPaymentPages(driver);
    }

    @When("I navigate to solusipayweb Pascabayar")
    public void i_navigate_to_solusipayweb_pascabayar() {
        setUpSolpayWebPascabayar();
        solpayWeb.scrollToBottomPage();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Navigate to Pascabayar Page");
        solpayWebHelper.delay(1000);
        solpayWeb.hitBtnMenuPascabayar();
    }

    @When("I choose {string} Pascabayar")
    public void i_choose_pascabayar(String arg0) throws Exception {
        solpayWebHelper.delay(1000);
        Map<String, Integer> productOptions = new HashMap<>();
        productOptions.put("FREN Pascabayar", 1);
        productOptions.put("INDOSAT Pascabayar", 2);
        productOptions.put("SMARTFREN Pascabayar", 3);
        productOptions.put("TELKOMSEL Pascabayar", 4);
        productOptions.put("THREE Pascabayar", 5);
        productOptions.put("XL Pascabayar", 6);


        if (productOptions.containsKey(arg0)) {
            pascbayar.chooseProduct(productOptions.get(arg0));
        } else {
            throw new Exception("Product Not Found");
        }
    }
    @When("I input {string} Pascabayar")
    public void i_input_pascabayar(String arg0) {
        solpayWebHelper.delay(2000);
        pascbayar.fillNumber(arg0);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Navigate to Pascabayar Page");
        solpayWebHelper.delay(2000);
    }
    @When("I click button bayar on Pascabayar")
    public void i_click_button_bayar_on_pascabayar() {
        solpayWebHelper.delay(1000);
        pascbayar.btnPayment();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Click Button Payment");
        solpayWebHelper.delay(2000);
    }
}
