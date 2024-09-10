package com.ids.automation.testrunner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.service.ExtentService;
import constant.SOBConstant;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.testng.annotations.AfterSuite;
import utility.ExtentReport;

import java.io.File;
import java.util.Arrays;

@RunWith(Cucumber.class)
@CucumberOptions(
        // login
//        features="src/test/resources/features/sob/SOB_Login.feature",
        // disi
//        features="src/test/resources/features/sob/disi",
//        features="src/test/resources/features/sob/disi/Disi_PartnerDeposit.feature",
//        features="src/test/resources/features/sob/disi/Disi_UserDeposit.feature",
//        features="src/test/resources/features/sob/disi/Disi_PettyCash.feature",
//        features="src/test/resources/features/sob/disi/Disi_ListWithdraw.feature",
        // digital goods
//        features="src/test/resources/features/sob/digitalGoods/DigitalGoods_TransactionSus.feature",
        // m&m
//        features="src/test/resources/features/sob/mnm/MnM_SendMessage.feature",
//        features="src/test/resources/features/sob/mnm/MnM_Transaction.feature",
//        features="src/test/resources/features/sob/solusipayWeb/SolusipayWeb_Transaction.feature",
//        features="src/test/resources/features/sob/solusipayWeb/SolusipayWeb_PartnerSetting.feature",
//        features="src/test/resources/features/sob/solusipayWeb/SolusipayWeb_Biller.feature",
        // ototrans
        features="src/test/resources/features/sob/ototrans/OtoTransBulkDisbursement.feature",
        glue= {"com.ids.automation.stepDefinitions.sob"},
        // tag login
//        tags = "@login",
//        tags= "@login_same_user or @session_end",
        // tag disi
//        tags = "not @navigate_disi_partner_deposit_page",
//        tags = "@disi_partner_deposit_filter_date",
//        tags = "@disi_partner_withdraw",
//        tags = "@disi_partner_withdraw_failed",
//        tags = "@disi_list_withdraw_export_list_withdraw",
//        tags = "@disi_list_withdraw_filter_data",
//        tags = "@disi_partner_withdraw or @disi_partner_withdraw_failed " +
//                "or @disi_partner_deposit_export_history_transaction",
//        tags = "@disi_user_deposit_filter_date",
//        tags = "@disi_petty_cash_filter_date",
//        tags = "not @navigate_disi_list_withdraw_page",
//        tags = "@disi_list_withdraw_advice",
        // tag digital goods
//        tags= "@navigate_digitalgoods_transsuspect",
        // tag m&m
//        tags= "@failed_add_data_partner",
//        tags= "@navigate_to_solusipay_web_partner_setting",
//        tags = "@fill_valid_marketing_message or @send_message",
//        tags = "@m&m_export_transaction",
//        tags = "@m&m_filter_by_status or @m&m_export_transaction",
//        tags = "@navigate_to_voucher_game_on_solusipayWeb",
//        tags = "@navigate_to_solusipay_web_partner_setting",
        // tag ototrans
//        tags = "@ototrans_success_bulk_disbursement",
        plugin ={"pretty",
//                "html:target/report/cucumber.html",
                "json:target/report/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
//        dryRun = true,
        monochrome = true
)

public class SOBRunner {
    private static String author = "Yudi Musthofa";
    private static String project = "SOB Automation Reports";
    private static String urlProject = SOBConstant.urlSOB;
    private static String envTesting = SOBConstant.envTes;
    private static ExtentReports extentReports = ExtentService.getInstance();

    @BeforeClass
    public static void prepareEnvironment() {
        ExtentReport.editExtentProperties("sob");

        extentReports.setSystemInfo("Engineer", author);
        extentReports.setSystemInfo("Project", project);
        extentReports.setSystemInfo("Url", urlProject);
        extentReports.setSystemInfo("Enviroment", envTesting);
    }

//    @AfterClass
//    public static void generateReport() {
//        String tittleReport = project + ", Created by: " + author;
//        Configuration config = new Configuration(new File("target"), tittleReport);
//        ReportBuilder reportBuilder = new ReportBuilder(
//                Arrays.asList("target/report/cucumber.json"),
//                config
//        );
//        reportBuilder.generateReports();
//    }

    @AfterSuite
    public void tearDownSuite() {
        // reporter.endReport();
        extentReports.flush();
    }
}
