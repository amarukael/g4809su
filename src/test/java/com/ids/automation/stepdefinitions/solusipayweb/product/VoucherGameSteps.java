package com.ids.automation.stepdefinitions.solusipayweb.product;

import com.ids.automation.configuration.BrowserSetup;
import helper.SolusipayWebHelper;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.solusipayweb.globalpages.SolusipayWebPages;
import pages.solusipayweb.product.VoucherGamePages;
import constant.SolusipayWebConstant;
import utility.Helper;
import io.cucumber.java.Scenario;

public class VoucherGameSteps {
    public static Scenario scenario;
    static WebDriver driver;
    static WebDriverWait wait;
    VoucherGamePages voucherGame;
    SolusipayWebHelper solpayWebHelper = new SolusipayWebHelper();
    SolusipayWebConstant solusipayWebConstant;
    private byte[] screenshotData;
    Integer countImage = 1;
    SolusipayWebPages solpayWeb;

    @Before
    public void afterScenario(Scenario scenario) {
        VoucherGameSteps.scenario = scenario;
    }

    public void setUpSolpayWebVoucherGame(){
        driver = BrowserSetup.getDriver();
        if(solpayWeb == null){
            solpayWeb = new SolusipayWebPages(driver);
        }

        if(voucherGame == null){
            voucherGame = new VoucherGamePages(driver);
        }

    }

    public void setUpSolpayWebVoucherGame2(){
        driver = BrowserSetup.getDriver();
        driver.get(SolusipayWebConstant.UrlSolpayWeb);
        if(solpayWeb == null){
            solpayWeb = new SolusipayWebPages(driver);
        }

        if(voucherGame == null){
            voucherGame = new VoucherGamePages(driver);
        }

    }

    @When("I navigate to solusipayweb Voucher Game")
    public void iNavigateToSolusipaywebVoucherGame(){
        setUpSolpayWebVoucherGame();
        solpayWeb.scrollToBottomPage();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Navigate to Voucher Game Page");
        solpayWebHelper.delay(1000);
        solpayWeb.hitBtnMenuVG();
    }

    @Given("I on Solusipay Web Voucher Game Page")
    public void iOnSolusipayWebVoucherGamePage(){
        setUpSolpayWebVoucherGame2();
        solpayWebHelper.delay(3000);
        solpayWeb.scrollToBottomPage();
        solpayWebHelper.delay(1000);
        solpayWeb.hitBtnMenuVG();
        solpayWebHelper.delay(2000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Voucher Game Page");
        solpayWebHelper.delay(1000);
    }

    @Then("I'm now on the voucher game page")
    public void imNowOnTheVoucherGamePage(){
        voucherGame.vrfyVoucherGamePage();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web Page - Voucher Game Page");
    }

    @When("I fill {string} a product game on search bar field")
    public void iFillProductGameOnSearchBaField(String searchInput){
        solpayWebHelper.delay(4000);
        voucherGame.fillSearchBar(searchInput);
        solpayWebHelper.delay(1500);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Voucher Game Page - fill search bar");
        solpayWebHelper.delay(500);
    }

    @Then("I {string} to see the product I was looking for")
    public void iCanSeeAProductThatIamLookingFor(String condition){
        if (condition.equals("Successfully")) {
            solpayWebHelper.delay(1000);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Voucher Game Page - search product appear");
            solpayWebHelper.delay(1000);
        }else{
            WebElement errMsgLgth = driver.findElement(By.id("searchInputField-helper-text"));
            if (errMsgLgth.isDisplayed()){
                screenshotData = Helper.takeScreenshot(driver);
                scenario.attach(screenshotData, "image/png", "Voucher Game Page - search product doesn't appear");
                solpayWebHelper.delay(1000);
            }
        }
    }

    @When("I click button {string} product")
    public void iClickButtonCateogryProduct(String category){
        if (category.equals("All")){
            voucherGame.btnAll();
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Voucher Game Page - click category");
            solpayWebHelper.delay(1000);
        } else if (category.equals("Voucher")) {
            voucherGame.btnCtgVoucher();
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Voucher Game Page - click category");
            solpayWebHelper.delay(1000);
        } else if (category.equals("TopUp")) {
            voucherGame.btnCtgTopUp();
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Voucher Game Page - click category");
            solpayWebHelper.delay(1000);
        }
    }

    @Then("I can see product list {string} by filter category")
    public void seeProductListbyFilter(String type){
        WebElement title = driver.findElement(By.xpath("//h5[text()='"+type+"']"));
        if (title.isDisplayed()){
            System.out.println("Product list by filter appears");
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Voucher Game Page - filter product category");
            solpayWebHelper.delay(1000);
        }
    }


    @When("I choose one {string} of the products")
    public void chooseOneProduct(String product){
        voucherGame.clickProductOnProductDetail();
        solpayWebHelper.delay(1000);
        voucherGame.chooseOneOfTheProduct(product);
        solpayWebHelper.delay(850);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Voucher Game Page - choose one of the product");
        solpayWebHelper.delay(1000);
    }

    @When("I verify product detail voucher game {string}")
    public void vrfyProductDetaiVoucherGame(String game){
        voucherGame.productDetailVoucherGame(game);
        solpayWebHelper.delay(850);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Voucher Game Page - verify element product detail");
        solpayWebHelper.delay(1000);
    }

    @When("I fill {string} customer id")
    public void iFillCustomerId(String customerId){
        voucherGame.fillCustId(customerId);
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Voucher Game Page - Fill Customer Id");
        solpayWebHelper.delay(1000);
    }

    @When("I fill {string} zone id")
    public void iFillZoneId(String zoneId){
        voucherGame.fillZoneId(zoneId);
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Voucher Game Page - Fill Zone Id");
        solpayWebHelper.delay(1000);
    }

    @When("I choose {string} zoneId")
    public void iChooseZoneId(String server) {
        voucherGame.clickDropdownZoneId();
        solpayWebHelper.delay(400);
        voucherGame.clickValueZoneId(server);
        solpayWebHelper.delay(400);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Voucher Game Page - Fill Zone Id");
        solpayWebHelper.delay(1000);
    }

    @Then("I verify error message User Id")
    public void vrifyErrorMSGUserId(){
        voucherGame.errUserId();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Voucher Game Page - Verify Error Message User Id");
        solpayWebHelper.delay(1000);
    }

    @Then("I verify error message Zone Id")
    public void vrifyErrorMSGZoneId(){
        voucherGame.errZoneId();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Voucher Game Page - Verify Error Message Zone Id");
        solpayWebHelper.delay(1000);
    }

    @Then("I verify the error message if the input is wrong")
    public void vrfyErrMsgInputWrong(){
        voucherGame.snackBarErr();
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Voucher Game Page - Verify Error Message snackbar");
        solpayWebHelper.delay(1000);
    }

    @When("I click product {string} top up i was looking for")
    public void iClickProductTopUpIWasLookingFor(String game) {
        solpayWebHelper.delay(1000);
        voucherGame.clickProductTopUp(game);
        solpayWebHelper.delay(500);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Voucher Game Page - Click Product Game");
        solpayWebHelper.delay(1000);
    }

    @When("I click product {string} voucher i was looking for")
    public void iClickProductVoucherIWasLookingFor(String game) {
        solpayWebHelper.delay(1000);
        voucherGame.clickProductVoucher(game);
        solpayWebHelper.delay(500);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Voucher Game Page - Click Product Game");
        solpayWebHelper.delay(1000);
    }

    @Then("I verify product detail top up game {string} with userId_zoneId")
    public void iVerifyProductDetailTopUpGameWithUserId_zoneId(String game) {
        voucherGame.productDetailTopUpGameUserId_zoneId(game);
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Voucher Game Page - Product Game Detail");
        solpayWebHelper.delay(1000);
    }

    @Then("I verify product detail top up game {string} with userId")
    public void iVerifyProductDetailTopUpGameWithUserId(String game) {
        voucherGame.productDetailTopUpGameUserId(game);
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Voucher Game Page - Product Game Detail");
        solpayWebHelper.delay(1000);
    }

    @Then("I verify product detail top up game {string} with userId_dropdown")
    public void iVerifyProductDetailTopUpGameWithUserId_dropdown(String game) {
        voucherGame.productDetailTopUpGameUserId_dropdown(game);
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Voucher Game Page - Product Game Detail");
        solpayWebHelper.delay(1000);
    }

    @When("I fill {string} username")
    public void iFillUsername(String username) {
        voucherGame.fillUsername(username);
        solpayWebHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Voucher Game Page - Fill username");
        solpayWebHelper.delay(1000);
    }
}
