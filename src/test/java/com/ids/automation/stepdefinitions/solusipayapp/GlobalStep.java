package com.ids.automation.stepdefinitions.solusipayapp;

import com.ids.automation.configuration.MobileSetup;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utility.Helper;

import java.util.Properties;
import java.util.logging.Logger;

public class GlobalStep {


    public static Scenario scenario;
    private static Logger LOG = Logger.getLogger(GlobalStep.class.getName());
    static WebDriver driver;

    private byte[] screenshotData;

    @Before
    public void afterScenario(Scenario scenario) {
        GlobalStep.scenario = scenario;
    }

    @Before("@SolusiPayAPP")
    public void setUp(){
        LOG.info("Setup Test");

        Properties prop = new Properties();
        try {
//            prop.load(App.class.getClassLoader().getResourceAsStream("config.properties"));
//            String uri = prop.getProperty("");
//            String appPackage = prop.getProperty("");
//            String activityPackage = prop.getProperty("");

            driver = MobileSetup.getDriver("http://127.0.0.1:4723/", "com.solusipay.id", "com.solusipay.MainActivity");
            Helper.sleep(5000);
            LOG.info("Setup Done");
        } catch (Exception e) {
            LOG.info("Setup Fail");
            e.printStackTrace();
        }

    }

    @Given("user at landing page")
    public void user_at_landing_page() {
        // Write code here that turns the phrase above into concrete actions
        setUp();
        WebElement titleElement = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Selamat Datang di Solusipay!\"]"));
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Landing Page");
        LOG.info("user at landing page");

    }
}
