package com.ids.automation.testrunner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.service.ExtentService;
import com.ids.automation.stepdefinitions.solusipayweb.Hooks;

import constant.SolusipayWebConstant;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import utility.ExtentReport;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/solusipayweb/asuransi/asuransi.feature", glue = {
        "com.ids.automation.stepdefinitions.solusipayweb" }, tags = "@transaction_ehe_with_payment_va", plugin = {
                "pretty",
                // "html:target/report/cucumber.html",
                "json:target/report/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" },
        // dryRun = true,
        monochrome = true)

public class SolusipayWebRunner {
    private static String author = "M. Fahmi Amaruddin";
    private static String project = "Solusipay Automation Reports";
    private static String urlProject = SolusipayWebConstant.UrlSolpayWeb;
    private static String envTesting = SolusipayWebConstant.envTes;
    private static ExtentReports extentReports = ExtentService.getInstance();

    @BeforeClass
    public static void prepareEnvironment() {
        ExtentReport.editExtentProperties("sob");

        extentReports.setSystemInfo("Engineer", author);
        extentReports.setSystemInfo("Project", project);
        extentReports.setSystemInfo("Url", urlProject);
        extentReports.setSystemInfo("Enviroment", envTesting);
    }

    @AfterSuite
    public void tearDownSuite() {
        // reporter.endReport();
        extentReports.flush();
    }

    @AfterClass
    public static void rename() {
        String nameFile = Hooks.getData();
        System.out.println(nameFile);
        String oldFilePath = "C:\\Users\\fahmi.amaruddin\\Documents\\repo\\Github\\Automation-UI\\test-output\\Report-Automation_ 19_Aug_24\\PDF Report\\ExtendPDF.pdf";
        String newFilePath = "C:\\Users\\fahmi.amaruddin\\Documents\\repo\\Github\\Automation-UI\\test-output\\Report-Automation_ 19_Aug_24\\PDF Report\\"
                + nameFile + ".pdf";
        File oldFile = new File(oldFilePath);
        File newFile = new File(newFilePath);
        if (newFile.exists()) {
            newFile.delete();
        }

        while (true) {
            try {
                if (oldFile.renameTo(newFile)) {
                    System.out.println("File berhasil di-rename.");
                    break;
                } else {
                    System.out.print(oldFile.exists());
                    System.out.println("Gagal merename file.");
                }
                Thread.sleep(500);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
