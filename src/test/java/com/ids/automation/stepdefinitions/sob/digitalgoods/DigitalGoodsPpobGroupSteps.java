package com.ids.automation.stepdefinitions.sob.digitalgoods;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;
import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import org.openqa.selenium.*;
import pages.sob.SOBGlobalPages;
import pages.sob.SOBHomePages;
import pages.sob.digitalgoods.SOBDigitalGoodsPpobGroupPages;
import utility.Helper;

public class DigitalGoodsPpobGroupSteps {
    static WebDriver driver;
    SOBHomePages homePage;
    SOBGlobalPages globalPages;
    SOBDigitalGoodsPpobGroupPages ppobGroupPages;
    SOBHelper sobHelper = new SOBHelper();
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;

    public void setUpPPOBGroup() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        globalPages = new SOBGlobalPages(driver);
        ppobGroupPages = new SOBDigitalGoodsPpobGroupPages(driver);
    }

    @Then("I click Tab PPOB Group on DGMS Product Master")
    public void i_click_tab_ppob_group_on_dgms_product_master() throws Exception {
        setUpPPOBGroup();
        String sobUser = sobHelper.readProperties("extent.username.sob");
        if (sobUser.isEmpty()) {
            throw new IllegalArgumentException("Username is empty or null");
        }
        String data = "[{\"username\":\""+sobUser+"\",\"menu\":\"/dashboard/dgms/product\"}," +
                "{\"username\":\""+sobUser+"\",\"menu\":\"/dashboard/dgms/product/product\"}]";
        ((JavascriptExecutor) driver).executeScript(String.format(
                "window.localStorage.setItem('opened-menu', '%s');", data));
        driver.navigate().refresh();
        sobHelper.delay(500);
        ppobGroupPages.tabPPOBGroup();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Using User : "+sobUser);
        globalPages.waitDatatableAppear();
        sobHelper.delay(1000);
    }

    @Then("I click field {string} and fill with {string} on DGMS Product Master PPOB Group")
    public void i_click_field_and_fill_with_on_dgms_product_master_ppob_group(String arg0, String arg1) {
        setUpPPOBGroup();
        String[] value = arg1.split(",");
        sobHelper.delay(500);
        if (value[0].equalsIgnoreCase("random")){
            globalPages.inputText(arg0, Helper.generateRandomString(Integer.parseInt(value[1])));
        }else {
            globalPages.inputText(arg0,value[0]);
        }
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Filter Section");
        sobHelper.delay(500);
    }

    @Then("I fill PPOB ID with {string} on DGMS Product Master PPOB Group")
    public void i_fill_ppobid_with_on_dgms_product_master_ppob_group(String arg0) {
        String[] value = arg0.split(",");
        sobHelper.delay(500);
        if (value[0].equalsIgnoreCase("random")){
            globalPages.inputText("PPOB ID", Helper.generateRandomString(Integer.parseInt(value[1])));
        } else if (value[0].equalsIgnoreCase("null")) {
            globalPages.inputText("PPOB ID","");
        } else if (value[0].equalsIgnoreCase("space")) {
            WebElement field = driver.findElement(By.xpath("(//label[text()='PPOB ID']/following::input)[1]"));
            field.click();
            for (int i = 0; i < 10; i++) {
                field.sendKeys(Keys.SPACE);
            }
        } else if (value[0].equalsIgnoreCase("number")) {
            globalPages.inputText("PPOB ID",Helper.randomString2(Integer.parseInt(value[1])));
        } else {
            globalPages.inputText("PPOB ID",value[0]);
        }
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Filter Section");
        sobHelper.delay(200);
    }

    @Then("I fill Description with {string} on DGMS Product Master PPOB Group")
    public void i_fill_description_with_on_dgms_product_master_ppob_group(String arg0) {
        String[] value = arg0.split(",");
        sobHelper.delay(500);
        if (value[0].equalsIgnoreCase("random")){
            globalPages.inputText("Description", Helper.generateRandomString(Integer.parseInt(value[1])));
        }else {
            globalPages.inputText("Description",value[0]);
        }
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Filter Section");
        sobHelper.delay(200);
    }

    @Then("I fill PPOB Description with {string} on DGMS Product Master PPOB Group")
    public void i_fill_ppob_description_with_on_dgms_product_master_ppob_group(String arg0) {
        String[] value = arg0.split(",");
        sobHelper.delay(500);
        if (value[0].equalsIgnoreCase("random")){
            globalPages.inputText("PPOB Description", Helper.generateRandomString(Integer.parseInt(value[1])));
        } else if (value[0].equalsIgnoreCase("null")) {
            globalPages.inputText("PPOB Description","");
        } else if (value[0].equalsIgnoreCase("space")) {
            WebElement field = driver.findElement(By.xpath("(//label[text()='PPOB Description']/following::input)[1]"));
            field.click();
            for (int i = 0; i < 10; i++) {
                field.sendKeys(Keys.SPACE);
            }
        } else {
            globalPages.inputText("PPOB Description",value[0]);
        }
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Filter Section");
        sobHelper.delay(200);
    }
}
