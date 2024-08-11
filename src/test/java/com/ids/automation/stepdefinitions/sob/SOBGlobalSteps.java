package com.ids.automation.stepdefinitions.sob;

import com.ids.automation.configuration.BrowserSetup;
import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.sob.SOBGlobalPages;
import pages.sob.SOBHomePages;
import utility.Helper;

import java.time.Duration;

//import static com.ids.automation.stepDefinitions.sob.SOB_LoginSteps.document;

public class SOBGlobalSteps {
    static WebDriver driver;
    static WebDriverWait wait;
    SOBLoginSteps loginSteps = new SOBLoginSteps();
    SOBHomePages homePage;
    Scenario scenario = SOBLoginSteps.scenario;
    SOBHelper sobHelper = new SOBHelper();
    SOBGlobalPages globalPages;
    Integer countImage = 1;
    byte[] screenshotData;

    private void setUp() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        globalPages = new SOBGlobalPages(driver);
    }

    @Given("I click filter button on {string}")
    public void i_click_filter_button(String arg0) {
        setUp();
        waitForAndClickElement(By.xpath("//button[contains(text(), 'FILTER')]"));
        System.out.println("I'm on Click Filter Button "+arg0);
    }

    @When("I click apply button filter")
    public void i_click_apply_button_filter() {
        setUp();
        waitForAndClickElement(By.xpath("//button[contains(text(), 'APPLY')]"));
        sobHelper.delay(2000);
//        document.newPage(); // Add a new page for the rest of the content
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Filter Section");
//        Helper.addImageToPDF(document, screenshotData);
        System.out.println("Im on Click apply Filter Button");
    }

    @Given("I choose {string} partnerID on filter field")
    public void i_choose_partner_id_on_filter_field(String datavalue) {
        setUp();
        WebElement partnerIdElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("mui-component-select-partnerId")));
        Actions builder = new Actions(driver);
        builder.moveToElement(partnerIdElement).clickAndHold().perform();
        sobHelper.delay(2000);
        clickElementWithCss("[data-value='" + datavalue + "']");
        System.out.println("Im on choose partner on filter field");
    }

    private void clickElementWithCss(String cssSelector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
        element.click();
        sobHelper.delay(1000); // Add a 2-second delay after clicking an element
    }

    @When("I click button next Page")
    public void i_click_button_next_page() {
        setUp();
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Go to next page']")));
        button.click();
        System.out.println("Im on Click Next Page Button");
    }

    @When("I click filter button again")
    public void i_click_filter_button_again() {
        setUp();
        waitForAndClickElement(By.xpath("//button[contains(text(), 'FILTER')]"));
        System.out.println("Im on click filter again");
    }

    @When("I click Remove Filter button")
    public void i_click_remove_filter_button() {
        setUp();
        waitForAndClickElement(By.xpath("//button[contains(text(), 'REMOVE FILTER')]"));
        System.out.println("Im on click remove filter");
    }

    @Given("I click add data button")
    public void i_click_add_data_button() {
        setUp();
        waitForAndClickElement(By.xpath("//button[contains(text(), 'Add Data')]"));
        System.out.println("Im on click add data");
    }

    @When("I click Save Data Button")
    public void i_click_save_data_button() {
        setUp();
        waitForAndClickElement(By.xpath("//button[contains(text(), 'SAVE DATA')]"));
        System.out.println("Im on click Save data");
    }

    @Then("I can see Success Notification Snackbar")
    public void i_can_see_success_notification_snackbar() {
        System.out.println("Im on Success Notif");
    }

    @Then("I can see Duplicate Partner Notification Snackbar")
    public void i_can_see_duplicate_partner_notification_snackbar() {
        System.out.println("Im on Duplicate partner Notif");

    }

    @When("I click Yes for Confirmation")
    public void i_click_yes_for_confirmation() {
        setUp();
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'YES')]")));
        button.click();
        System.out.println("Im on Yes Confirmation");
    }

    @Then("I click pencil icon for Edit")
    public void i_click_pencil_icon_for_edit() {
        System.out.println("Im on pencil icon edit");
    }

    private void waitForAndClickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        sobHelper.delay(1000);
    }

    @When("I hit {string} Button {string} times")
    public void iHitButtonTimes(String arg0, String arg1) throws Exception{
        setUp();
        Integer intArg1 = Integer.parseInt(arg1);
        sobHelper.delay(500);
        if (arg0.equals("next")){
            globalPages.nextDataTable(intArg1);
        } else if (arg0.equals("prev")) {
            globalPages.nextDataTable(intArg1);
            globalPages.prevDataTable(intArg1);
        } else {
            throw new Exception("Something Wrong I Can Feel It");
        }
    }

    @Then("I Hit Filter Button")
    public void iHitFilterButton() {
        setUp();
        globalPages.filterButton();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "DigitalGoods - Transaction");
        sobHelper.delay(500);
    }

    @Given("I am in Menu {string} and Sub-Menu {string}")
    public void iAmInMenuAndSubMenu(String menu, String subMenu) {
        String sobUser = sobHelper.readProperties("extent.username.sob");
        System.out.println(sobUser);
        if (sobUser.isEmpty()) {
            throw new IllegalArgumentException("Username is empty or null");
        }
        loginSteps.setUp();
        loginSteps.setUpLogin(sobUser, menu +"_"+subMenu);
        setUp();
        homePage.MenuDrawer();
        homePage.openNavMenu(menu, 0, 250, "smooth");
        homePage.openNavSubMenu(subMenu);
        sobHelper.delay(800);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", menu+" - "+subMenu);
        homePage.MenuDrawer();
        sobHelper.delay(1500);
    }

    @Then("I fill From Date \\({string}) and To Date \\({string}) on {string}")
    public void iFillFromDateAndToDateOn(String arg0, String arg1, String arg2) {
        i_fill_from_date_on_Dana_transaction_suspect(arg0);
        i_fill_to_date_on_Dana_transaction_suspect(arg1);
        System.out.println("Successfully fill date on "+arg2);
    }

    @Given("I fill From Date \\({string}) on {string}")
    public void i_fill_from_date_on_Dana_transaction_suspect(String sDate) {
        String[] value = sDate.split(",");
        globalPages.fillFromDate(value);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Filter Date");
        sobHelper.delay(500);
    }

    @Given("I fill To Date \\({string}) on {string}")
    public void i_fill_to_date_on_Dana_transaction_suspect(String sDate) {
        String[] value = sDate.split(",");
        globalPages.fillToDate(value);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Filter Date");
        sobHelper.delay(500);
    }

    @Then("Datatable show data {string}")
    public void datatableShowData(String arg0) throws Exception {
        sobHelper.delay(500);
        globalPages.waitDatatableAppear();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", arg0
                + countImage);
        countImage++;
        sobHelper.delay(1000);
        globalPages.scrollDataTabletoLeft(3000);
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", arg0
                + countImage);
        countImage++;
        sobHelper.delay(500);
    }

    @Then("I {string} navigate to {string}")
    public void iNavigateTo(String arg0, String arg1) {
        setUp();
        String[] value = arg1.split(", ");
        homePage.MenuDrawer();
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Homepage");
        homePage.openNavMenu(value[0], 0, 250, "smooth");
        sobHelper.delay(1000);
        homePage.openNavSubMenu(value[1]);
        sobHelper.delay(800);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "CICO - Transaction --" + arg0);
        homePage.MenuDrawer();
        sobHelper.delay(1000);
    }
}
