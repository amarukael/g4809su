package com.ids.automation.testrunner;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.service.ExtentService;

import constant.SolusipayWebConstant;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import utility.ExtentReport;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/solusipayapp/Login.feature",
        glue= {"com.ids.automation.stepDefinitions.solusipayapp"},
        plugin ={"pretty",
//                "html:target/report/cucumber.html",
                "json:target/report/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
//        dryRun = true,
        monochrome = true
)
public class SolusiPayAppRunner {
        private static String author = "Kelby Hubert";
    private static String project = "Solusipay App Automation Reports";
    private static String urlProject = SolusipayWebConstant.UrlSolpayWeb;
    private static String envTesting = SolusipayWebConstant.envTes;
    private static ExtentReports extentReports = ExtentService.getInstance();

    @BeforeClass
    public static void prepareEnvironment() {
        ExtentReport extReport = new ExtentReport();
        extReport.editExtentProperties("solusipayWeb");

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
