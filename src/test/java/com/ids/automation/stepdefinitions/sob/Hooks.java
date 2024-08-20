package com.ids.automation.stepdefinitions.sob;

import com.ids.automation.configuration.BrowserSetup;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class Hooks {
    public static String scenarioName;

    @After
    public void tearDown(Scenario scenario) {
        scenarioName = scenario.getName();
        BrowserSetup.closeDriver();
        BrowserSetup.closeDriver2();
        BrowserSetup.closeDriverMobile();
    }

    public static String getData() {
        return scenarioName;
    }
}
