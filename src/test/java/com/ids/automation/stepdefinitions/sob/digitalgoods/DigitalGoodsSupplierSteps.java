package com.ids.automation.stepdefinitions.sob.digitalgoods;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;
import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import org.openqa.selenium.*;
import pages.sob.SOBGlobalPages;
import pages.sob.SOBHomePages;
import pages.sob.digitalgoods.SOBDigitalGoodsSupplierPages;
import utility.Helper;

import static org.junit.Assert.assertFalse;

public class DigitalGoodsSupplierSteps {
    static WebDriver driver;
    SOBHomePages homePage;
    SOBGlobalPages globalPages;
    SOBDigitalGoodsSupplierPages supplierPages;
    SOBHelper sobHelper = new SOBHelper();
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;

    public void setUpSupplier() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        globalPages = new SOBGlobalPages(driver);
        supplierPages = new SOBDigitalGoodsSupplierPages(driver);
    }

    @Then("I click Tab Supplier on DGMS Product Master")
    public void i_click_tab_supplier_on_dgms_product_master() throws Exception {
        setUpSupplier();
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
        supplierPages.tabSupplier();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Supplier Page DGMS");
        globalPages.waitDatatableAppear();
        sobHelper.delay(1000);
    }

    @Then("I click field {string} and fill with {string} on DGMS Product Master Supplier")
    public void i_click_field_and_fill_with_on_dgms_product_master_supplier(String arg0, String arg1) {
        setUpSupplier();
        String[] value = arg1.split(",");
        sobHelper.delay(500);
        if (value[0].equalsIgnoreCase("random")) {
            globalPages.inputText(arg0, Helper.generateRandomString(Integer.parseInt(value[1])));
        } else if (value[0].equalsIgnoreCase("number")) {
            globalPages.inputText(arg0, Helper.randomString2(Integer.parseInt(value[1])));
        } else {
            globalPages.inputText(arg0, value[0]);
        }
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Filter Section");
        assertFalse("Hint error appeared", globalPages.errorField());
        sobHelper.delay(200);
    }

    @Then("I fill Supplier Name with {string} on DGMS Product Master Supplier")
    public void i_fill_supplier_name_with_on_dgms_product_master_supplier(String arg0) {
        setUpSupplier();
        String[] value = arg0.split(",");
        sobHelper.delay(500);
        if (value[0].equalsIgnoreCase("random")) {
            globalPages.inputText("Supplier Name", Helper.generateRandomString(Integer.parseInt(value[1])));
        } else if (value[0].equalsIgnoreCase("null")) {
            globalPages.inputText("Supplier Name", "");
        } else if (value[0].equalsIgnoreCase("space")) {
            WebElement field = driver.findElement(By.xpath("(//label[text()='Supplier Name']/following::input)[1]"));
            field.click();
            for (int i = 0; i < 1; i++) {
                field.sendKeys(Keys.SPACE);
            }
        } else {
            globalPages.inputText("Supplier Name", value[0]);
        }
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Filter Section");
        sobHelper.delay(200);
    }

    @Then("I fill Product Type with {string} on DGMS Product Master Supplier")
    public void i_fill_product_type_with_on_dgms_product_master_supplier(String arg0) {
        setUpSupplier();
        String[] value = arg0.split(",");
        if (value[0].equalsIgnoreCase("random")) {
            globalPages.inputText("Product Type", Helper.generateRandomString(Integer.parseInt(value[1])));
        } else if (value[0].equalsIgnoreCase("null")) {
            globalPages.inputText("Product Type", "");
        } else if (value[0].equalsIgnoreCase("space")) {
            WebElement field = driver.findElement(By.xpath("(//label[text()='Product Type']/following::input)[1]"));
            field.click();
            for (int i = 0; i < 1; i++) {
                field.sendKeys(Keys.SPACE);
            }
        } else {
            globalPages.inputText("Product Type", value[0]);
        }
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Filter Section");
        sobHelper.delay(200);
    }

    @Then("I fill Masking Name with {string} on DGMS Product Master Supplier")
    public void i_fill_masking_name_with_on_dgms_product_master_supplier(String arg0) {
        setUpSupplier();
        String[] value = arg0.split(",");
        sobHelper.delay(500);
        if (value[0].equalsIgnoreCase("random")) {
            globalPages.inputText("Masking Name", Helper.generateRandomString(Integer.parseInt(value[1])));
        } else if (value[0].equalsIgnoreCase("null")) {
            globalPages.inputText("Masking Name", "");
        } else if (value[0].equalsIgnoreCase("space")) {
            WebElement field = driver.findElement(By.xpath("(//label[text()='Masking Name']/following::input)[1]"));
            field.click();
            for (int i = 0; i < 1; i++) {
                field.sendKeys(Keys.SPACE);
            }
        } else {
            globalPages.inputText("Masking Name", value[0]);
        }
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Filter Section");
        sobHelper.delay(200);
    }

    @Then("I fill Supplier ID with {string} on DGMS Product Master Supplier")
    public void i_fill_supplier_id_with_on_dgms_product_master_supplier(String arg0) {
        setUpSupplier();
        String[] value = arg0.split(",");
        sobHelper.delay(500);
        if (value[0].equalsIgnoreCase("random")) {
            globalPages.inputText("Supplier ID", Helper.generateRandomString(Integer.parseInt(value[1])));
        } else if (value[0].equalsIgnoreCase("null")) {
            globalPages.inputText("Supplier ID", "");
        } else if (value[0].equalsIgnoreCase("space")) {
            WebElement field = driver.findElement(By.xpath("(//label[text()='Supplier Name']/following::input)[1]"));
            field.click();
            for (int i = 0; i < 1; i++) {
                field.sendKeys(Keys.SPACE);
            }
        } else {
            globalPages.inputText("Supplier ID", value[0]);
        }
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Filter Section");
        sobHelper.delay(200);
    }
}
