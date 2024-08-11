package com.ids.automation.stepdefinitions.solusipayapp;

import com.ids.automation.configuration.MobileSetup;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utility.Helper;

import java.util.Properties;
import java.util.logging.Logger;

public class RegisterStep {
    public static Scenario scenario;
    private static Logger LOG = Logger.getLogger(LoginStep.class.getName());
    static WebDriver driver;

    private byte[] screenshotData;
    private static GlobalStep globalStep = new GlobalStep();

    // step setup

    @Before
    public void afterScenario(Scenario scenario) {
        RegisterStep.scenario = scenario;
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


    // step scenario
    @Then("user click register")
    public void user_click_register() {
        setUp();
        // Write code here that turns the phrase above into concrete actions
        WebElement loginButton = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[4]"));
        loginButton.click();
        sleep(10000);
        LOG.info("user register");

    }


    @Then("user at register page")
    public void user_at_register_page() {
        // Write code here that turns the phrase above into concrete actions
        WebElement titleElement = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Daftar Akun\"]"));
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Register Page");
        sleep(1000);
        LOG.info("user at register page");

    }
    @When("user fill register form \\({string}{int}, {string}, {string} , {string})")
    public void user_fill_register(String phoneNumber, int length,String name, String password, String repassword) {

        WebElement phoneNumberField = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.EditText"));
        phoneNumberField.sendKeys(phoneNumber + Helper.randomString2(length));
        LOG.info("User fill phone number field");


        WebElement nameField = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.EditText"));
        nameField.sendKeys(name);
        LOG.info("User fill name field");

        WebElement passwordField = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.widget.EditText"));
        passwordField.sendKeys(password);
        LOG.info("User fill password field");

        WebElement repasswordField = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[4]/android.view.ViewGroup/android.widget.EditText"));
        repasswordField.sendKeys(repassword);
        LOG.info("User fill repassword field");

        WebElement registerButton = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[8]"));
        registerButton.click();

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Fill Register Form");
        sleep(10000);
        LOG.info("user finish fill register form");

    }
    @Then("user {string} to get otp \\(message : {string})")
    public void user_to_register(String condition, String message) {
        // Write code here that turns the phrase above into concrete actions
        if(condition.equals("Successfully")){
            WebElement otpPage = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Masukkan Kode Verifikasi\"]"));
        }else{
            //todo check flash component
            //android.widget.TextView[@text="Mobile Phone Number is required."]
            WebElement invalidMessage = driver.findElement(By.xpath("//android.widget.TextView[@text=\"" + message + "\"]"));
        }

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "User " + condition + " login");
        LOG.info("User " + condition + " login");

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
