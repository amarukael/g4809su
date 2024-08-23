package com.ids.automation.stepdefinitions.sob.solusipayweb;

import com.ids.automation.configuration.BrowserSetup;
import helper.SOBHelper;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.sob.SOBHomePages;
import pages.sob.solusipayweb.SOBSolusipayWebFAQPages;
import utility.Helper;

//import static com.ids.automation.stepDefinitions.sob.SOB_LoginSteps.document;

public class SolusipayWebFAQSteps {

    static WebDriver driver;
    SOBHelper sobHelper = new SOBHelper();
    SOBHomePages homePage;
    SOBSolusipayWebFAQPages FAQPage;
    
    @When("I navigate to SolusipayWeb FAQ")
    public void i_navigate_to_solusipay_web_faq() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        FAQPage = new SOBSolusipayWebFAQPages(driver);
        homePage.MenuDrawer();
        sobHelper.delay(1000);
//        document.open();
//        document.newPage(); // Add a new page for the rest of the content
        byte[] screenshotData = Helper.takeScreenshot(driver);
//        Helper.addImageToPDF(document, screenshotData);
        homePage.openNavMenu("Solusipay Web", 0, 250, "smooth");
        sobHelper.delay(1000);
//        document.newPage(); // Add a new page for the rest of the content
        byte[] screenshotData1 = Helper.takeScreenshot(driver);
//        Helper.addImageToPDF(document, screenshotData1);
        homePage.openNavSubMenu("FAQ");
        sobHelper.delay(800);
        homePage.MenuDrawer();
        sobHelper.delay(1000);
    }

    @Given("I on a SolusipayWeb FAQ Page")
    public void i_on_a_solusipay_web_faq_page() {
        FAQPage.getFAQPages();
        sobHelper.delay(1000);
//        document.newPage(); // Add a new page for the rest of the content
        byte[] screenshotData = Helper.takeScreenshot(driver);
//        Helper.addImageToPDF(document, screenshotData);
        sobHelper.delay(500);
    }

    @Then("I on a SolusipayWeb FAQ Page two")
    public void i_on_a_solusipay_web_faq_page_two() {
        System.out.println("Im on FAQ Page 2");
    }

    @Then("I can see FAQ SolusipayWeb data is show")
    public void i_can_see_faq_solusipay_web_data_is_show() {
        System.out.println("Im on FAQ data show");
    }

    @Then("I can see all data FAQ SolusipayWeb is show")
    public void i_can_see_all_data_faq_solusipay_web_is_show() {
        System.out.println("Im on FAQ All data show");
    }

    @When("I choose partner FAQ dropdown")
    public void i_choose_partner_faq_dropdown() {
        System.out.println("Im on choose partner FAQ");
    }

    @When("I write some content on FAQ")
    public void i_write_some_content_on_faq() {
        System.out.println("Im on write content FAQ");
    }

    @Then("I can see warning on Partner Field")
    public void i_can_see_warning_on_partner_field() {
        System.out.println("Im on warning partner ID");
    }

    @Then("I switch status on first data FAQ")
    public void i_switch_status_on_first_data_faq() {
        System.out.println("Im on first data FAQ");
    }

    @When("I edit some content on FAQ")
    public void i_edit_some_content_on_faq() {
        System.out.println("Im on edit data FAQ");
    }

    @When("I change the status")
    public void i_change_the_status() {
        System.out.println("Im on change status FAQ");
    }

    @When("I want change partner but the field is inactive")
    public void i_want_change_partner_but_the_field_is_inactive() {
        System.out.println("Im on change partner");
    }

    @Then("I click trash icon for delete on nineth data FAQ")
    public void i_click_trash_icon_for_delete_on_nineth_data_faq() {
        System.out.println("Im on delete faq");
    }
}
