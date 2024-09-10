package com.ids.automation.stepdefinitions.sob.solusipayweb;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;
import com.ids.automation.stepdefinitions.sob.SOBGlobalSteps;
import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.sob.solusipayweb.SOBSolusipayWebPartnerSettingPages;
import pages.sob.SOBHomePages;
import utility.Helper;
import java.time.Duration;
import static org.testng.Assert.*;

public class SolusipayWebPartnerSettingSteps {
    static WebDriver driver;
    static WebDriverWait wait;
    SOBLoginSteps loginSteps = new SOBLoginSteps();
    SOBSolusipayWebPartnerSettingPages partnerSettingPages;
    SOBHelper sobHelper = new SOBHelper();
    SOBHomePages homePage;
    SOBGlobalSteps globalSteps;
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;
    Integer countImage = 1;

    @When("I {string} navigate to SolusipayWeb Partner Setting")
    public void i_navigate_to_solusipay_web_partner_setting(String condition){
        setUpSolusipayWebPartnerSetting();
        homePage.MenuDrawer();
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Homepage");

        if (condition.equals("Successfully")) {
            homePage.openNavMenu("Solusipay Web", 0, 250, "smooth");
            sobHelper.delay(1500);
            homePage.openNavSubMenu("Partner Setting");
            sobHelper.delay(1000);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Solusipay Web - Partner Setting");
            homePage.MenuDrawer();
            sobHelper.delay(1000);
            assertEquals(partnerSettingPages.getLblPartnerSetting(), "Partner List");
        } else {
            assertTrue(homePage.checkNoNavMenu("Solusipay Web"));
        }
    }

    @Given("I on a SolusipayWeb Partner Setting Page")
    public void i_on_a_solusipayweb_partner_setting_page(){
        loginSteps.setUp();
        loginSteps.setUpLogin("admin", "solusipay2eb_partnersetting");
        setUpSolusipayWebPartnerSetting();
        homePage.MenuDrawer();
        homePage.openNavMenu("Solusipay Web",0,250,"smooth");
        homePage.openNavSubMenu("Partner Setting");
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web - Partner Setting");
        homePage.MenuDrawer();
        sobHelper.delay(1000);
    }

    public void setUpSolusipayWebPartnerSetting() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        partnerSettingPages = new SOBSolusipayWebPartnerSettingPages(driver);
    }

    @Given("I hit Filter Button on Partner Setting page")
    public void i_hit_filter_button_on_partner_setting_page() {
        partnerSettingPages.hitBtnFilter();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Partner Setting - hit filter button" + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Given("I hit Add Partner Button on Partner Setting page")
    public void i_hit_add_partner_button_on_partner_setting_page() {
        partnerSettingPages.hitBtnAddPartner();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Partner Setting - hit add partner button" + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Given("I click Add Data")
    public void clickAddData(){
        sobHelper.delay(2000);
        WebElement btnAdd = driver.findElement(By.xpath("//button[text()='ADD']"));
        btnAdd.click();

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Partner Setting - click add data partner" + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Given("I click Cancel Add Data Partner")
    public void clickCancelAddDataPartner(){
        sobHelper.delay(2000);
        WebElement btnCancel = driver.findElement(By.xpath("//button[contains(text(), 'CANCEL')]"));
        btnCancel.click();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Partner Setting - click cancel add data partner" + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Given("I fill Partner Id {string}")
    public void i_fill_field_partner_id(String partnerId){
        partnerSettingPages.fillPartnerIdField(partnerId);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Partner Setting -  fill field partner id" + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Given("I fill Partner Name {string}")
    public void i_fill_field_partner_name(String partnerName){
        partnerSettingPages.fillPartnerNameField(partnerName);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Partner Setting -  fill field partner name" + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Given("I fill Admin Fee {string}")
    public void i_fill_admin_fee(String adminFee){
        partnerSettingPages.fillAdminFee(adminFee);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Partner Setting -  fill field admin fee" + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Given("I fill Session Time {string}")
    public void i_fill_session_time(String sessionTime){
        partnerSettingPages.fillSessionTime(sessionTime);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Partner Setting -  fill field session time" + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Given("I choose {string} partner id on filter field partner setting")
    public void i_choose_partner_id_on_filter_field_partner_setting(String datavalue) {
        setUp();
        WebElement partnerIdElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("mui-component-select-partnerId")));
        Actions builder = new Actions(driver);
        builder.moveToElement(partnerIdElement).clickAndHold().perform();
        sobHelper.delay(2000);
        clickElementWithCss("[data-value='" + datavalue + "']");

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web - Choose Partner Id");
        sobHelper.delay(1000);
        System.out.println("Im on choose partner on filter field");
    }

    @Given("I choose {string} partner name on filter field")
    public void i_choose_partner_name_on_filter_field(String datavalue) {
        setUp();
        WebElement partnerNameElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("mui-component-select-partnerName")));
        Actions builder = new Actions(driver);
        builder.moveToElement(partnerNameElement).clickAndHold().perform();
        sobHelper.delay(2000);
        clickElementWithCss("[data-value='" + datavalue + "']");

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Partner Setting - Choose Partner Name");
        sobHelper.delay(1000);
        System.out.println("Im on choose partner name on filter field");
    }

    @Given("I choose {string} status on add partner")
    public void i_choose_status_on_add_partner(String status) {
        partnerSettingPages.displayListStatus();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Partner Setting - choose status " + countImage);
        countImage++;
        sobHelper.delay(1000);
        partnerSettingPages.fillStatusAddParter(status);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Partner Setting - choose status " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Then("I {string} Add Data Partner")
    public void iAddDataPartner(String condition) {
        if(condition.equals("Successfully")){
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Partner Setting Suspect" + countImage);
            countImage++;
            sobHelper.delay(1000);
        } else {
            try {
                WebElement errMsgPartnerId = driver.findElement(By.xpath("//p[text()='Partner ID Cannot be empty']"));
                errMsgPartnerId.isDisplayed();
            }catch (NoSuchElementException e){
                // Element is not found, then there is no need to take any action
            }
            try {
                WebElement errMsgPartnerName = driver.findElement(By.xpath("//p[text()='Partner Name Cannot be empty']"));
                errMsgPartnerName.isDisplayed();
            }catch (NoSuchElementException e){
                // Element is not found, then there is no need to take any action
            }
            try {
                WebElement errMsgAdminFee = driver.findElement(By.xpath("//p[text()='Admin Fee Cannot be empty']"));
                errMsgAdminFee.isDisplayed();
            }catch (NoSuchElementException e){
                // Element is not found, then there is no need to take any action
            }
            try {
                WebElement errMsgSessionTime = driver.findElement(By.xpath("//p[text()='Session Time Cannot be empty']"));
                errMsgSessionTime.isDisplayed();
            }catch (NoSuchElementException e){
                // Element is not found, then there is no need to take any action
            }

            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Partner Setting Suspect" + countImage);
            countImage++;
            sobHelper.delay(1000);
        }
    }

    @Then("Data table show data Partner Setting Suspect")
    public void dataTableShowDataPartnerSettingSuspect() {
        homePage.scrollBody(200);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Transaction Suspect");
        sobHelper.delay(1000);
    }

    private void setUp() {
        driver = BrowserSetup.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void clickElementWithCss(String cssSelector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
        element.click();
        sobHelper.delay(1000);
    }

    private void waitForAndClickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        sobHelper.delay(1000);
    }

    @When("I click button detail partner list")
    public void iClickButtonDetailPartnerList() {
        WebElement btnDetail = driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-hkn3qg']"));
        btnDetail.click();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web - Partner Setting " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Then("I on a Detail Partner Setting Page")
    public void iOnADetailPartnerSettingPage() {
        WebElement title = driver.findElement(By.xpath("//h6[text()='Partner Detail']"));
        if (title.isDisplayed()){
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Solusipay Web - Partner Setting " + countImage);
            countImage++;
            sobHelper.delay(1000);
            homePage.scrollBody(920);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Solusipay Web - Partner Setting " + countImage);
            countImage++;
            sobHelper.delay(1000);
        } else {
            System.out.println("I'm not on detail partner page");
        }
    }

    @Given("I click button change data on partner detail page")
    public void i_click_button_change_data_on_partner_detail_page(){
        partnerSettingPages.hitBtnChangeData();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Partner Setting - hit change data button" + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Given("I want to edit data session time on partner detail {string}")
    public void i_want_to_edit_data_session_time_on_partner_detail(String sessionTime){
        partnerSettingPages.changeSessionTimePartner(sessionTime);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Partner Setting - hit change data button" + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @When("I click button save data on partner detail page")
    public void iClickButtonSaveDataOnPartnerDetailPage() {
        partnerSettingPages.hitBtnSaveData();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Partner Setting - hit save data button" + countImage);
        countImage++;
        sobHelper.delay(1000);
    }


    @Then("I success change data on partner detail page")
    public void iSuccessChangeDataOnPartnerDetailPage() {
        WebElement vrfyChangeData= driver.findElement(By.xpath("//button[text()='Change Data']"));
        if (vrfyChangeData.isDisplayed()) {
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Partner Setting - hit save data button" + countImage);
            countImage++;
            sobHelper.delay(1000);
        }
    }

    @When("I scroll to biller setting on partner detail page")
    public void iScrollToBillerSettingOnPartnerDetailPage() {
        homePage.scrollBody(920);
        WebElement titleBillerSetting = driver.findElement(By.xpath("//h6[text()='Biller Settings']"));
        if (titleBillerSetting.isDisplayed()){
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Partner Setting - scroll to biller setting" + countImage);
            countImage++;
            sobHelper.delay(1500);
        }
    }

    @Given("I click button add biller on partner detail page")
    public void i_click_button_add_biller_on_partner_detail_page(){
        partnerSettingPages.hitBtnAddBiller();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Partner Setting - hit add biller button" + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Given("I choose biller name {string} on add biller")
    public void i_choose_biller_name_on_add_biller(String datavalue){
        setUp();
        WebElement partnerIdElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("mui-component-select-billerId")));
        Actions builder = new Actions(driver);
        builder.moveToElement(partnerIdElement).clickAndHold().perform();
        sobHelper.delay(2000);
        clickElementWithCss("[data-value='" + datavalue + "']");

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Solusipay Web - Choose Partner Id");
        sobHelper.delay(1000);
        System.out.println("Im on choose partner on filter field");
    }

    @Given("I can see data table after add new biller")
    public void i_can_see_data_table_after_add_new_biller(){
        WebElement titleBillerSetting = driver.findElement(By.xpath("//h6[text()='Biller Settings']"));
        if (titleBillerSetting.isDisplayed()){
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Partner Setting - look at the data table" + countImage);
            countImage++;
            sobHelper.delay(1000);
        }
    }

    @Given("I can see data table after filter data")
    public void i_can_see_data_table_after_filter_data(){
        WebElement titleBillerSetting = driver.findElement(By.xpath("//h6[text()='Biller Settings']"));
        if (titleBillerSetting.isDisplayed()){
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Partner Setting - look at the data table" + countImage);
            countImage++;
            sobHelper.delay(1000);
        }
    }

    @Given("I fill {string} biller id on filter biller setting")
    public void i_fill_biller_id_on_filter_biller_setting(String billerId){
        partnerSettingPages.fillBillerIdOnBillerSetting(billerId);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Partner Setting - fill biller id on biller setting" + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Given("I click action button status on data table biller setting")
    public void i_click_action_button_status(){
        partnerSettingPages.hitBtnChangeStatus();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Partner Setting - change biller status" + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Given("I click button yes")
    public void i_click_button_yes(){
        partnerSettingPages.hitBtnYes();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Partner Setting - hit button Yes" + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Given("I can see data table after change status")
    public void i_can_see_data_table_biller_setting(){
        WebElement titleBillerSetting = driver.findElement(By.xpath("//h6[text()='Biller Settings']"));
        if (titleBillerSetting.isDisplayed()){
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Partner Setting - look at the data table" + countImage);
            countImage++;
            sobHelper.delay(1000);
        }
    }

    @Given("I can see data table delete biller")
    public void i_can_see_data_table_after_delete_biller(){
        WebElement titleBillerSetting = driver.findElement(By.xpath("//h6[text()='Biller Settings']"));
        if (titleBillerSetting.isDisplayed()){
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Partner Setting - look at the data table" + countImage);
            countImage++;
            sobHelper.delay(1000);
        }
    }

    @Given("I click icon delete for delete biller")
    public void i_click_icon_delete(){
        partnerSettingPages.hitBtnDelete();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Partner Setting - hit button delete" + countImage);
        countImage++;
        sobHelper.delay(1000);
    }
}
