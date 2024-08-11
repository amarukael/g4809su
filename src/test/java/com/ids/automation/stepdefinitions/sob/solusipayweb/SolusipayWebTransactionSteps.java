package com.ids.automation.stepdefinitions.sob.solusipayweb;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;
import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.sob.SOBHomePages;
import pages.sob.solusipayweb.SOBSolusipayWebTransactionPages;
import utility.Helper;

import java.time.Duration;

import static org.testng.Assert.*;

public class SolusipayWebTransactionSteps {
    static WebDriver driver;
    static WebDriverWait wait;
    SOBLoginSteps loginSteps = new SOBLoginSteps();
    SOBHelper sobHelper = new SOBHelper();
    SOBHomePages homePage;
    SOBSolusipayWebTransactionPages transactionPages;
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;
    Integer countImage = 1;

    @When("I {string} navigate to SolusipayWeb Transaction")
    public void i_navigate_to_solusipayweb_transaction (String condition){
        setUpSolusipayWebTransaction();
        homePage.MenuDrawer();
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Homepage");

        if (condition.equals("Successfully")) {
            homePage.openNavMenu("Solusipay Web", 0, 250, "smooth");
            sobHelper.delay(1500);
            homePage.openNavSubMenu("Transaction");
            sobHelper.delay(1000);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Solusipay Web - Transaction");
            homePage.MenuDrawer();
            sobHelper.delay(1000);
            assertEquals(transactionPages.getLblTransaction(), "Transaction List");
        } else {
            assertTrue(homePage.checkNoNavMenu("Solusipay Web"));
        }
    }

    @Given("I on a SolusipayWeb Transaction Page")
    public void iOnASolusipayWebTransactionPage() {
        loginSteps.setUp();
        loginSteps.setUpLogin("admin", "solusipayweb_transaction");
        setUpSolusipayWebTransaction();
        homePage.MenuDrawer();
        homePage.openNavMenu("Solusipay Web", 0, 250, "smooth");
        homePage.openNavSubMenu("Transaction");
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web - Transaction");
        homePage.MenuDrawer();
        sobHelper.delay(1000);
    }

    @Given("I hit Filter Button")
    public void i_hit_filter_button() {
        transactionPages.hitBtnFilter();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web - Transaction " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Given("I fill from date on filter field {string}")
    public void iFillFromDateOnFilterField(String sDate) {
        transactionPages.applyFromDate(sDate);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web - Filter From Date");
        sobHelper.delay(500);
    }
    @Given("I fill to date on filter field {string}")
    public void iFillToDateOnFilterField(String sDate) {
        transactionPages.applyToDate(sDate);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web - Filter To Date");
        sobHelper.delay(500);
    }

    @When("I fill From Date on Filter Field")
    public void i_fill_from_date_on_filter_field() {
        String[] valueDate = {"01", "03"};
        transactionPages.fillFromDate(valueDate);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web - Transaction " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }
    @When("I fill To Date on Filter Field")
    public void i_fill_to_date_on_filter_field() {
        String[] valueDate = {"07", "03"};
        transactionPages.fillToDate(valueDate);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web - Transaction " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Given("I choose {string} status on filter field")
    public void i_choose_status_on_filter_field(String datavalue) {
        setUp();
        WebElement statusElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("mui-component-select-status")));
        Actions builder = new Actions(driver);
        builder.moveToElement(statusElement).clickAndHold().perform();
        sobHelper.delay(2000);
        clickElementWithCss("[data-value='" + datavalue + "']");

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web - Transaction Status");
        sobHelper.delay(1000);
        System.out.println("Im on choose status on filter field");
    }
    @Given("I fill {string} with {string}")
    public void i_fill_with(String field, String value) {
        if (field.equals("Order ID")) {
            transactionPages.fillOrderIdField(value);
        } else if (field.equals("Customer ID")) {
            transactionPages.fillCustomerIdField(value);
        }

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Fill fields");
        System.out.println("Im on fill fields");
    }
    @And("{string} error message partner id is empty")
    public void errorMessagePartnerIdIsEmpty(String condition) {
        System.out.println("Im on Before check error message");
        WebElement errMsg = driver.findElement(By.xpath("//body/div[contains(@class, 'MuiModal-root')]/div[contains(@class, 'ModelFilter')]/div[contains(@class, 'MuiStack-root')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo({left: 0, top: -400, behavior: 'smooth'});", errMsg);
//        ((JavascriptExecutor) driver).executeScript("const x = document.querySelector('body > div.MuiModal-root.css-8ndowl > div.ModelFilter.MuiBox-root.css-1xg6nuv > div.MuiStack-root.css-vrnrl8'); x.scrollTo(0, -250);");
        sobHelper.delay(800);

        if (condition.equals("Failed")){
            assertTrue(transactionPages.getTitleErrorMsgPartnerId());
        }

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Error Message");
        sobHelper.delay(1000);
        System.out.println("Im on After check error message");
    }
    private void clickElementWithCss(String cssSelector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
        element.click();
        sobHelper.delay(1000); // Add a 2-second delay after clicking an element
    }
    @Then("Data table show data Transaction Suspect")
    public void dataTableShowDataTransactionSuspect() {
        homePage.scrollBody(200);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Transaction Suspect");
        sobHelper.delay(1000);
    }

    public void setUpSolusipayWebTransaction() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        transactionPages = new SOBSolusipayWebTransactionPages(driver);
    }

    private void setUp() {
        driver = BrowserSetup.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
