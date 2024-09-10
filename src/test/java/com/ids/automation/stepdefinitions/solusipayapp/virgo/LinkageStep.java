package com.ids.automation.stepdefinitions.solusipayapp.virgo;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utility.Helper;

import java.util.List;
import java.util.logging.Logger;

public class LinkageStep {

    //android.widget.Image[@text="virgo"]
    //android.widget.HorizontalScrollView/android.view.ViewGroup/android.widget.ImageView[2] -- (>) untuk melakukan linkage
    //android.widget.CheckBox[@text="Dengan ini saya memahami dan menyetujui syarat & ketentuan Virgo."]
    //android.widget.Button[@text="Hubungkan Akun"]
    //android.widget.Button[@text="Lanjutkan"]
    //android.webkit.WebView[@text="Verify OTP | Virgo Co-Brand"]/android.view.View/android.view.View/android.view.View/android.widget.TextView[3]
    //android.webkit.WebView[@text="Verify Passcode | Virgo Co-Brand"]/android.view.View/android.view.View/android.view.View/android.widget.TextView[2]


    //register
    //android.widget.TextView[@text="Buka akun Virgo"]
    //android.webkit.WebView[@text="Create Passcode | Virgo Co-Brand"]/android.view.View/android.view.View/android.view.View/android.widget.TextView[2]
    //android.widget.Button[@text="Oke, Sip"]

    public static Scenario scenario;
    private static Logger LOG = Logger.getLogger(LinkageStep.class.getName());
    static WebDriver driver;

    private byte[] screenshotData;

//    @Before("@SolusiPayAPP")
//    private void setUp(){
//        LOG.info("Setup Test");
//
//        Properties prop = new Properties();
//        try {
////            prop.load(App.class.getClassLoader().getResourceAsStream("config.properties"));
////            String uri = prop.getProperty("");
////            String appPackage = prop.getProperty("");
////            String activityPackage = prop.getProperty("");
//
//            driver = MobileSetup.getDriver("http://127.0.0.1:4723/", "com.solusipay.id", "com.solusipay.MainActivity");
//            sleep(5000);
//            LOG.info("Setup Done");
//        } catch (Exception e) {
//
//            LOG.info("Setup Fail");
//            e.printStackTrace();
//        }
//
//    }

    // step setup

    @Before
    public void afterScenario(Scenario scenario) {
        LinkageStep.scenario = scenario;
    }

    @Given("user click linkage")
    public void user_click_linkage() {
        WebElement linkageButton = driver.findElement(By.xpath("//android.widget.HorizontalScrollView/android.view.ViewGroup/android.widget.ImageView[2]"));
        linkageButton.click();
    }


    @Then("virgo web view display")
    public void virgo_web_view_display() {
        // Write code here that turns the phrase above into concrete actions
        WebElement virgoWebView = driver.findElement(By.xpath("//android.widget.Image[@text=\"virgo\"]"));
        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Landing Page");

    }
    @When("user follow all virgo web view step with passcode {string}")
    public void user_follow_all_virgo_web_view_step(String passcode) {
        // Write code here that turns the phrase above into concrete actions

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Landing Page");

        WebElement termButton = driver.findElement(By.xpath("//android.widget.CheckBox[@text=\"Dengan ini saya memahami dan menyetujui syarat & ketentuan Virgo.\"]"));
        termButton.click();
        sleep(400);

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Landing Page");

        WebElement connectAccountButton = driver.findElement(By.xpath("//android.widget.Button[@text=\"Hubungkan Akun\"]"));
        connectAccountButton.click();
        sleep(400);

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Landing Page");

        List<WebElement> registerState = driver.findElements(By.xpath("//android.widget.TextView[@text=\"Buka akun Virgo\"]"));


        if(registerState.isEmpty()){
            //register state

            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Landing Page");
            WebElement nextButton = driver.findElement(By.xpath("//android.widget.Button[@text=\"Lanjutkan\"]"));
            nextButton.click();
            sleep(400);


            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Landing Page");
            WebElement otpField = driver.findElement(By.xpath("//android.webkit.WebView[@text=\"Verify OTP | Virgo Co-Brand\"]/android.view.View/android.view.View/android.view.View/android.widget.TextView[3]"));
            otpField.sendKeys("020202");
            sleep(400);


            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Landing Page");
            WebElement passwordField = driver.findElement(By.xpath("//android.webkit.WebView[@text=\"Create Passcode | Virgo Co-Brand\"]/android.view.View/android.view.View/android.view.View/android.widget.TextView[2]"));
            passwordField.sendKeys(passcode);
            sleep(400);


            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Landing Page");
            WebElement rePasswordField = driver.findElement(By.xpath("//android.webkit.WebView[@text=\"Create Passcode | Virgo Co-Brand\"]/android.view.View/android.view.View/android.view.View/android.widget.TextView[2]"));
            rePasswordField.sendKeys(passcode);
            sleep(400);
        }else{

            //login state

            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Landing Page");
            WebElement nextButton = driver.findElement(By.xpath("//android.widget.Button[@text=\"Lanjutkan\"]"));
            nextButton.click();
            sleep(400);


            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Landing Page");
            WebElement otpField = driver.findElement(By.xpath("//android.webkit.WebView[@text=\"Verify OTP | Virgo Co-Brand\"]/android.view.View/android.view.View/android.view.View/android.widget.TextView[3]"));
            otpField.sendKeys("020202");
            sleep(400);


            screenshotData = Helper.takeScreenshot(driver);
            scenario.attach(screenshotData, "image/png", "Landing Page");
            WebElement passwordField = driver.findElement(By.xpath("//android.webkit.WebView[@text=\"Verify Passcode | Virgo Co-Brand\"]/android.view.View/android.view.View/android.view.View/android.widget.TextView[2]"));
            passwordField.sendKeys(passcode);
            sleep(400);

        }


        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Landing Page");
        WebElement doneButton = driver.findElement(By.xpath("//android.widget.Button[@text=\"Lanjutkan\"]"));
        doneButton.click();
        sleep(400);

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Landing Page");

    }
    @Then("virgo account linked with solpay account")
    public void virgo_account_linked_with_solpay_account() {
        // Write code here that turns the phrase above into concrete actions
        WebElement virgo = driver.findElement(By.xpath(""));

        screenshotData = Helper.takeScreenshot(driver);
        scenario.attach(screenshotData, "image/png", "Landing Page");
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
