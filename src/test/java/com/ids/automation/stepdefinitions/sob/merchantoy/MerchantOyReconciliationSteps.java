package com.ids.automation.stepdefinitions.sob.merchantoy;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;
import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.sob.SOBHomePages;
import pages.sob.merchantoy.SOBMerchantOyReconciliationPages;
import utility.Helper;

public class MerchantOyReconciliationSteps {
    static WebDriver driver;
    SOBHomePages homePage;
    SOBMerchantOyReconciliationPages merchantOyReconciliationPages;
    SOBHelper sobHelper = new SOBHelper();
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;
    Integer countImage = 1;

    public void setUpTransaction() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        merchantOyReconciliationPages = new SOBMerchantOyReconciliationPages(driver);
    }

    @Then("I click Field {string} and Fill With {string} on Merchant OY Reconciliation")
    public void i_click_field_and_fill_with_on_merchant_oy_reconciliation(String arg0, String arg1) {
        setUpTransaction();
        String[] value = arg1.split(",");
        sobHelper.delay(500);
        if (value[0].equalsIgnoreCase("random")){
            merchantOyReconciliationPages.fieldFilter(arg0, Helper.generateRandomString(Integer.parseInt(value[1])));
        }else {
            merchantOyReconciliationPages.fieldFilter(arg0,value[0]);
        }
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Merchant OY - Reconciliation Field " + arg0);
        sobHelper.delay(500);
    }

    @Then("I fill all fields on filter Merchant OY Reconciliation")
    public void i_fill_all_fields_on_filter_merchant_oy_reconciliation() {
        setUpTransaction();
        String[] field = {"Ref","Transactions ID", "Product ID","Product Description", "Payment Code List",
                "Receipt No", "Through"};
        String[] data = {"249486691455", "OYZ336B1U5G5W8G5FL38", "37","FAZNET","8253369412120111",
                "IDM1105627430","indomaret"};

        int numField = field.length;

        for (int i = 0; i < numField; i++) {
            sobHelper.delay(500);
            merchantOyReconciliationPages.fieldFilter(field[i], data[i]);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Merchant OY - Reconciliation"
                    + countImage);
            countImage++;
            sobHelper.delay(500);
        }
    }

    @Then("I fill all fields on filter Merchant OY Reconciliation with random value field {string}")
    public void i_fill_all_fields_on_filter_merchant_oy_reconciliation_with_random_value_field(String arg0) {
        setUpTransaction();
        String[] field = {"Ref","Transactions ID", "Product ID","Product Description", "Payment Code List",
                "Receipt No", "Through"};
        String[] data = {"249486691455", "OYZ336B1U5G5W8G5FL38", "37","FAZNET","8253369412120111",
                "IDM1105627430","indomaret"};

        int numField = field.length;

        for (int i = 0; i < numField; i++) {
            sobHelper.delay(500);
            if (arg0.equalsIgnoreCase(field[i])){
                merchantOyReconciliationPages.fieldFilter(field[i], Helper.generateRandomString(data[i].length()));
            }else {
                merchantOyReconciliationPages.fieldFilter(field[i], data[i]);
            }
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Merchant OY - Reconciliation"
                    + countImage);
            countImage++;
            sobHelper.delay(500);
        }
    }
}
