package com.ids.automation.stepdefinitions.solusipayapp;

import com.ids.automation.configuration.MobileSetup;
import io.cucumber.java.After;

import java.io.IOException;

public class Hook {

    @After
    public void tearDown() throws IOException {
        MobileSetup.closeDriver();
    }
}
