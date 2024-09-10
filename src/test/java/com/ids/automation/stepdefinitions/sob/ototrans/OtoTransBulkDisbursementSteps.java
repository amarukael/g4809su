package com.ids.automation.stepdefinitions.sob.ototrans;

import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;
import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.sob.SOBHomePages;
import pages.sob.ototrans.SOBOtoTransBulkDisbursementPages;
import utility.Helper;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class OtoTransBulkDisbursementSteps {
    static WebDriver driver;
    SOBLoginSteps loginSteps = new SOBLoginSteps();
    SOBHomePages homePage;
    SOBHelper sobHelper = new SOBHelper();
    SOBOtoTransBulkDisbursementPages ototransBulkDisbursePage;
    Scenario scenario = SOBLoginSteps.scenario;
    private byte[] screenshotData;
    Integer countImage = 1;

    @Given("I am in OtoTrans Bulk Disbursement Page")
    public void i_am_in_oto_trans_bulk_disbursement_page() {
        loginSteps.setUp();
        loginSteps.setUpLogin("adminqa", "ototrans_bulk_transaction");
        setUpOtoTransBulkDisburse();
        homePage.MenuDrawer();
        homePage.openNavMenu("oToTrans", 0, 450, "smooth");
        homePage.openNavSubMenu("Bulk Disbursement");
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "OtoTrans - Bulk Disbursement");
        homePage.MenuDrawer();
        sobHelper.delay(1000);
    }

    @Given("I hit ADD DISBURSEMENT Button on OtoTrans Bulk Disbursement")
    public void i_hit_add_disbursement_button_on_ototrans_bulk_disbursement() {
        ototransBulkDisbursePage.hitAddDisbursement();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "OtoTrans - New Create Transaction Section");
        sobHelper.delay(800);
    }

    @Given("I fill Mandatory Fields \\({string}, {string}, {string}, {string}, {string}) On Create New Transaction Section")
    public void i_fill_mandatory_fields_on_create_new_transaction_section(String partnerId, String transNm
            , String transDesc, String uploadFormat, String typeAction) {
        ototransBulkDisbursePage.choosePartnerId(partnerId);
        sobHelper.delay(800);
        ototransBulkDisbursePage.fillTransNm(transNm);
        sobHelper.delay(800);
        ototransBulkDisbursePage.fillTransDesc(transDesc);
        sobHelper.delay(800);
        ototransBulkDisbursePage.chooseUploadFormat(uploadFormat);
        sobHelper.delay(800);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "OtoTrans - Fill Fields Create New Transaction Section");
        sobHelper.delay(800);
        homePage.scrollBody(350);
        String pathFile = "";
        if (typeAction.equals("approve")) {
            pathFile = "C:/Users/BOIDS 48/Desktop/Additional Data Testing Automation/SOB OtoTrans/IN-616_BulkDisburse.csv";
        } else {
            pathFile = "C:/Users/BOIDS 48/Desktop/Additional Data Testing Automation/SOB OtoTrans/IN-616_BulkDisburse_Fail.csv";
        }

        ototransBulkDisbursePage.addFile(pathFile);
        sobHelper.delay(800);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "OtoTrans - Fill Fields Create New Transaction Section " + countImage);
        countImage++;
        sobHelper.delay(800);
        homePage.scrollBody(530);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "OtoTrans - Fill Fields Create New Transaction Section " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Given("I hit Filter Button on OtoTrans Bulk Disbursement")
    public void i_hit_filter_button_on_oto_trans_bulk_disbursement() {
        ototransBulkDisbursePage.hitFilter();
        sobHelper.delay(800);
    }

    @Given("I fill Partner ID Field \\({string}) on Filter Field on OtoTrans Bulk Disbursement")
    public void i_fill_partner_id_field_on_filter_field_on_oto_trans_bulk_disbursement(String partnerId) {
        ototransBulkDisbursePage.choosePartnerId(partnerId);
        sobHelper.delay(800);
    }

    @Given("I hit Apply Button on OtoTrans Bulk Disbursement")
    public void i_hit_apply_button_on_oto_trans_bulk_disbursement() {
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "OtoTrans - Fill Fields Filter Section");
        ototransBulkDisbursePage.hitApply();
        sobHelper.delay(800);
    }

    @When("i hit SUBMIT Button")
    public void i_hit_submit_button() {
        homePage.scrollBody(-530);
        sobHelper.delay(800);
        ototransBulkDisbursePage.hitSubmit();
        sobHelper.delay(800);
    }

    @When("I hit Failed Button {string} on DataTable on OtoTrans Bulk Disbursement")
    public void i_hit_failed_button_on_data_table_on_oto_trans_bulk_disbursement(String transNm) {
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "OtoTrans - Data Bulk Transaction");
        sobHelper.delay(1000);
        String row = ototransBulkDisbursePage.getRowId(transNm);
        sobHelper.delay(800);
        if(Integer.parseInt(row) > 5) {
            homePage.scrollBody(300);
        }
        sobHelper.delay(800);
        ototransBulkDisbursePage.scrollDatatable();
        sobHelper.delay(800);
        ototransBulkDisbursePage.hitFailed(row);
        sobHelper.delay(1000);
    }

    @When("I hit Approve Button {string} on DataTable on OtoTrans Bulk Disbursement")
    public void i_hit_approve_button_on_data_table_on_oto_trans_bulk_disbursement(String transNm) {
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "OtoTrans - Data Bulk Transaction");
        sobHelper.delay(1000);
        String row = ototransBulkDisbursePage.getRowId(transNm);
        sobHelper.delay(800);
        if(Integer.parseInt(row) > 5) {
            homePage.scrollBody(300);
        }
        sobHelper.delay(800);
        ototransBulkDisbursePage.scrollDatatable();
        sobHelper.delay(800);
        ototransBulkDisbursePage.hitApprove(row);
        sobHelper.delay(1000);
    }

    @Then("I {string} navigate to OTOTrans Bulk Disbursement")
    public void i_navigate_to_oto_trans_bulk_disbursement(String condition) {
        setUpOtoTransBulkDisburse();
        homePage.MenuDrawer();
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Homepage");

        // condition check
        if (condition.equals("Successfully")) {
            homePage.openNavMenu("oToTrans", 0, 450, "smooth");
            sobHelper.delay(1500);
            homePage.openNavSubMenu("Bulk Disbursement");
            sobHelper.delay(1000);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "OtoTrans - Bulk Disbursement");
            homePage.MenuDrawer();
            sobHelper.delay(1000);
            assertEquals(ototransBulkDisbursePage.getLblBulkTransaction(), "Bulk Disbursement List");
        } else {
            assertTrue(homePage.checkNoNavMenu("oToTrans"));
        }

        sobHelper.delay(1000);
    }

    @Then("{string} create new transaction Bulk Disbursement")
    public void create_new_transaction_bulk_disbursement(String condition) {
        String ssName= "";
        assertTrue(ototransBulkDisbursePage.getSuccessAlert());
        ssName = "OtoTrans - Success Alert";
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", ssName);
        sobHelper.delay(1000);
    }

    @Then("I hit Confrim Button on Validation Section on OtoTrans Bulk Disbursement")
    public void i_hit_confrim_button_on_validation_section_on_oto_trans_bulk_disbursement() {
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "OtoTrans - Confrim Failed Bulk Disbursement");
        sobHelper.delay(800);
        ototransBulkDisbursePage.hitConfirm();
        sobHelper.delay(800);
    }

    @Then("{string} Action Fail Bulk Disbursement")
    public void action_fail_bulk_disbursement(String condition) {
        assertTrue(ototransBulkDisbursePage.getSuccessFailedAlert());
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "OtoTrans - Success Action Failed Alert");
        sobHelper.delay(1000);
    }

    @Then("I fill OTP on OtoTrans Bulk Disbursement")
    public void i_fill_otp_on_oto_trans_bulk_disbursement() {
        ototransBulkDisbursePage.hitEmail();
        System.out.println("Delay Get OTP");
        sobHelper.delay(40000);
    }

    @Then("I hit SUBMIT Button on Validation Section on OtoTrans Bulk Disbursement")
    public void i_hit_submit_button_on_validation_section_on_oto_trans_bulk_disbursement() {
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "OtoTrans - Confrim Approve Bulk Disbursement");
        sobHelper.delay(800);
        ototransBulkDisbursePage.hitSubmit();
        sobHelper.delay(800);
    }

    @Then("{string} Action Success Bulk Disbursement")
    public void action_success_bulk_disbursement(String string) {
        assertTrue(ototransBulkDisbursePage.getSuccessApproveAlert());
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "OtoTrans - Success Action Approve Alert");
        sobHelper.delay(1000);
    }

    public void setUpOtoTransBulkDisburse() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        ototransBulkDisbursePage = new SOBOtoTransBulkDisbursementPages(driver);
    }
}
