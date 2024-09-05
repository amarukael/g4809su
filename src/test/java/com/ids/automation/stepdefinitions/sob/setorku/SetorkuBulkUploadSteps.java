package com.ids.automation.stepdefinitions.sob.setorku;

import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBGlobalSteps;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;

import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import pages.sob.SOBGlobalPages;
import pages.sob.SOBHomePages;
import pages.sob.setorku.SOBSetorkuBulkUploadPages;
import utility.Helper;

public class SetorkuBulkUploadSteps {
    static WebDriver driver;
    SOBHomePages homePage;
    SOBGlobalSteps globalSteps;
    SOBGlobalPages globalPages;
    SOBSetorkuBulkUploadPages bulkUploadPages;
    SOBHelper sobHelper = new SOBHelper();
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;
    Integer countImage = 1;

    public void setUpBulkUpload() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        globalPages = new SOBGlobalPages(driver);
        bulkUploadPages = new SOBSetorkuBulkUploadPages(driver);
    }

    @Then("I click field {string} and fill with {string} on Setorku Bulk Upload")
    public void i_click_field_and_fill_with_on_setorku_bulkUpload(String arg0, String arg1) {
        setUpBulkUpload();
        String[] value = arg1.split(",");
        sobHelper.delay(500);
        if (arg0.equalsIgnoreCase("Status")) {
            if (!arg1.isEmpty()) {
                globalPages.dropList(arg0, arg1);
            }

        } else if (!arg1.isEmpty()) {
            sobHelper.delay(500);
            if (value[0].equalsIgnoreCase("random")) {
                arg1 = globalPages.inputText(arg0, Helper.generateRandomString(Integer.parseInt(value[1])));
            } else if (value[0].equalsIgnoreCase("randomUser")) {
                arg1 = globalPages.inputText(arg0, sobHelper.generateName());
            } else if (value[0].equalsIgnoreCase("randomCase")) {
                arg1 = globalPages.inputText(arg0, Helper.randomCase(value[1]));
            } else if (value[0].equalsIgnoreCase("number")) {
                arg1 = globalPages.inputText(arg0, Helper.randomString2(Integer.parseInt(value[1])));
            } else if (value[0].equals("space")) {
                String spaces = " ".repeat(Integer.parseInt(value[1]));
                arg1 = globalPages.inputText(arg0, spaces);
            } else if (value[0].equalsIgnoreCase("randomString")) {
                arg1 = globalPages.inputText(arg0, Helper.randomAlphanumeric(Integer.parseInt(value[1])));
            } else if (value[0].equals("timestamp")) {
                long time = System.currentTimeMillis();
                String timestamp = time + "";
                if (!value[1].isEmpty()) {
                    timestamp = timestamp + Helper.randomString2(Integer.parseInt(value[1]));
                }
                arg1 = globalPages.inputText(arg0, timestamp);
            } else {
                arg1 = globalPages.inputText(arg0, value[0]);
            }
        }

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Field : " + arg0 + "\nValue : " + arg1);
        sobHelper.delay(500);
    }

    @Then("SetorKu bulkUpload {string} snackbar success")
    public void setorku_bulkUpload_show_alert_success(String arg0) {
        if (arg0.equalsIgnoreCase("not show")) {
            System.out.println("Skip Steps");
        } else {
            sobHelper.delay(1000);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Snackbar");
            assertTrue("Snack bar Success", globalPages.successAlert());
            assertFalse("Snack bar Error", globalPages.errorAlert());
            sobHelper.delay(200);
        }
    }

    @Then("I click add Bulk Upload button on SetorKu Bulk Upload")
    public void i_click_add_bulk_upload_on_setorku_bulk_upload() {
        setUpBulkUpload();
        sobHelper.delay(300);
        bulkUploadPages.addBulk();
        scenario.log("Click Button Add Bulk");
        sobHelper.delay(300);
    }

    @Then("I click detail on row {string} Setorku Bulk Upload")
    public void i_click_detail_on_row_string_setorku_bulk_upload(String s) {
        setUpBulkUpload();
        sobHelper.delay(300);
        bulkUploadPages.getValueCell(s);
        bulkUploadPages.detailBulk(s);
        String value = bulkUploadPages.validateValue(s);
        scenario.log("Result " + value);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", value);
        sobHelper.delay(500);
    }

    @Then("I click add data button on Setorku Detail Bulk")
    public void i_click_add_data_button_on_setorku_detail_bulk() {
        driver.findElement(By.id("button-add")).click();
        scenario.log("Hit Add Data");
        sobHelper.delay(300);
    }

    @Then("I click {string} button on row {string} Setorku Bulk Upload")
    public void i_click_string_button_on_row_string_setorku_bulk_upload(String s, String s1) {
        setUpBulkUpload();
        sobHelper.delay(300);
        bulkUploadPages.actionBulk(s, s1);
        sobHelper.delay(300);
    }

    @Then("I validate payment code show on Setorku Bulk Upload")
    public void i_validate_payment_code_show_on_setorku_bulk_upload() {
        sobHelper.delay(300);
        String count = bulkUploadPages.validPayCode();
        scenario.log(count);
        sobHelper.delay(300);
    }

    @Then("back page")
    public void back_page() {
        driver.navigate().back();
    }
}
