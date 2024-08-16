package com.ids.automation.testrunner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.service.ExtentService;
import com.ids.automation.stepdefinitions.sob.Hooks;

import constant.SOBConstant;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import utility.ExtentReport;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/sob/digitalgoods/DigitalGoodsProduct.feature", glue = {
        "com.ids.automation.stepdefinitions.sob" }, tags = "@dgms_product_filter", plugin = { "pretty",
                // "html:target/report/cucumber.html",
                "json:target/report/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" },
        // dryRun = true,
        monochrome = true)

public class SOBRunner {
    // private static String namefile = "[SOB][DGMS][FE] Product Master - Category
    // Add New Category";
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

    @AfterClass
    public static void rename() {
        String nameFile = Hooks.getData();
        String oldFilePath = "test-output/Report-Automation_ 16_Aug_24/PDF Report/ExtentPDF.pdf";
        String newFilePath = "test-output/Report-Automation_ 16_Aug_24/PDF Report/" + nameFile + ".pdf";
        File oldFile = new File(oldFilePath);
        File newFile = new File(newFilePath);
        if (newFile.exists()) {
            newFile.delete();
        }

        // Mencoba merename file
        if (oldFile.renameTo(newFile)) {
            System.out.println("File berhasil di-rename.");
        } else {
            System.out.println("Gagal merename file.");
        }
        System.out.println(nameFile);
    }
}
