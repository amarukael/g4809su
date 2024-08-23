package com.ids.automation.stepdefinitions.sob.dana;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;
import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.sob.SOBGlobalPages;
import pages.sob.SOBHomePages;
import pages.sob.dana.SOBDanaTransactionSusPages;
import utility.Helper;

import static org.testng.Assert.*;

public class DanaTransactionSusSteps {
    static WebDriver driver;
    SOBLoginSteps loginSteps = new SOBLoginSteps();
    SOBHomePages homePage;
    SOBGlobalPages globalPages;
    SOBDanaTransactionSusPages transSusPage;
    SOBHelper sobHelper = new SOBHelper();
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;
    Integer countImage = 1;

    @Given("I am in Dana Transaction Suspect")
    public void i_am_in_Dana_transaction_suspect() {
        loginSteps.setUp();
        loginSteps.setUpLogin("adminstg", "Dana_trans_suspect");
        setUpTransSus();
        homePage.MenuDrawer();
        homePage.openNavMenu("DANA", 0, 250, "smooth");
        homePage.openNavSubMenu("Transaction Suspect");
        sobHelper.delay(800);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Dana - Transaction Suspect");
        homePage.MenuDrawer();
        sobHelper.delay(1000);
    }

    @Given("I on a Dana Transaction Suspect Page")
    public void i_on_a_Dana_transaction_suspect_page() {
        homePage.openNavSubMenu("Transaction Pending");
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Dana - Transaction Suspect");
        sobHelper.delay(500);
    }

    @Given("I fill From Date \\({string}) on Dana Transaction Suspect")
    public void i_fill_from_date_on_Dana_transaction_suspect(String sDate) {
        String[] value = sDate.split(",");
        transSusPage.fillFromDate(value);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Filter Date");
        sobHelper.delay(500);
    }
    @Given("I fill To Date \\({string}) on Dana Transaction Suspect")
    public void i_fill_to_date_on_Dana_transaction_suspect(String sDate) {
        String[] value = sDate.split(",");
        transSusPage.fillToDate(value);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Filter Date");
        sobHelper.delay(500);
    }

    @Given("I choose {string} and fill with {string} on Dana Transaction Suspect")
    public void i_choose_and_fill_with_on_dana_transaction_suspect(String field, String value) throws Exception {
        if (field.equals("Acquirement Id")) {
            transSusPage.fillAcqIdField(value);
        } else if (field.equals("Product Name")) {
            transSusPage.fillProdNameField(value);
        } else if (field.equals("Customer ID")) {
            transSusPage.fillCustomerIdField(value);
        } else if (field.equals("Ref")) {
            transSusPage.fillRefField(value);
        } else if (field.equals("Amount")) {
            transSusPage.fillAmnField(value);
        } else if (field.equals("Product ID")) {
            transSusPage.fillProdIdField(value);
        } else {
            throw new Exception("Field Not Found");
        }
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Filter " + field);
        sobHelper.delay(500);
//        TransSusPage.scrollNavFilter(50);
    }

    @Given("I hit Filter Button on Dana Transaction Suspect")
    public void i_hit_filter_button_on_Dana_transaction_suspect() {
        transSusPage.hitBtnFilter();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Dana - Transaction Suspect "
                + countImage);
        countImage++;
        sobHelper.delay(100);
    }

    @When("I hit Apply Button on Dana Transaction Suspect")
    public void i_hit_apply_button_on_Dana_transaction_suspect() {
        transSusPage.hitBtnApply();
        sobHelper.delay(500);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Dana - Transaction Suspect "
                + countImage);
        countImage++;
    }

    @When("I hit Button {string} with receipt no {string} on Dana Transaction Suspect")
    public void i_hit_button_with_receipt_no_on_digital_goods_transaction_suspect(String button
            , String receiptNo) {
        homePage.scrollBody(400);
        globalPages.scrollDataTabletoLeft(1200);
        if (button.equals("Success"))   {
            transSusPage.hitBtnSuccess();
            sobHelper.delay(1000);
            transSusPage.fillReceiptNo(receiptNo);
            transSusPage.hitBtnEdit();
//            transSusPage.hitBtnConfirm();
        } else if (button.equals("Failed")) {
            transSusPage.hitBtnFailed();
            sobHelper.delay(1000);
//            transSusPage.hitBtnConfirm();
        }
        sobHelper.delay(1000);
    }

    @Then("I {string} navigate to Dana Transaction Suspect")
    public void i_navigate_to_Dana_transaction_suspect(String condition) {
        setUpTransSus();
        homePage.MenuDrawer();
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Homepage");
        homePage.openNavMenu("DANA", 0, 250, "smooth");
        sobHelper.delay(1000);
        homePage.openNavSubMenu("Transaction Suspect");
        sobHelper.delay(800);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Dana - Transaction Suspect");
        homePage.MenuDrawer();
        sobHelper.delay(1000);
    }

    @Then("Dana Datatable show data Transaction Suspect")
    public void dana_datatable_show_data_transaction_suspect() {
        homePage.scrollBody(400);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Dana - Transaction Suspect "
                + countImage);
        countImage++;

        assertTrue(transSusPage.getDataTable());
        assertEquals(transSusPage.getRowsDataTable(), transSusPage.getRowsPerPage());
        sobHelper.delay(1000);
    }

    @When("I choose {string} and fill with values \\({string}, {string}) on Dana Transaction Suspect")
    public void i_choose_and_fill_with_values_on_digital_goods_transaction_suspect(String fields
            , String sDate, String ref) {
        String[] value = sDate.split(",");
        transSusPage.fillFromDate(value);
        transSusPage.fillRefField(ref);
        transSusPage.hitBtnApply();
        sobHelper.delay(3000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Dana - Transaction Suspect "
                + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Then("Dana Display value in Column Status and Action in data Transaction Suspect")
    public void dana_display_value_in_column_status_and_action_in_data_transaction_suspect() {
        globalPages.scrollDataTabletoLeft(1400);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Dana - Transaction Suspect "
                + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Then("I Hit Button Force {string} and Fill {string} with Value {string}")
    public void i_hit_button_force_success_and_fill_with_value(String buttonAction,String unUsed,String receiptCode) {
        globalPages.scrollDataTabletoLeft(1400);
        sobHelper.delay(500);
        if (buttonAction.equals("APPROVE")){
            transSusPage.hitBtnSuccess();
            transSusPage.fillReceiptCodeAction(receiptCode);
        } else if (buttonAction.equals("REJECT")) {
            transSusPage.hitBtnFailed();
            transSusPage.fillRejectReasonAction(receiptCode);
        }
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Dana - Transaction Suspect "
                + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Then("I Success change Final Status to {string} on Dana Transaction Suspect")
    public void i_success_change_final_status_to_on_digital_goods_transaction_suspect(String testCase) {
        assertTrue(transSusPage.getSuccessAlert());
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Dana - " + testCase + " Alert");
    }

    public void setUpTransSus() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        transSusPage = new SOBDanaTransactionSusPages(driver);
    }

    @Then("I Hit Edit Button and Show Alert")
    public void iHitEditButtonAndShowAlert() {
        transSusPage.hitBtnEdit();
        transSusPage.getSuccessAlert();
        scenario.attach(screenshotData, "image/png", "Dana - Transaction Suspect "
                + countImage);
        countImage++;
        sobHelper.delay(800);
    }

    @Then("Show Info {string} Date")
    public void showInfoSpanDate(String type) {
        try {
            transSusPage.getSpanInfoDate(type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        scenario.attach(screenshotData, "image/png", "Dana - Transaction Suspect "
                + countImage);
        countImage++;
        sobHelper.delay(500);
    }

    @Given("I fill From Date \\({string}) and To Date \\({string}) on Dana Transaction Suspect")
    public void I_fill_From_Date_and_To_Date_on_Dana_Transaction_Suspect(String fromdate, String todate){
        i_fill_from_date_on_Dana_transaction_suspect(fromdate);
        i_fill_to_date_on_Dana_transaction_suspect(todate);
    }

    @Then("I Hit Header {string} Datatable to Sort {string}")
    public void iHitHeaderDatatableToSort(String arg0, String arg1) throws Exception {
        int scrollAmount = 0;
        switch (arg0) {
            case "Customer ID" -> scrollAmount = 300;
            case "Product Name" -> scrollAmount = 500;
            case "Amount" -> scrollAmount = 700;
            case "RC" -> scrollAmount = 900;
            case "RC Desc" -> scrollAmount = 1100;
            case "No", "Settle Date", "Tracking Reff", "Acquirement Id", "Product ID" -> {}
            default -> throw new Exception("Header not listed");
        }

        if (scrollAmount > 0) {
            globalPages.scrollDataTabletoLeft(scrollAmount);
        }

        if (arg1.equals("ASC & DSC")) {
            transSusPage.headerDatatable(arg0, "ASC");
            sobHelper.delay(1000);
            takeAndAttachScreenshot();
            transSusPage.headerDatatable(arg0, "ASC");
        } else {
            transSusPage.headerDatatable(arg0, arg1);
        }
        takeAndAttachScreenshot();
    }

    private void takeAndAttachScreenshot() {
        byte[] screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Dana - Transaction Suspect " + countImage);
        countImage++;
        sobHelper.delay(500);
    }

}
