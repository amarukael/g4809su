package com.ids.automation.stepdefinitions.solusipayweb;

import com.ids.automation.configuration.BrowserSetup;
import io.cucumber.java.After;

public class Hooks {
    @After
    public void tearDown() {
        BrowserSetup.closeDriver();
        BrowserSetup.closeDriver2();
        BrowserSetup.closeDriverMobile();
    }
}
