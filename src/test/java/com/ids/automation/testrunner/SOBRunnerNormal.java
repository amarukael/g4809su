package com.ids.automation.testrunner;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.service.ExtentService;

import constant.SOBConstant;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import utility.ExtentReport;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/sob/digitalgoods/DigitalGoodsProduct.feature", glue = {
        "com.ids.automation.stepdefinitions.sob" }, tags = "@dgms_add_new_product", plugin = { "pretty",
                // "html:target/report/cucumber.html",
                "json:target/report/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" },
        // dryRun = true,
        monochrome = true)

public class SOBRunnerNormal {
    private static String author = "M. Fahmi Amaruddin";
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

    @AfterSuite
    public void tearDownSuite() {
        // reporter.endReport();
        extentReports.flush();
    }
}
