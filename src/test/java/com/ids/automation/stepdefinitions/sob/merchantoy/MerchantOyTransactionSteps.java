package com.ids.automation.stepdefinitions.sob.merchantoy;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;
import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.sob.SOBHomePages;
import pages.sob.merchantoy.SOBMerchantOyTransactionPages;
import utility.Helper;

public class MerchantOyTransactionSteps {
    static WebDriver driver;
    SOBHomePages homePage;
    SOBMerchantOyTransactionPages merchantOyTransactionPages;
    SOBHelper sobHelper = new SOBHelper();
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;
    Integer countImage = 1;

    public void setUpTransaction() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        merchantOyTransactionPages = new SOBMerchantOyTransactionPages(driver);
    }

    @Then("I click Field {string} and Fill With {string} on Merchant OY Transaction")
    public void i_click_field_and_fill_with_on_merchant_oy_transaction(String arg0, String arg1) {
        setUpTransaction();
        String[] value = arg1.split(",");
        sobHelper.delay(500);
        if (value[0].equalsIgnoreCase("random")){
            merchantOyTransactionPages.fieldFilter(arg0, Helper.generateRandomString(Integer.parseInt(value[1])));
        }else {
            merchantOyTransactionPages.fieldFilter(arg0,value[0]);
        }
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Merchant OY - Transaction Field " + arg0);
        sobHelper.delay(500);
    }

    @Then("I fill all fields on filter Merchant OY Transaction")
    public void i_fill_all_fields_on_filter_merchant_oy_transaction() {
        setUpTransaction();
        String[] field = {"Transactions ID", "Product ID", "Payment Code",
                "Partner Name", "Status"};
        String[] data = {"OYYAGWJKWIBTXO2CVEC0", "37", "0205622594910438",
                "OY","Success"};

        int numField = field.length;

        for (int i = 0; i < numField; i++) {
            sobHelper.delay(500);
            merchantOyTransactionPages.fieldFilter(field[i], data[i]);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Merchant OY - Transaction"
                    + countImage);
            countImage++;
            sobHelper.delay(500);
        }
    }

    @Then("I fill all fields on filter Merchant OY Transaction with random value field {string}")
    public void i_fill_all_fields_on_filter_merchant_oy_transaction_with_random_value_field(String arg0) {
        setUpTransaction();
        String[] field = {"Transactions ID", "Product ID", "Payment Code",
                "Partner Name", "Status"};
        String[] data = {"OYYAGWJKWIBTXO2CVEC0", "37", "0205622594910438",
                "OY","Success"};

        int numField = field.length;

        for (int i = 0; i < numField; i++) {
            sobHelper.delay(500);
            if (arg0.equalsIgnoreCase(field[i])){
                merchantOyTransactionPages.fieldFilter(field[i], Helper.generateRandomString(data[i].length()));
            }else {
                merchantOyTransactionPages.fieldFilter(field[i], data[i]);
            }
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Merchant OY - Transaction"
                    + countImage);
            countImage++;
            sobHelper.delay(500);
        }
    }
}
