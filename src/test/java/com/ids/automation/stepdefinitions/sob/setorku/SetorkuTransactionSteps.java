package com.ids.automation.stepdefinitions.sob.setorku;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;
import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.sob.SOBGlobalPages;
import pages.sob.SOBHomePages;
import pages.sob.setorku.SOBSetorkuTransactionPages;
import utility.Helper;


public class SetorkuTransactionSteps {
    static WebDriver driver;
    SOBHomePages homePage;
    SOBGlobalPages globalPages;
    SOBHelper sobHelper = new SOBHelper();
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;
    Integer countImage = 1;
    SOBSetorkuTransactionPages sobSetorkuTransactionPages;

    public void setUpTransaction() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        globalPages = new SOBGlobalPages(driver);
        sobSetorkuTransactionPages = new SOBSetorkuTransactionPages(driver);
    }

    @Then("I click field {string} and fill with {string} on Setorku Transaction")
    public void i_click_field_and_fill_with_on_setorku_transaction(String arg0, String arg1) {
        setUpTransaction();
        String[] value = arg1.split(",");
        sobHelper.delay(500);
        if (arg0.equalsIgnoreCase("Product ID")) {
            sobSetorkuTransactionPages.fieldProductId();
            sobHelper.delay(300);
            sobSetorkuTransactionPages.listProductId(value[0]);
        } else if (arg0.equalsIgnoreCase("Status")) {
            sobSetorkuTransactionPages.fieldStatus();
            sobHelper.delay(300);
            sobSetorkuTransactionPages.listStatus(value[0]);
        }else {
            if (value[0].equalsIgnoreCase("random")) {
                globalPages.inputText(arg0, Helper.generateRandomString(Integer.parseInt(value[1])));
            } else if (value[0].equalsIgnoreCase("number")) {
                globalPages.inputText(arg0, Helper.randomString2(Integer.parseInt(value[1])));
            } else {
                globalPages.inputText(arg0, value[0]);
            }
        }
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Field : " + arg0+"\nValue : "+arg1);
        sobHelper.delay(500);
    }
}
