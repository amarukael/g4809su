package com.ids.automation.stepdefinitions.sob.solusipayweb;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.sob.SOBHomePages;
import com.ids.automation.stepdefinitions.sob.SOBGlobalSteps;
import io.cucumber.java.Scenario;
import pages.sob.solusipayweb.SOBSolusipayWebBillerPages;
import helper.SOBHelper;
import utility.Helper;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;

public class SolusipayWebBillerSteps {
    static WebDriver driver;
    static WebDriverWait wait;
    SOBLoginSteps loginSteps = new SOBLoginSteps();
    SOBSolusipayWebBillerPages billerPages;
    SOBHelper sobHelper = new SOBHelper();
    SOBHomePages homePage;
    SOBGlobalSteps globalSteps;
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;
    Integer countImage = 1;

    @When("I {string} navigate to Solusipay Web Biller")
    public void i_navigate_to_solusipay_web_biller(String condition){
        setUpSolusipayWebBiller();
        homePage.MenuDrawer();
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "HomePage");

        if (condition.equals("Successfully")){
            homePage.openNavMenu("Solusipay Web", 0, 250, "smooth");
            sobHelper.delay(1500);
            homePage.openNavSubMenu("Biller");
            sobHelper.delay(1000);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Solusipay Web - Biller");
            homePage.MenuDrawer();
            sobHelper.delay(1000);
            assertEquals(billerPages.getLblBiller(), "Biller List");
        } else {
            assertTrue(homePage.checkNoNavMenu("Solusipay Web"));
        }
    }

    public void setUpSolusipayWebBiller() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        billerPages = new SOBSolusipayWebBillerPages(driver);
    }

    @Given("I on a SolusipayWeb Biller")
    public void i_on_a_solusipay_web_biller(){
        loginSteps.setUp();
        loginSteps.setUpLogin("admin", "solusipayweb_biller");
        setUpSolusipayWebBiller();
        homePage.MenuDrawer();
        homePage.openNavMenu("Solusipay Web",0,250,"smooth");
        homePage.openNavSubMenu("Biller");
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web - Biller");
        homePage.MenuDrawer();
        sobHelper.delay(1000);
    }

    @When("I Hit Filter Button on Biller page")
    public void iHitFilterButtonOnBillerPage() {
        billerPages.hitBtnFilter();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web - Biller " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Then("I fill {string} Biller Id on Filter field")
    public void iFillBillerIdOnFilterField(String billerId) {
        billerPages.fillBillerIdOnBiller(billerId);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Biller - fill biller id" + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Then("I can see data table biller list")
    public void iCanSeeDataTableBillerList() {
        WebElement titleBillerSetting = driver.findElement(By.xpath("//h5[text()='Biller List']"));
        if (titleBillerSetting.isDisplayed()){
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Biller - look at the data table " + countImage);
            countImage++;
            sobHelper.delay(1000);
        }
    }

    @When("I click filter button again on biller")
    public void i_click_filter_button_again_on_biller() {
        setUp();
        waitForAndClickElement(By.xpath("//button[contains(text(), 'FILTER')]"));
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Biller - look at the data table " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @When("I click add biller button")
    public void i_click_add_biller_button(){
        billerPages.hitBtnAddBiller();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web - Biller " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Given("I fill {string} biller id on form add data biller")
    public void i_fill_biller_id_on_form_add_data_biller(String billerId){
        billerPages.fillBillerIdOnBiller(billerId);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Biller - fill biller id " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Given("I fill {string} biller name on form add data biller")
    public void i_fill_biller_name_on_form_add_data_biller(String billerName){
        billerPages.fillBillerName(billerName);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Biller - fill biller id " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Given("I choose {string} type biller on form add data biller")
    public void i_choose_type_biller_on_form_add_data_bilelr(String type){
        billerPages.displayListBoxType();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Biller - fill biller id " + countImage);
        countImage++;
        sobHelper.delay(1000);
        billerPages.fillTypeBiller(type);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Biller - fill biller id " + countImage);
        countImage++;
        sobHelper.delay(1000);

    }

    @Given("I choose {string} status on form add data biller")
    public void i_choose_status_on_form_add_data_biller(String status){
        billerPages.displayListStatus();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Biller - fill biller id " + countImage);
        countImage++;
        sobHelper.delay(1000);
        billerPages.fillStatus(status);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Biller - fill biller id " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @When("I click add data button on form add data biller")
    public void i_click_add_data_button_on_form_add_data_biller(){
        billerPages.hitBtnADD();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web - Biller " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Then("I verify add data biller was {string}")
    public void iVerfyAddDataBiller(String condition){
        if (condition.equals("Successfully")){
            sobHelper.delay(1000);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Solusipay Web - Biller " + countImage);
            countImage++;
            sobHelper.delay(1000);
        } else {
            WebElement errMsgBillerId = driver.findElement(By.xpath("//p[text()='Biller ID Must be number']"));
            if (errMsgBillerId.isDisplayed()){
                if(condition.equals("Failed")){
                    screenshotData = Helper.takeScreenshot(driver);
                    scenario.attach(screenshotData, "image/png", "Solusipay Web - Biller " + countImage);
                    countImage++;
                    sobHelper.delay(1000);
                }
            }
        }
    }

    private void setUp() {
        driver = BrowserSetup.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void waitForAndClickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        sobHelper.delay(1000);
    }

    private void clickElementWithCss(String cssSelector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
        element.click();
        sobHelper.delay(1000);
    }

    @Then("I verify error message field on form add data biller")
    public void iVerifyErrorMessageFieldOnFormAddDataBiller() {
        try {
            WebElement errMsgBillerId = driver.findElement(By.xpath("//p[text()='Biller ID Must be number']"));
            errMsgBillerId.isDisplayed();
        }catch (NoSuchElementException e){
            // Element is not found, then there is no need to take any action
        }
        try {
            WebElement errMsgBillerName = driver.findElement(By.xpath("//p[text()='Biller Name Cannot be empty']"));
            errMsgBillerName.isDisplayed();
        }catch (NoSuchElementException e){
            // Element is not found, then there is no need to take any action
        }
        try {
            WebElement errMsgTypeFee = driver.findElement(By.xpath("//p[text()='Type Cannot be empty']"));
            errMsgTypeFee.isDisplayed();
        }catch (NoSuchElementException e){
            // Element is not found, then there is no need to take any action
        }
        try {
            WebElement errMsgStatus = driver.findElement(By.xpath("//p[text()='Status Cannot be empty']"));
            errMsgStatus.isDisplayed();
        }catch (NoSuchElementException e){
            // Element is not found, then there is no need to take any action
        }

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "BIller - Verify error message " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Given("I click button change status biller")
    public void iClickButtonChangeStatusBiller(){
        billerPages.hitBtnChangStatus();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "BIller - Hit change status " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @When("I click button {string} confirm status")
    public void iClickbuttonConfirmStatus(String confirmStatus){
        if (confirmStatus.equals("YES")){
            billerPages.hitBtnConfirmStatusYes();
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "BIller - Hit btn Yes " + countImage);
            countImage++;
            sobHelper.delay(1000);
        }else if (confirmStatus.equals("NO")){
            billerPages.hitBtnConfirmStatusNo();
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "BIller - Hit btn No " + countImage);
            countImage++;
            sobHelper.delay(1000);
        }
        sobHelper.delay(1000);
    }

    @Then("I want to verify to see data table biller {string}")
    public void iWantToVerifyToSeeDataTableBiller(String condition){
        if (condition.equals("Successfully")){
            WebElement title = driver.findElement(By.xpath("//h5[text()='Biller List']"));
            if (title.isDisplayed()){
                screenshotData = Helper.takeScreenshot(driver);
                scenario.attach(screenshotData, "image/png", "BIller - Verify to see data table " + countImage);
                countImage++;
                sobHelper.delay(1000);
            }
        }else {
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "BIller - Verify to see data table " + countImage);
            countImage++;
            sobHelper.delay(1000);
        }
    }

    @Given("I click icon edit on biller")
    public void iClickIconEditOnBiller(){
        billerPages.hitIconEdit();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "BIller - click icon edit " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @When("I edit field {string} biller name on form edit biller")
    public void iEditBillerNameOnFormEditBiller(String billerName){
        billerPages.editBillerName(billerName);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "BIller - fill biller name " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @When("I click button edit on form edit biller")
    public void iClickButtonEditOnFromEditBiller(){
        billerPages.hitBtnEditOnEditBiller();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "BIller - click button edit " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }
}
