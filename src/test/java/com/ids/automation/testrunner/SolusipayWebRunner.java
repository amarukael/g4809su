package com.ids.automation.testrunner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.service.ExtentService;
import constant.SolusipayWebConstant;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.testng.annotations.AfterSuite;
import utility.ExtentReport;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/solusipayWeb/pascabayar/Pascabayar.feature",
        glue= {"com.ids.automation.stepDefinitions.solusipayweb"},
        tags = "@Transaction",
        plugin ={"pretty",
//                "html:target/report/cucumber.html",
                "json:target/report/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
//        dryRun = true,
        monochrome = true
)

public class SolusipayWebRunner {
    private static String author = "Hermanto Joko Lumekso";
    private static String project = "Solusipay Web Automation Reports";
    private static String urlProject = SolusipayWebConstant.UrlSolpayWeb;
    private static String envTesting = SolusipayWebConstant.envTes;
    private static ExtentReports extentReports = ExtentService.getInstance();

    @BeforeClass
    public static void prepareEnvironment() {
        ExtentReport extReport = new ExtentReport();
        ExtentReport.editExtentProperties("solusipayWeb");

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
