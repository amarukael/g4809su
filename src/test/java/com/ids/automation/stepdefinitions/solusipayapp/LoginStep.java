package com.ids.automation.stepdefinitions.solusipayapp;

import java.util.Properties;
import java.util.logging.Logger;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ids.automation.configuration.MobileSetup;
import org.openqa.selenium.WebElement;
import utility.Helper;

public class LoginStep {
    public static Scenario scenario;
    private static Logger LOG = Logger.getLogger(LoginStep.class.getName());
    static WebDriver driver;

    private byte[] screenshotData;

    // step setup

    @Before
    public void afterScenario(Scenario scenario) {
        LoginStep.scenario = scenario;
    }

    @Before("@SolusiPayAPP")
    private void setUp(){
        LOG.info("Setup Test");

        Properties prop = new Properties();
        try {
//            prop.load(App.class.getClassLoader().getResourceAsStream("config.properties"));
//            String uri = prop.getProperty("");
//            String appPackage = prop.getProperty("");
//            String activityPackage = prop.getProperty("");

            driver = MobileSetup.getDriver("http://127.0.0.1:4723/", "com.solusipay.id", "com.solusipay.id.MainActivity");
            sleep(5000);
            LOG.info("Setup Done");
        } catch (Exception e) {

            LOG.info("Setup Fail");
            e.printStackTrace();
        }
        
    }


    // step scenario
    @Then("user click login")
    public void user_click_login() {
        setUp();
        // Write code here that turns the phrase above into concrete actions
        WebElement loginButton = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]"));
        loginButton.click();
        sleep(10000);
        LOG.info("user login");

    }
    @Then("user at login page")
    public void user_at_login_page() {
        // Write code here that turns the phrase above into concrete actions
        WebElement titleElement = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Masuk Akun\"]"));
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Login Page");
        sleep(1000);
        LOG.info("user at login page");

    }
    @When("user fill login form with username {string} and password {string}")
    public void user_fill_login_form_with_username_and_password(String phoneNumber, String password) {
        // Write code here that turns the phrase above into concrete actions
        WebElement phoneNumberField = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.EditText"));
        phoneNumberField.sendKeys(phoneNumber);

        WebElement passwordField = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.EditText"));
        passwordField.sendKeys(password);


        WebElement loginButton = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[4]"));
        loginButton.click();

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Fill Form");
        sleep(10000);
        LOG.info("user fill login page");

    }
    @Then("user {string} to login")
    public void user_to_login(String condition) {
        // Write code here that turns the phrase above into concrete actions
        if(condition.equals("Successfully")){
            WebElement homeLabelElement = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Selamat Datang\"]"));
        }else{
            //todo check flash component
        }

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "User " + condition + " login");
        LOG.info("User " + condition + " login");

    }


    @Given("user open solusipayapp")
    public void userOpenSolusipayapp() {
        LOG.info("user at landing page");
    }

    private void sleep(int milliSecond){
        try {
            Thread.sleep(milliSecond);
        }catch (Exception e){
            e.printStackTrace();
            LOG.info(e.getMessage());
        }
    }

    @When("user click {string} login")
    public void userClickLogin(String loginMethod) {

        WebElement loginButton;
        if(loginMethod.equals("google")){
            System.out.println("tes");
            loginButton = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[7]/android.widget.ImageView"));
        }else{
            loginButton = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[8]/android.widget.ImageView"));
        }

        loginButton.click();
        sleep(2500);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Login with" + loginMethod);
        sleep(2500);

    }

    @And("user choose {string}")
    public void userChoose(String email) {
        WebElement emailElement = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.google.android.gms:id/account_name\" and @text=\"" + email + "\"]"));
        emailElement.click();

        sleep(2500);
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "User Choose " + email);
        sleep(2500);
    }
}
