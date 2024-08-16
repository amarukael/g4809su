package com.ids.automation.stepdefinitions.sob.digitalgoods;

import static org.junit.Assert.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;

import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import pages.sob.SOBGlobalPages;
import pages.sob.SOBHomePages;
import pages.sob.digitalgoods.SOBDigitalGoodsProductPages;
import utility.Helper;

public class DigitalGoodsProductSteps {
    static WebDriver driver;
    SOBHomePages homePage;
    SOBGlobalPages globalPages;
    SOBDigitalGoodsProductPages productPages;
    SOBHelper sobHelper = new SOBHelper();
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;

    public void setUpProduct() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        globalPages = new SOBGlobalPages(driver);
        productPages = new SOBDigitalGoodsProductPages(driver);
    }

    @Then("I click Tab Product on DGMS Product Master")
    public void i_click_tab_product_on_dgms_product_master() throws Exception {
        setUpProduct();
        String sobUser = sobHelper.readProperties("extent.username.sob");
        if (sobUser.isEmpty()) {
            throw new IllegalArgumentException("Username is empty or null");
        }
        String data = "[{\"username\":\"" + sobUser + "\",\"menu\":\"/dashboard/dgms/product\"}," +
                "{\"username\":\"" + sobUser + "\",\"menu\":\"/dashboard/dgms/product/product\"}]";
        ((JavascriptExecutor) driver).executeScript(String.format(
                "window.localStorage.setItem('opened-menu', '%s');", data));
        String storedValue = (String) ((JavascriptExecutor) driver).executeScript(
                "return window.localStorage.getItem('opened-menu');");
        System.out.println("Sukses Inject Data : " + storedValue);
        driver.navigate().refresh();
        sobHelper.delay(500);
        productPages.tabProduct();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Product Page DGMS");
        globalPages.waitDatatableAppear();
        sobHelper.delay(1000);
    }

    @Then("I click field {string} and fill with {string} on DGMS Product Master Product")
    public void i_click_field_and_fill_with_on_dgms_product_master_product(String arg0, String arg1) throws Exception {
        setUpProduct();
        sobHelper.delay(500);
        if (arg0.equalsIgnoreCase("Category Name")) {
            productPages.dropFill(arg0, arg1);
            if (productPages.checkNoOption()) {
                screenshotData = Helper.takeScreenshot(driver);
                scenario.attach(screenshotData, "image/png", "Filter Section");
            }
            assertFalse("No Options", productPages.checkNoOption());
            new Actions(driver).keyDown(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        } else if (arg0.equalsIgnoreCase("Sub Category Name")) {
            productPages.listSubCategory(arg1);
            if (productPages.checkNoOption()) {
                screenshotData = Helper.takeScreenshot(driver);
                scenario.attach(screenshotData, "image/png", "Filter Section");
            }
            assertFalse("No Options", productPages.checkNoOption());
            new Actions(driver).keyDown(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        } else if (arg0.equalsIgnoreCase("Status") || arg0.equalsIgnoreCase("Supplier Name")
                || arg0.equalsIgnoreCase("PPOB ID - PPOB Name")) {
            globalPages.dropList(arg0, arg1);
        } else if (arg0.equals("Category & Sub")) {
            String[] value = arg1.split(",");
            productPages.dropFill("Category Name", value[0]);
            sobHelper.delay(500);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Sub Cat Section");
            new Actions(driver).keyDown(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
            sobHelper.delay(1000);
            productPages.dropFill("Sub Category Name", value[1]);
            sobHelper.delay(500);
            assertFalse("No Options", productPages.checkNoOption());
            new Actions(driver).keyDown(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        } else {
            String[] value = arg1.split(",");
            sobHelper.delay(500);
            if (value[0].equals("random")) {
                globalPages.inputText(arg0, Helper.generateRandomString(Integer.parseInt(value[1])));
            } else if (value[0].equals("number")) {
                globalPages.inputText(arg0, Helper.randomString2(Integer.parseInt(value[1])));
            } else if (value[0].equals("space")) {
                String spaces = " ".repeat(Integer.parseInt(value[1]));
                globalPages.inputText(arg0, spaces);
            } else if (value[0].equals("randomcase")) {
                String randomcase = sobHelper.toRandomCase(value[1]);
                globalPages.inputText(arg0, randomcase);
            } else {
                globalPages.inputText(arg0, arg1);
            }
        }
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Filter Section");

        assertFalse("Hint error appeared", globalPages.errorField());
        sobHelper.delay(200);
    }

    @Then("I choose Single Add on page")
    public void i_choose_single_add_on_page() {
        sobHelper.delay(500);
        screenshotData = Helper.takeScreenshot(driver);
        productPages.addSingleProduct();
        scenario.attach(screenshotData, "image/png", "Add Single Product");
    }
}
