package com.ids.automation.stepdefinitions.sob;

import com.ids.automation.configuration.BrowserSetup;
import io.cucumber.java.After;

public class Hooks {
    @After
    public void tearDown() {
//        document.close();
        BrowserSetup.closeDriver();
        BrowserSetup.closeDriver2();
        BrowserSetup.closeDriverMobile();
    }
}
