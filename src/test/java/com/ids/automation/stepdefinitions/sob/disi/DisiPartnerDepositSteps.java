package com.ids.automation.stepdefinitions.sob.disi;

import com.google.gson.Gson;
import com.ids.automation.configuration.BrowserSetup;
import com.ids.automation.stepdefinitions.sob.SOBLoginSteps;
import helper.SOBHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.disi.PartnerData;
import model.disi.PartnerDetail;
import org.openqa.selenium.WebDriver;
import pages.sob.SOBHomePages;
import pages.sob.disi.SOBDisiPartnerDepositPages;
import utility.Helper;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DisiPartnerDepositSteps {
    static WebDriver driver;
    Gson gson = new Gson();
    SOBLoginSteps loginSteps = new SOBLoginSteps();
    SOBHomePages homePage;
    SOBHelper sobHelper = new SOBHelper();
    SOBDisiPartnerDepositPages disiPartnerDepositPage;
    Scenario scenario = SOBLoginSteps.scenario;
    PartnerData pd = new PartnerData();
    private byte[] screenshotData;
    private String tmpInvalidParam;
    private String tmpInvalidDesc;
    Integer countImage = 1;

    @Given("I am in DISI Partner Deposit Page")
    public void i_am_in_disi_partner_deposit_page() {
        loginSteps.setUp();
        loginSteps.setUpLogin("pettycashpartner", "disi_partner_deposit");
        setUpDisiPartnerDeposit();
        homePage.MenuDrawer();
        homePage.openNavMenu("Deposit Sistem", 0, 450, "smooth");
        homePage.openNavSubMenu("Partner Deposit");
        sobHelper.delay(3000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - Partner Deposit");
        homePage.MenuDrawer();

        sobHelper.delay(2000);
        // insert before data pettycash
        String availableBalance = disiPartnerDepositPage.getAvailableBalance();
        String accountsReceivable = disiPartnerDepositPage.getAccountsReceivable();
        String balance = disiPartnerDepositPage.getBalance();
        String pendingBalance = disiPartnerDepositPage.getPendingBalance();
        PartnerDetail pDetail = new PartnerDetail(availableBalance, accountsReceivable, balance, pendingBalance);
        pd.setBeforeAction(pDetail);

        sobHelper.delay(1000);
    }

    @Given("I hit Filter Button on DISI Partner Deposit")
    public void i_hit_filter_button_on_disi_partner_deposit() {
        disiPartnerDepositPage.hitBtnFilter();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - Partner Deposit " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @When("I fill From Date \\({string}, {string}, {string}) on Filter Field on DISI Partner Deposit")
    public void i_fill_from_date_on_filter_field_on_disi_partner_deposit(String condition, String fromDate
            , String toDate) {
        String[] valueDate = fromDate.split(",", -1);
        if (valueDate[1].equals("12")) {
            disiPartnerDepositPage.fillFromDateWithYear(valueDate);
        } else {
            disiPartnerDepositPage.fillFromDate(valueDate);
        }

        if (!toDate.isEmpty()) {
            String[] valueToDate = toDate.split(",", -1);
            disiPartnerDepositPage.fillToDate(valueToDate);
        }

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - Partner Deposit " + countImage);
        countImage++;
        sobHelper.delay(1000);
        // Get value from date and to date
        if (!condition.isEmpty())
            assertTrue(disiPartnerDepositPage.checkValueFromToDate());
    }

    @When("I fill List Box Transaction Type \\({string}) on Filter Field on DISI Partner Deposit")
    public void i_fill_list_box_transaction_type_on_filter_field_on_disi_partner_deposit(String transType) {
        disiPartnerDepositPage.fillTransactionType(transType);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - Partner Deposit " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @When("I fill List Box Status \\({string}) on Filter Field on DISI Partner Deposit")
    public void iFillListBoxStatusOnFilterFieldOnDISIPartnerDeposit(String status) {
        disiPartnerDepositPage.hitListBoxStatus();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - Partner Deposit " + countImage);
        countImage++;
        disiPartnerDepositPage.fillStatus(status);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - Partner Deposit " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @When("I choose and fill fields \\({string}) in Withdraw Section")
    public void i_choose_and_fill_fields_in_withdraw_section(String amount) {
        pd.setAmount(amount);

        disiPartnerDepositPage.hitBtnWithdraw();
        sobHelper.delay(1000);
        disiPartnerDepositPage.fillAmountWithdraw(amount);
        disiPartnerDepositPage.fillBankName();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - Withdraw");
        disiPartnerDepositPage.hitBtnSubmitWithdraw();
        disiPartnerDepositPage.hitBtnConfirm();
        sobHelper.delay(35000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - Withdraw 2");
        disiPartnerDepositPage.hitBtnVerifyOTP();
    }

    @When("I choose and fill fields in Withdraw Section with invalid {string} {string}")
    public void i_choose_and_fill_fields_in_withdraw_section_with_invalid(String param, String desc) {
        tmpInvalidParam = param;
        tmpInvalidDesc = desc;
        if (param.equals("otp")) {
            System.out.println("Start Delay Failed Case...");
            sobHelper.delay(35000);
            System.out.println("End Delay...");
        }

        String amount = "10000";
        if (param.equals("amount") && desc.equals("Invalid")) {
            amount = "1000";
        } else if (param.equals("balance") && desc.equals("Insufficient")) {
            Integer amountBalanceInssuff = Integer.parseInt(pd.getBeforeAction().getAvailableBalance()) + 100000;
            amount = String.valueOf(amountBalanceInssuff);
        }

        disiPartnerDepositPage.hitBtnWithdraw();
        sobHelper.delay(1000);
        disiPartnerDepositPage.fillAmountWithdraw(amount);
        disiPartnerDepositPage.fillBankName();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - Withdraw");
        disiPartnerDepositPage.hitBtnSubmitWithdraw();

        String paramDesc = param + "_" + desc;
        if (!paramDesc.equals("amount_Invalid"))  {
            disiPartnerDepositPage.hitBtnConfirm();
            sobHelper.delay(35000);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Disi - Withdraw 2");
            disiPartnerDepositPage.hitBtnVerifyOTP();
        }
    }

    @Then("I {string} navigate to DISI Partner Deposit")
    public void i_navigate_to_disi_partner_deposit(String condition) {
        setUpDisiPartnerDeposit();
        homePage.MenuDrawer();
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Homepage");

        // condition check
        if (condition.equals("Successfully")) {
            homePage.openNavMenu("Deposit Sistem", 0, 450, "smooth");
            sobHelper.delay(1500);
            homePage.openNavSubMenu("Partner Deposit");
            sobHelper.delay(3000);
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Disi - Partner Deposit");
            homePage.MenuDrawer();
            sobHelper.delay(2000);
            assertEquals(disiPartnerDepositPage.getLblPartnerDepo(), "Partner Deposit");
        } else {
            assertTrue(homePage.checkNoNavMenu("Deposit Sistem"));
        }

        sobHelper.delay(1000);
    }

    @Then("I hit Apply Button on DISI Partner Deposit")
    public void i_hit_apply_button_on_disi_partner_deposit() {
        disiPartnerDepositPage.hitBtnApply();
        sobHelper.delay(2000);
        homePage.scrollBody(300);
        disiPartnerDepositPage.scrollDatatable();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - Partner Deposit " + countImage);
        countImage++;
        sobHelper.delay(1000);
        homePage.scrollBody(350);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - Partner Deposit " + countImage);
        countImage++;
        sobHelper.delay(1000);
    }

    @Then("I hit Export Button on DISI Partner Deposit")
    public void i_hit_export_button_on_disi_partner_deposit() {
//        homePage.scrollBody(350);
//        screenshotData = Helper.takeScreenshot(driver);
//        scenario.attach(screenshotData, "image/png", "Disi - Partner Deposit " + countImage);
//        countImage++;
//        sobHelper.delay(800);
        homePage.scrollBody(-350);
        disiPartnerDepositPage.hitBtnExport();
        sobHelper.delay(1000);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Disi - Partner Deposit " + countImage);
        countImage++;
        sobHelper.delay(3000);
        assertEquals(Helper.check_file_exist("partner-deposit-real.xlsx"), "File Present");
    }

    @Then("I {string} Withdraw into registered Bank Account")
    public void i_withdraw_into_registered_bank_account(String condition) {
        Integer flgData = 0;
        if (condition.equals("Successfully")) {
            assertTrue(disiPartnerDepositPage.getSuccessAlert());
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Disi - Success Alert");

            flgData = 1;
        } else {
            assertTrue(disiPartnerDepositPage.getFailedAlert(tmpInvalidParam + "_" + tmpInvalidDesc));
            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Disi - Failed Alert");

            if (!tmpInvalidParam.equals("amount") && !tmpInvalidParam.equals("Invalid")) {
                flgData = 1;
            }
        }

        if (flgData == 1) {
            sobHelper.delay(4000);
            // insert after data pettycash
            String availableBalance = disiPartnerDepositPage.getAvailableBalance();
            String accountsReceivable = disiPartnerDepositPage.getAccountsReceivable();
            String balance = disiPartnerDepositPage.getBalance();
            String pendingBalance = disiPartnerDepositPage.getPendingBalance();
            PartnerDetail pDetail = new PartnerDetail(availableBalance, accountsReceivable, balance, pendingBalance);
            pd.setAfterAction(pDetail);
            scenario.log("Detail Data Partner Deposit: " + gson.toJson(pd));
        }
    }

    public void setUpDisiPartnerDeposit() {
        driver = BrowserSetup.getDriver();
        homePage = new SOBHomePages(driver);
        disiPartnerDepositPage = new SOBDisiPartnerDepositPages(driver);
    }
}
