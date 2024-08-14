package com.ids.automation.testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features/RemidAir/RemidAirLogin.feature",
        glue= {"com.ids.automation.stepDefinitions.RemidAir"},
        tags= "",
        plugin ={
                "pretty",
                "json:target/JSONReports/report.json",
                "junit:target/JUnitReports/report.xml",
                "html:target/HtmlReports"})

public class RemidAirRunner {
}
