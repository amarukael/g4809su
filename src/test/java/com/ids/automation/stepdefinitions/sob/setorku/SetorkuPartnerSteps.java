package com.ids.automation.stepdefinitions.sob.setorku;

import static org.junit.Assert.*;

import org.openqa.selenium.WebDriver;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBGlobalSteps;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;

import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.sob.SOBGlobalPages;
import pages.sob.SOBHomePages;
import pages.sob.setorku.SOBSetorkuPartnerPages;
import utility.Helper;

public class SetorkuPartnerSteps {
    static WebDriver driver;
    SOBHomePages homePage;
    SOBGlobalSteps globalSteps;
    SOBGlobalPages globalPages;
    SOBHelper sobHelper = new SOBHelper();
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;
    Integer countImage = 1;
    SOBSetorkuPartnerPages setorkuPages;

    public void setUpPartner() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        globalPages = new SOBGlobalPages(driver);
        setorkuPages = new SOBSetorkuPartnerPages(driver);
    }

    @Then("I click field {string} and fill with {string} on Setorku Partner")
    public void i_click_field_and_fill_with_on_setorku_partner(String arg0, String arg1) {
        setUpPartner();
        String[] value = arg1.split(",");
        sobHelper.delay(500);
        if (arg0.equalsIgnoreCase("Status")) {
            if (!arg1.isEmpty()) {
                setorkuPages.fieldStatus();
                sobHelper.delay(300);
                setorkuPages.listStatus(arg1);
            }
        } else {
            if (value[0].equalsIgnoreCase("random")) {
                globalPages.inputText(arg0, Helper.generateRandomString(Integer.parseInt(value[1])));
            } else if (value[0].equalsIgnoreCase("randomCase")) {
                globalPages.inputText(arg0, Helper.randomCase(value[1]));
            } else if (value[0].equalsIgnoreCase("number")) {
                globalPages.inputText(arg0, Helper.randomString2(Integer.parseInt(value[1])));
            } else if (value[0].equals("space")) {
                String spaces = " ".repeat(Integer.parseInt(value[1]));
                globalPages.inputText(arg0, spaces);
            } else {
                globalPages.inputText(arg0, value[0]);
            }
        }
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Field : " + arg0 + "\nValue : " + arg1);
        sobHelper.delay(500);
    }

    @When("I hit icon trash can on row {string}")
    public void I_hit_icon_trash_bin_on_row(String arg0) {
        setUpPartner();
        setorkuPages.trashCan(arg0);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Success delete row : " + arg0);
        sobHelper.delay(500);
    }

    @Then("SetorKu partner {string} snackbar success")
    public void setorku_partner_show_alert_success(String arg0) {
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

}
