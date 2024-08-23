package com.ids.automation.stepdefinitions.solusipayapp;

import com.ids.automation.configuration.MobileSetup;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class DeleteAccountStep {
    public static Scenario scenario;
    private static Logger LOG = Logger.getLogger(DeleteAccountStep.class.getName());
    static WebDriver driver;

    private byte[] screenshotData;

    // step setup

    @Before
    public void afterScenario(Scenario scenario) {
        DeleteAccountStep.scenario = scenario;
    }

    @Before("@SolusiPayAPP")
    private void setUp(){
        LOG.info("Setup Test");

        try {
            driver = MobileSetup.getDriver("http://127.0.0.1:4723/",
                    "com.solusipay.id",
                    "com.solusipay.MainActivity");
            sleep(5000);
            LOG.info("Setup Done");
        } catch (Exception e) {

            LOG.info("Setup Fail");
            e.printStackTrace();
        }
    }

    private void sleep(int milliSecond){
        try {
            Thread.sleep(milliSecond);
        }catch (Exception e){
            e.printStackTrace();
            LOG.info(e.getMessage());
        }
    }
}
