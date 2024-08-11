package com.ids.automation.stepdefinitions.sob;

import com.ids.automation.configuration.BrowserSetup;
//import com.itextpdf.text.*;
import constant.SOBConstant;
import helper.SOBHelper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.sob.SOBHomePages;
import pages.sob.SOBLoginPages;
import utility.Helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;


public class SOBLoginSteps {
    public static Scenario scenario;
    static WebDriver driver;
    static WebDriver driver2;
    SOBLoginPages loginPage;
    SOBHomePages homePage;
    SOBLoginPages loginPage2;
    SOBHomePages homePage2;
    SOBHelper sobHelper = new SOBHelper();
    SOBConstant sobConstant = new SOBConstant();
    private byte[] screenshotData;
    DateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    @Before
    public void afterScenario(Scenario scenario) {
        SOBLoginSteps.scenario = scenario;
    }

    @Given("I open SOB Website")
    public void i_open_sob_website() {
        setUp();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "SOB Website");
        sobHelper.delay(1000);
    }

    @When("I logged in with username {string}")
    public void i_logged_in_with_username(String user) {
        setUpLogin(user, "login");
        String conditional = scenario.getName();
        Pattern pattern = Pattern.compile("Success");
        Matcher matcher = pattern.matcher(conditional);
        if (matcher.find()){
            sobHelper.writeProperties("extent.username.sob", user);
        }else {
            System.out.println("extent.username.sob not modified");
        }
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Login");
        sobHelper.delay(1000);
        i_click_submit_button();
    }

    @When("I click submit button")
    public void i_click_submit_button(){
        loginPage.pressSubmit();
        sobHelper.delay(1000);
    }

    @When("I logged in with same username {string}")
    public void i_logged_in_with_same_Username(String user) {
        setUp2();
        screenshotData = Helper.takeScreenshot(driver2);
        scenario.attach(screenshotData, "image/png", "SOB Website 2nd");
        sobHelper.delay(1000);

        setUpLogin2(user, "login");
        screenshotData = Helper.takeScreenshot(driver2);
        scenario.attach(screenshotData, "image/png", "Login 2nd");
        sobHelper.delay(1000);
        loginPage2.pressSubmit();
        sobHelper.delay(1000);

        assertEquals(homePage2.getWelcomeMessageText(), "Welcome to Our Website");
        screenshotData = Helper.takeScreenshot(driver2);
        scenario.attach(screenshotData, "image/png", "Homepage Login 2nd");
        sobHelper.delay(1000);
    }

    @Then("I on a Homepage")
    public void i_on_a_homepage() {
        assertEquals(homePage.getWelcomeMessageText(), "Welcome to Our Website");
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Homepage");
        sobHelper.delay(1000);
    }

    @Then("I back to window 1st login")
    public void i_refresh_homepage_in_1st_Login() {
        WebDriver.Window win = driver2.manage().window();
        win.minimize();
        sobHelper.delay(800);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Homepage Refresh");
    }

    @Then("I wait expired session and refresh Homepage")
    public void i_wait_expired_session_and_refresh_homepage() {
        Date dNow = new Date();
        scenario.log("Start: " + ft.format(dNow));
//        System.out.println("Start: " + ft.format(dNow));
        for (int i = 1; i < 61; i++) {
            System.out.println("Delay ke " + i);
            sobHelper.delay(60000);
        }

        sobHelper.delay(1000);
        Date dNow2 = new Date();
        scenario.log("End: " + ft.format(dNow2));
//        System.out.println("End: " + ft.format(dNow2));

        driver.navigate().refresh();
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Homepage Refresh");
    }

    @Then("I back to Login Page")
    public void i_back_to_login_page() {
        sobHelper.delay(800);
        assertEquals(loginPage.getTextLoginPage(), "Develop your business with IDS");
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Login Page");
        sobHelper.delay(800);
    }

    public void setUpLogin(String user, String flag) {
        Map<String, String> userCredentials = sobHelper.getUserCredentials(user);
        if (userCredentials != null) {
            String username = userCredentials.get("Username");
            String password = userCredentials.get("Password");
            loginPage.fillUsername(username);
            sobHelper.delay(800);
            loginPage.fillPassword(password);
            sobHelper.delay(800);
        }

        if (!flag.equals("login"))
            i_click_submit_button();
    }

    public void setUpLogin2(String user, String flag) {
        Map<String, String> userCredentials = sobHelper.getUserCredentials(user);
        if (userCredentials != null) {
            String username = userCredentials.get("Username");
            String password = userCredentials.get("Password");
            loginPage2.fillUsername(username);
            sobHelper.delay(800);
            loginPage2.fillPassword(password);
            sobHelper.delay(800);
        }
    }

    public void setUp() {
        driver = BrowserSetup.getDriver();
        loginPage = new SOBLoginPages(driver);
        homePage = new SOBHomePages(driver);
        driver.get(SOBConstant.urlSOB);
    }

    public void setUp2() {
        driver2 = BrowserSetup.getDriver2();
        loginPage2 = new SOBLoginPages(driver2);
        homePage2 = new SOBHomePages(driver2);
        driver2.get(SOBConstant.urlSOB);
    }

    public static boolean matcher_condition(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);

        boolean found = false;
        while (matcher.find()) {
            found = true;
            System.out.println("Full match: " + matcher.group(0));

            // Tampilkan grup-grup yang cocok (jika ada)
            for (int i = 1; i <= matcher.groupCount(); i++) {
                System.out.println("Group " + i + ": " + matcher.group(i));
            }
        }
        return found;
    }
}

