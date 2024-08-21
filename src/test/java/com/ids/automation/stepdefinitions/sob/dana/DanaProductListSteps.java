package com.ids.automation.stepdefinitions.sob.dana;

import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;

import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.sob.SOBGlobalPages;
import pages.sob.SOBHomePages;
import pages.sob.dana.SOBDanaProductListPages;
import utility.Helper;

public class DanaProductListSteps {

    static WebDriver driver;
    SOBLoginSteps loginSteps = new SOBLoginSteps();
    SOBHomePages homePage;
    SOBDanaProductListPages prodListPage;
    SOBHelper sobHelper = new SOBHelper();
    SOBGlobalPages globalPages;
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;
    Integer countImage = 1;

    @Then("I {string} navigate to Dana Product List")
    public void i_navigate_to_Dana_product_list(String condition) {
        setUpPordList();
        homePage.MenuDrawer();
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Homepage");
        homePage.openNavMenu("DANA", 0, 250, "smooth");
        sobHelper.delay(1000);
        homePage.openNavSubMenu("Product");
        sobHelper.delay(800);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Dana - Product List " + condition);
        homePage.MenuDrawer();
        sobHelper.delay(1000);
    }

    public void setUpPordList() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        prodListPage = new SOBDanaProductListPages(driver);
    }

    @Then("Dana Display Action Column in Product List")
    public void dana_display_action_column_in_product_list() {
        prodListPage.scrollDatatabletoLeft(300);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Dana - Product List"
                + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Then("Dana Datatable show Product List")
    public void DanaDatatableShowProductList() {
        prodListPage.waitDatatableDanaAppear();
        globalPages.scrollToBottom();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Dana - Product List" + countImage);
        countImage++;

        assertTrue(prodListPage.getDataTable());
        assertEquals(prodListPage.getRowsDataTable(), prodListPage.getRowsPerPage());
        sobHelper.delay(1000);
    }

    @Then("Dana Datatable {string} show Product List")
    public void dana_datatable_show_product_list(String arg0) {
        Boolean bool = prodListPage.getAlert();
        if (bool.equals(true)) {
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Dana - Product List" + arg0 + countImage);
            countImage++;
        } else {
            prodListPage.waitDatatableDanaAppear();
            globalPages.scrollToBottom();
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Dana - Product List" + countImage);
            countImage++;

            assertTrue(prodListPage.getDataTable());
            assertEquals(prodListPage.getRowsDataTable(), prodListPage.getRowsPerPage());
            sobHelper.delay(1000);
        }
    }

    @When("I hit Apply Button on Dana Product List")
    public void i_hit_apply_button_on_dana_product_list() {
        prodListPage.hitBtnApply();
        sobHelper.delay(500);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Dana - Transaction Suspect "
                + countImage);
        countImage++;
    }

    @Given("I hit Filter Button on Dana Product List")
    public void i_hit_filter_button_on_dana_product_list() {
        prodListPage.hitBtnFilter();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Dana - Transaction Suspect "
                + countImage);
        countImage++;
        sobHelper.delay(100);
    }

    @Given("I am in Dana Product List")
    public void i_am_in_dana_product_list() {
        loginSteps.setUp();
        loginSteps.setUpLogin("adminstg", "dana_product_list");
        setUpPordList();
        homePage.MenuDrawer();
        homePage.openNavMenu("DANA", 0, 250, "smooth");
        homePage.openNavSubMenu("Product");
        sobHelper.delay(800);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Dana - Product List");
        homePage.MenuDrawer();
        sobHelper.delay(1000);
    }

    @Given("I choose {string} and fill with {string} on Dana Product")
    public void i_choose_and_fill_with_on_dana_product(String field, String value) throws Exception {
        Map<String, String> fieldMap = new HashMap<>();
        fieldMap.put("Product ID", "productId");
        fieldMap.put("New Product ID", "productIdNew");
        fieldMap.put("Supplier ID", "supplierId");
        fieldMap.put("Product Description", "productDesc");
        fieldMap.put("Status", "mui-component-select-isActive");

        if (fieldMap.containsKey(field)) {
            if (field.equals("Status")) {
                value = value.equals("Active") ? "1" : "0";
            }
            prodListPage.fillFiterField(value, fieldMap.get(field));
        } else {
            throw new Exception("Field not listed");
        }

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Filter " + field);
        sobHelper.delay(500);
    }

    @Then("I Hit Switch Button Product Row {string} to {string} Product")
    public void iHitSwitchButtonProductRowToProduct(String arg0, String arg1) {
        prodListPage.waitDatatableDanaAppear();
        try {
            prodListPage.SwitchButton(arg0, arg1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Product List");
        sobHelper.delay(500);
    }

    @Then("I Hit {string} Button in Status Confirmation and Show Alert on Dana Product")
    public void iHitButtonInStatusConfirmationAndShowAlert(String arg0) {
        sobHelper.delay(500);
        try {
            prodListPage.statusConfirmation(arg0);
            prodListPage.waitButtonSwitched();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Product List");
        sobHelper.delay(1000);
    }

    @Then("I Hit Size Datatable Button and Select {string}")
    public void iHitSizeDatatableButtonAndSelect(String arg0) {
        sobHelper.delay(500);
        prodListPage.selectSizeData(arg0);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Product List");
        sobHelper.delay(500);
    }
}
