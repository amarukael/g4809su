package com.ids.automation.stepdefinitions.sob;

import org.junit.Before;

import com.ids.automation.configuration.BrowserSetup;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class Hooks {
    public static String scenarioName;

    @Before
    public void scenarioInit(Scenario scenario) {
        scenarioName = scenario.getName();
        System.out.println("Before : " + scenarioName);
    }

    @After
    public void tearDown(Scenario scenario) {
        // document.close();
        scenarioName = scenario.getName();
        System.out.println("After : " + scenarioName);
        BrowserSetup.closeDriver();
        BrowserSetup.closeDriver2();
        BrowserSetup.closeDriverMobile();
    }

    public static String getData() {
        return scenarioName;
    }
}
